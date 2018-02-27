
package org.jlab.coda.cool.parser;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.QuerySolution;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

import org.jlab.coda.cool.ontology.AComponent;
import org.jlab.coda.cool.ontology.AControl;
import org.jlab.coda.cool.ontology.AChannel;
import org.jlab.coda.cool.ontology.ALink;
import org.jlab.coda.cool.ontology.AOption;
import org.jlab.coda.cool.ontology.APackage;
import org.jlab.coda.cool.ontology.APlugin;
import org.jlab.coda.cool.ontology.APanel;
import org.jlab.coda.cool.ontology.AProcess;
import org.jlab.coda.cool.ontology.ARule;
import org.jlab.coda.cool.ontology.AScript;
import org.jlab.coda.cool.ontology.AService;
import org.jlab.coda.cool.ontology.AState;
import org.jlab.coda.cool.ontology.AWidget;
import org.jlab.coda.cool.ontology.HMI;

import org.jlab.coda.cool.util.CoolConstants;
import org.jlab.coda.cool.util.CoolTools;
import org.jlab.coda.cool.util.CodaTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Control Oriented Ontology Language parser.
 * This class is used by afecs-4.0.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public final class CParser {
    /* Jena model */
    private Model gModel;

    // List of the included cool configuration file jena models
    private Map<String, Model> includeModels = new HashMap<>();

    // Cool  top level run configuration files directory
    private String runConfigDir;

    // Cool  top level configuration files directory
    private String userConfigDir;

    // default option directories for the control
    private Set<String> optionsDirs = new HashSet<>();

    private int numberOfFileComponents;
    private int erId = -1;
    private int pebId = -1;
    private int sebId = -1;
    private int ebId = -1;
    private int cdebId = -1;
    private int dcId = -1;

    private String cool;
    private String expid;
    // this is passed by the rcGui rtv table set by the user at the run time.
    private Map<String, String> rTVMap = new HashMap<>();

    private CParser() {
    }

    /**
     * Builder for fluent interface.
     *
     * @return CParser object
     */
    public static CParser builder() {
        return new CParser();
    }

    /**
     * Define cool String.
     *
     * @param cool String
     * @return CParser object
     */
    public CParser cool(String cool) {
        this.cool = cool;
        return this;
    }

    /**
     * Define experiment ID.
     *
     * @param expid String
     * @return CParser object
     */
    public CParser expid(String expid) {
        this.expid = expid;
        return this;
    }

    /**
     * Define real time variable map.
     *
     * @param rTVMap rtv map
     * @return CParser object
     */
    public CParser rtv(Map<String, String> rTVMap) {
        this.rTVMap = rTVMap;
        return this;
    }

    /**
     * The build men=thod of the CParser fluent interface.
     *
     * @return CParser object
     */
    public CParser build() {
        runConfigDir = cool + File.separator
                + expid + File.separator
                + "config" + File.separator;

        userConfigDir = cool + File.separator
                + expid + File.separator
                + "user" + File.separator;

        return this;
    }

    /**
     * Constructor.
     *
     * @param fileName of the rdf/cool configuration file
     * @param debug    if true prints statements of the cool description
     * @return stat status of construction
     */
    public boolean openFile(String fileName, boolean debug) {
        boolean stat;
        stat = createModel(runConfigDir + "Control" + File.separator + fileName) && generateFinalModel();
        if (stat && debug) {
            printStatements(gModel);
        }
        return stat;
    }

    /**
     * Parses Cool control concept and fills {@link AControl} object.
     *
     * @param runType        of the control
     * @param onlyComponents parse only components of a control
     * @return AControl object
     */
    public AControl parseControl(String runType, boolean onlyComponents) {
        erId = -1;
        pebId = -1;
        sebId = -1;
        ebId = -1;
        cdebId = -1;
        dcId = -1;
        numberOfFileComponents = 0;

//        default directory for option files
        String fn = runConfigDir + "Control" + File.separator + runType + File.separator + "Options";
        optionsDirs.add(fn);

        AControl c = new AControl();
        c.setName(runType);
        c.setComponents(parseComponent(CoolConstants.COOL_HTTP_BASE
                + "Control" + File.separator
                + runType + File.separator
                + runType + "#"
                + runType, "hasComponent"));

        if (!onlyComponents) {

            c.setGui(parseHmi(CoolConstants.COOL_HTTP_BASE
                    + "Control" + File.separator
                    + runType + File.separator
                    + runType + "#"
                    + runType, "hasHmi"));

            c.setOption(parseOption(CoolConstants.COOL_HTTP_BASE
                    + "Control" + File.separator
                    + runType + File.separator
                    + runType + "#"
                    + runType, "hasOption"));


            // create a supervisor agent for this control
            AComponent duper = new AComponent();
            duper.setName("sms_" + runType);
            duper.setType("SMS");
            duper.setPriority(CodaTypes.SMS.priority());

            // add options to the supervisor agent
            if (c.getOption() != null) {
                if (c.getOption().getEventLimit() > 0) {
                    duper.setEventLimit(c.getOption().getEventLimit());
                }
//                if (c.getOption().getDataLimit() > 0) {
//                    //@todo is not implemented for AComponent
//                }
            }

            // parse and add described processes for the control to the supervisor agent
            List<AProcess> processlist = parseProcess(CoolConstants.COOL_HTTP_BASE
                    + "Control"
                    + File.separator + runType
                    + File.separator + runType
                    + "#"
                    + runType, "hasProcess");

            if (processlist != null && !processlist.isEmpty()) {
                duper.setProcesses(processlist);
            }

            //add supervisor agent to the control
            c.setSupervisor(duper);
        }
        defineStreams(c.getComponents());

        return c;
    }

    /**
     * Defines number of streams defined in the configuration.
     *
     * @param comps List of control agents
     */
    private void defineStreams(List<AComponent> comps) {
        for (AComponent comp : comps) {
            comp.setStreamCount(numberOfFileComponents);
        }
    }

    /**
     * Creates Jena model of the rdf/cool file.
     *
     * @param fileName of the rdf/cool configuration file direct path
     * @return stat status of the operation
     */
    private boolean createModel(String fileName) {
        boolean stat = true;
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
// create the jena model
        Model model = ModelFactory.createDefaultModel();

        try {
            model.read(fis, CoolConstants.COOL_HTTP_BASE, "RDF/XML");
        } catch (Exception ee) {
            ee.printStackTrace();
            return false;
        }

        // add this model to the model list
        if (!includeModels.containsKey(fileName)) {
            includeModels.put(fileName, model);
        }

        // list the statements in the Model
        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            RDFNode node = stmt.getObject();

            if (node instanceof Resource) {
                if ((node.toString().endsWith(".rdf"))) {
                    String incName;
                    if (node.toString().contains(CoolConstants.COOL_HTTP_BASE)) {
                        incName = replace(node.toString(), CoolConstants.COOL_HTTP_BASE, runConfigDir);
                    } else {
                        return false;
                    }
                    if (incName.contains("Option")) {
                        String opp = incName.substring(0, incName.lastIndexOf(File.separator));

                        optionsDirs.add(opp);
                    }
                    stat = createModel(incName);
                    if (!stat) {
                        return false;
                    }
                }
            }
        }
        return stat;
    }

    /**
     * Creates one big jena model including all sub-models.
     *
     * @return stat status of the operation
     */
    private boolean generateFinalModel() {
        boolean stat = false;
        if (!includeModels.isEmpty()) {
            // create union of the jena models
            gModel = ModelFactory.createDefaultModel();
            for (String s : includeModels.keySet()) {
                gModel = gModel.union(includeModels.get(s));
            }
            stat = true;
        }
        return stat;
    }

    /**
     * Replaces the substring of the given string with the new string.
     *
     * @param str     given string
     * @param pattern substring
     * @param replace the new string
     * @return result string
     */
    private String replace(String str, String pattern, String replace) {
        int s = 0;
        int e;
        StringBuilder result = new StringBuilder();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    /**
     * Debugging method prints all the rdf/cool statements of the model.
     *
     * @param model Jena model object
     */
    public void printStatements(Model model) {
        // list the statements in the Model
        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode node = stmt.getObject();
            System.out.println("subject   = " + subject.toString());
            System.out.println("predicate = " + predicate.toString());
            if (node instanceof Resource) {
                System.out.println("resource = " + node.toString());
            } else {
                System.out.println("literal = " + node.toString());
            }
            System.out.println();
        }
    }

    /**
     * Method creates a query from the string and executes it within the
     * jena model and returns the result as a string.
     *
     * @param subject   object of the cool concept
     * @param predicate String: cool predicate ( for example has timestamp, hasdatatype, etc.)
     * @return result           string
     */
    private String getValue(Object subject, String predicate) {
        Object x = null;
        String sq = "SELECT ?x "
                + "WHERE(<" + subject.toString()
                + "> <" + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(sq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            if (resultSet.hasNext()) {
                QuerySolution result = resultSet.nextSolution();
                x = result.getResource("x");
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (x == null) {
            return null;
        } else {
            return x.toString();
        }
    }

    /**
     * Method creates a query from the string and executes it within the
     * jena model and returns the result as a list of strings.
     *
     * @param subject   object of the cool concept
     * @param predicate String: cool predicate ( for example has timestamp, hasdatatype, etc.)
     * @return result           string
     */
    private List<String> getValueList(Object subject, String predicate) {
        Object x;
        List<String> l = new ArrayList<String>();

        String sq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + "> <"
                + CoolConstants.COOL_CORE
                + "" + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(sq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            if (resultSet.hasNext()) {
                QuerySolution result = resultSet.nextSolution();
                x = result.getResource("x");
                if (x != null) {
                    l.add(x.toString());
                }
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return l;
    }

    /**
     * Parsing the component of the control.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return cl       list of {@link AComponent} objects
     */

    private ArrayList<AComponent> parseComponent(Object subject, String predicate) {
        AComponent cmp;
        List<AProcess> processlist;
        List<AState> stateList;
        List<AService> services;
        HMI gui;
        APlugin plugin;
        AOption option;
        List<ALink> link;
        String tmps;

        ArrayList<AComponent> cl = new ArrayList<>();
        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";

        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                cmp = new AComponent();

                tmps = getValue(x, "hasName");
                if (tmps != null) {
                    cmp.setName(tmps);
                } else {
                    System.out.println("COOL: Component " + x + " does not have a name");
                    return null;
                }

                tmps = getValue(x, "hasClassPath");
                if (tmps != null) {
                    cmp.setClassPath(tmps);
                }

                tmps = getValue(x, "hasClassName");
                if (tmps != null) {
                    cmp.setClassName(tmps);
                }

                tmps = getValue(x, "hasType");
                if (tmps != null) {
                    if (tmps.equals(CodaTypes.FILE.name())) {
                        numberOfFileComponents++;
                        continue;
                    } else {
                        cmp.setType(tmps);
                        if (tmps.equals(CodaTypes.ER.name())) {
                            erId++;
                            cmp.setStreamId(erId);
                        } else if (tmps.equals(CodaTypes.PEB.name())) {
                            pebId++;
                            cmp.setStreamId(pebId);
                        } else if (tmps.equals(CodaTypes.SEB.name())) {
                            sebId++;
                            cmp.setStreamId(sebId);
                        } else if (tmps.equals(CodaTypes.EB.name())) {
                            ebId++;
                            cmp.setStreamId(ebId);
                        } else if (tmps.equals(CodaTypes.CDEB.name())) {
                            cdebId++;
                            cmp.setStreamId(cdebId);
                        } else if (tmps.equals(CodaTypes.DC.name())) {
                            dcId++;
                            cmp.setStreamId(dcId);
                        }
                    }
                }
                tmps = getValue(x, "hasID");
                if (tmps != null) {
                    try {
                        cmp.setId(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "hasDescription");
                if (tmps != null) {
                    cmp.setDescription(tmps);
                }

                tmps = getValue(x, "hasHost");
                if (tmps != null) {
                    cmp.setHost(tmps);
                }

                tmps = getValue(x, "hasCode");
                if (tmps != null) {
                    if (CoolTools.containsRTV(tmps)) {
                        cmp.setCode(CoolTools.checkRtvs(tmps, rTVMap));
                    } else {
                        cmp.setCode(tmps);
                    }
                }

                tmps = getValue(x, "hasPriority");
                if (tmps != null) {
                    try {
                        cmp.setPriority(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (cmp.getType().equalsIgnoreCase(CodaTypes.USR.name())) {
                        cmp.setPriority(CodaTypes.USR.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.SLC.name())) {
                        cmp.setPriority(CodaTypes.SLC.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.WNC.name())) {
                        cmp.setPriority(CodaTypes.WNC.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.ER.name())) {
                        cmp.setPriority(CodaTypes.ER.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.FCS.name())) {
                        cmp.setPriority(CodaTypes.FCS.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.PEB.name())) {
                        cmp.setPriority(CodaTypes.PEB.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.SEB.name())) {
                        cmp.setPriority(CodaTypes.SEB.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.EB.name())) {
                        cmp.setPriority(CodaTypes.EB.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.CDEB.name())) {
                        cmp.setPriority(CodaTypes.CDEB.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.DC.name())) {
                        cmp.setPriority(CodaTypes.DC.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.ROC.name())) {
                        cmp.setPriority(CodaTypes.ROC.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.GT.name())) {
                        cmp.setPriority(CodaTypes.GT.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.TS.name())) {
                        cmp.setPriority(CodaTypes.TS.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.SMS.name())) {
                        cmp.setPriority(CodaTypes.SMS.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.RCS.name())) {
                        cmp.setPriority(CodaTypes.RCS.priority());
                    } else if (cmp.getType().equalsIgnoreCase(CodaTypes.FILE.name())) {
                        cmp.setPriority(CodaTypes.FILE.priority());
                    } else {
                        System.err.println("Error: "
                                + cmp.getType()
                                + " type is not defined for the component "
                                + " "
                                + cmp.getName());
                    }
                }

                tmps = getValue(x, "hasUserConfig");
                if (tmps != null) {
                    if (CoolTools.containsRTV(tmps)) {
                        cmp.setUserConfig(CoolTools.checkRtvs(tmps, rTVMap));
                    } else if (tmps.startsWith("/")) {
                        cmp.setUserConfig(tmps);
                    } else {
                        cmp.setUserConfig(userConfigDir + tmps);
                    }
                }

                tmps = getValue(x, "hasSession");
                if (tmps != null) {
                    cmp.setSession(tmps);
                }

                tmps = getValue(x, "hasRunType");
                if (tmps != null) {
                    cmp.setRunType(tmps);
                }

                option = parseOption(x, "hasOption");
                if (option != null) {
                    cmp.setOption(option);
                    for (String s : optionsDirs) {
                        cmp.addDefaultOptionDir(s);
                    }
                }

                tmps = getValue(x, "representsCoda2Component");
                if (tmps != null) {
                    cmp.setCoda2Component(tmps);
                }

                tmps = getValue(x, "isCodaComponent");
                if (tmps != null) {
                    cmp.setCodaComponent(tmps);
                }


                link = parseLink(x, "usesLink");
                if (link != null) {
                    cmp.setLinks(link);
                }

                tmps = getValue(x, "hasReportingInterval");
                if (tmps != null) {
                    try {
                        cmp.setReportingInterval(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                stateList = parseStates(x, "hasState");
                if (stateList != null && !stateList.isEmpty()) {
                    cmp.setStates(stateList);
                }
                processlist = parseProcess(x, "hasProcess");
                if (processlist != null && !processlist.isEmpty()) {
                    cmp.setProcesses(processlist);
                }

                services = parseServices(x, "providesService");
                if (services != null && !services.isEmpty()) {
                    cmp.setServices(services);
                }

                gui = parseHmi(x, "hasHMI");
                if (gui != null) {
                    cmp.setGui(gui);
                }

                plugin = parsePlugin(x, "hasCommunicationPlugin");
                if (plugin != null) {
                    cmp.setPlugin(plugin);
                }

                tmps = getValue(x, "XCo");
                if (tmps != null) {
                    try {
                        cmp.setX(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "YCo");
                if (tmps != null) {
                    try {
                        cmp.setY(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "WCo");
                if (tmps != null) {
                    try {
                        cmp.setW(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "HCo");
                if (tmps != null) {
                    try {
                        cmp.setH(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                List<String> tmL = getValueList(x, "linkedTo");
                if (tmL != null && !tmL.isEmpty()) {
                    cmp.setLinkedComponentNames(tmL);
                }

                cl.add(cmp);
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cl;
    }

    /**
     * Method parses the cool Plugin concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return states           The {@link APlugin} object
     */
    private APlugin parsePlugin(Object subject, String predicate) {
        APlugin plugin = null;
        String name;
        String description;
        String className;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";

        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                plugin = new APlugin();

                name = getValue(x, "hasName");
                if (name != null) {
                    plugin.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the Plugin of the" + x.toString());
                    return null;
                }

                className = getValue(x, "hasClass");
                if (className != null) {
                    plugin.setClassName(className);
                } else {
                    System.out.println("COOL: ClassName is not defined for the Plugin of the" + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    plugin.setDescription(description);
                }

            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return plugin;
    }

    /**
     * Method parses the cool State concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return states           List of the {@link AState} objects
     */
    private ArrayList<AState> parseStates(Object subject, String predicate) {
        AState state;
        String name;
        String description;
        List<String> compNames;
        List<AProcess> processes;


        ArrayList<AState> states = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }

                state = new AState();

                name = getValue(x, "hasName");
                if (name != null) {
                    state.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the State of the" + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    state.setDescription(description);
                }


                processes = parseProcess(x, "achievedThrough");
                if (processes != null && !processes.isEmpty()) {
                    state.setProcesses(processes);
                }

                compNames = getValueList(x, "addressedTo");
                if (compNames != null && !compNames.isEmpty()) {
                    state.setComponentNames(compNames);
                }
                states.add(state);
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return states;
    }

    /**
     * Parses Cool process concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return pl              List of {@link AProcess} objects
     */
    private List<AProcess> parseProcess(Object subject, String predicate) {
        AProcess process;
        String name;
        String before;
        String after;
        String status;
        String initiator;
        String description;
        String critical;
        String sync;
        List<AScript> scripts;
        List<APackage> sendPackages;
        List<APackage> receivePackages;
        List<AProcess> pl = new ArrayList<>();
        String tmps;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + "> <"
                + CoolConstants.COOL_CORE
                + "" + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }

                process = new AProcess();

                name = getValue(x, "hasName");
                if (name != null) {
                    process.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the Process " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    process.setDescription(description);
                }

                critical = getValue(x, "isCritical");
                if (critical != null) {
                    process.setCritical(critical);
                }

                sync = getValue(x, "isSync");
                if (sync != null) {
                    process.setSync(sync);
                }

                before = getValue(x, "before");
                if (before != null) {
                    process.setBefore(before);
                }

                after = getValue(x, "after");
                if (after != null) {
                    process.setAfter(after);
                }

                tmps = getValue(x, "hasPeriodicity");
                if (tmps != null) {
                    process.setPeriodicity(Integer.parseInt(tmps));
                }

                tmps = getValue(x, "hasDelay");
                if (tmps != null) {
                    process.setDelay(Integer.parseInt(tmps));
                }

                tmps = getValue(x, "hasTimeOut");
                // Note: time-outs are in seconds
                if (tmps != null) {
                    process.setTimeout(Integer.parseInt(tmps));
                }

                tmps = getValue(x, "hasRequestId");
                if (tmps != null) {
                    process.setRequestid(Integer.parseInt(tmps));
                }

                status = getValue(x, "status");
                if (status != null) {
                    process.setStatus(status);
                }
                initiator = getValue(x, "isInitiator");
                if (initiator != null) {
                    process.setInitiator(initiator);
                }
                scripts = parseScript(x, "runs");
                if (scripts != null && !scripts.isEmpty()) {
                    process.setScripts(scripts);
                }

                sendPackages = parsePackage(x, "sends");
                if (sendPackages != null && !sendPackages.isEmpty()) {
                    process.setSendPackages(sendPackages);
                }
                receivePackages = parsePackage(x, "receives");
                if (receivePackages != null && !receivePackages.isEmpty()) {
                    process.setReceivePackages(receivePackages);
                }

                pl.add(process);
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pl;
    }

    /**
     * Parses Cool service concept (object of the rdf statement) and
     * fills arraylist of {@link AService} objects.
     *
     * @param subject   of the rdf statement
     * @param predicate of the rdf statement
     * @return AService objects.
     */
    private List<AService> parseServices(Object subject, String predicate) {
        AService service;
        List<AService> services = new ArrayList<>();
        String name;
        String description;
        String owner;
        ARule stateMachineRule;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + "" + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }

                service = new AService();

                name = getValue(x, "hasName");
                if (name != null) {
                    service.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the Service " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    service.setDescription(description);
                }

                owner = getValue(x, "hasOwner");
                if (owner != null) {
                    service.setOwner(owner);
                }

                stateMachineRule = parseRule(x, "startsStatemachine");
                if (stateMachineRule != null) {
                    service.setStateMachineRule(stateMachineRule);
                }
                services.add(service);
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return services;
    }

    /**
     * Method parses the cool rule concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return rule             {@link ARule} object
     */
    private ARule parseRule(Object subject, String predicate) {
        ARule rule = null;
        String name;
        String description;
        String code;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + "" + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }

                rule = new ARule();

                name = getValue(x, "hasName");
                if (name != null) {
                    rule.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the rule of the" + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    rule.setDescription(description);
                }

                code = getValue(x, "hasCode");
                if (code != null) {
                    rule.setCode(code);
                } else {
                    System.out.println("COOL: Code is not defined for the rule of the" + x.toString());
                }
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rule;
    }

    /**
     * Parsing the link.
     *
     * @param subject   COOL subject
     * @param predicate COOL predicate
     * @return ALink object
     */
    private List<ALink> parseLink(Object subject, String predicate) {
        ALink link;
        String tmps;
        List<ALink> al = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }

                link = new ALink();

                tmps = getValue(x, "hasName");
                if (tmps != null) {
                    link.setName(tmps);
                } else {
                    System.out.println("COOL: Name is not defined for the linkPoint " + x.toString());
                    return null;
                }

                tmps = getValue(x, "hasDescription");
                if (tmps != null) {
                    link.setDescription(tmps);
                }

                tmps = getValue(x, "hasTransport");
                if (tmps != null) {
                    link.setTransport(tmps);
                }

                tmps = getValue(x, "sourceComponentName");
                if (tmps != null) {
                    link.setSourceComponentName(tmps);
                }

                tmps = getValue(x, "sourceHost");
                if (tmps != null) {
                    link.setSourceHost(tmps);
                }

                tmps = getValue(x, "sourcePort");
                if (tmps != null) {
                    try {
                        link.setSourcePort(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "destinationComponentName");
                if (tmps != null) {
                    link.setDestinationComponentName(tmps);
                }

                tmps = getValue(x, "destinationHost");
                if (tmps != null) {
                    link.setDestinationHost(tmps);
                }

                tmps = getValue(x, "destinationPort");
                if (tmps != null) {
                    try {
                        link.setDestinationPort(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "destinationETNumberEvents");
                if (tmps != null) {
                    try {
                        link.setDestinationETNumberEvents(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "destinationETEventSize");
                if (tmps != null) {
                    try {
                        link.setDestinationETEventSize(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "startX");
                if (tmps != null) {
                    try {
                        link.setStartX(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "startY");
                if (tmps != null) {
                    try {
                        link.setStartY(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "endX");
                if (tmps != null) {
                    try {
                        link.setEndX(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "endY");
                if (tmps != null) {
                    try {
                        link.setEndY(Double.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                al.add(link);
            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    /**
     * Method parses the cool option concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return option           {@link AOption} object
     */
    private AOption parseOption(Object subject, String predicate) {
        AOption option = null;
        String name;
        String description;
        String dataFile;
        String dataFilePrefix;
        String coda2Component;
        String autoStart;
        String startRun;
        String configFile;
        String configString;
        String tmps;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                option = new AOption();

                name = getValue(x, "hasName");
                if (name != null) {
                    option.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the option " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    option.setDescription(description);
                }

                dataFile = getValue(x, "hasDataFile");
                if (dataFile != null) {
                    option.setDataFile(dataFile);
                }

                dataFilePrefix = getValue(x, "hasDataFilePrefix");
                if (dataFilePrefix != null) {
                    option.setDataFilePrefix(dataFilePrefix);
                }

                coda2Component = getValue(x, "hasCoda2Component");
                if (coda2Component != null) {
                    option.setCoda2Component(coda2Component);
                }

                autoStart = getValue(x, "hasAutoStart");
                if (autoStart != null) {
                    option.setAutoStart(autoStart);
                }

                startRun = getValue(x, "hasStartRun");
                if (startRun != null) {
                    option.setStartRun(startRun);
                }

                configFile = getValue(x, "hasConfigFile");
                if (configFile != null) {
                    option.setConfigFile(configFile);
                }

                configString = getValue(x, "hasConfigString");
                if (configString != null) {
                    option.setConfigString(configString);
                }

                tmps = getValue(x, "hasDownloadString");
                if (tmps != null) {
                    option.setDownloadString(tmps);
                }

                tmps = getValue(x, "hasPrestartString");
                if (tmps != null) {
                    option.setPrestartString(tmps);
                }

                tmps = getValue(x, "hasGoString");
                if (tmps != null) {
                    option.setGoString(tmps);
                }

                tmps = getValue(x, "hasEndString");
                if (tmps != null) {
                    option.setEndString(tmps);
                }


                tmps = getValue(x, "hasEventLimit");
                if (tmps != null) {
                    try {
                        option.setEventLimit(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "hasDataLimit");
                if (tmps != null) {
                    try {
                        option.setDataLimit(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
            qe.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return option;
    }

    /**
     * Method parses the cool package concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return list of          {@link APackage} objects
     */
    private List<APackage> parsePackage(Object subject, String predicate) {
        APackage pk;
        String name;
        String description;
        String tmp;
        String subj;
        String type;
        String text;
        List<String> receivedTexts;
        List<AChannel> channels;

        List<APackage> al = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                pk = new APackage();

                name = getValue(x, "hasName");
                if (name != null) {
                    pk.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the package " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    pk.setDescription(description);
                }

                subj = getValue(x, "hasSendSubject");
                if (subj != null) {
                    pk.setSendSubject(subj);
                }

                type = getValue(x, "hasSendType");
                if (type != null) {
                    pk.setSendType(type);
                }

                text = getValue(x, "hasSendText");
                if (text != null) {
                    pk.setSendText(text);
                }

                subj = getValue(x, "hasReceivedSubject");
                if (subj != null) {
                    pk.setReceivedSubject(subj);
                }

                type = getValue(x, "hasReceivedType");
                if (type != null) {
                    pk.setReceivedType(type);
                }

                receivedTexts = getValueList(x, "hasReceivedText");
                if (receivedTexts != null && !receivedTexts.isEmpty()) {
                    pk.setReceivedText(receivedTexts);
                }

                tmp = getValue(x, "isForRcClient");
                if (tmp != null) {
                    pk.setForRcClient(tmp);
                }

                tmp = getValue(x, "isForNativecMsg");
                if (tmp != null) {
                    pk.setForNativecMsg(tmp);
                }

                tmp = getValue(x, "isForShellClient");
                if (tmp != null) {
                    pk.setForShellClient(tmp);
                }

                channels = parseChannel(x, "hasChannel");
                if (channels != null && !channels.isEmpty()) {
                    pk.setChannels(channels);
                }

                al.add(pk);
            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    /**
     * Method parses the cool channel concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return list of          {@link AChannel} objects
     */
    private List<AChannel> parseChannel(Object subject, String predicate) {
        AChannel ch;
        String name;
        String value;
        String minValue;
        String maxValue;
        String setValue;
        String tmps;
        String unit;
        String description;

        List<AChannel> al = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                ch = new AChannel();

                name = getValue(x, "hasName");
                if (name != null) {
                    ch.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the channel " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    ch.setDescription(description);
                }

                tmps = getValue(x, "hasValueType");
                if (tmps != null) {
                    try {
                        ch.setValueType(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                value = getValue(x, "hasValue");
                if (value != null) {
                    ch.setValue(value);
                }

                minValue = getValue(x, "hasMinValue");
                if (minValue != null) {
                    ch.setMinValue(minValue);
                }

                maxValue = getValue(x, "hasMaxValue");
                if (maxValue != null) {
                    ch.setMaxValue(maxValue);
                }

                setValue = getValue(x, "hasSetValue");
                if (setValue != null) {
                    ch.setSetValue(setValue);
                }

                unit = getValue(x, "hasUnit");
                if (unit != null) {
                    ch.setUnit(unit);
                }

                al.add(ch);
            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    /**
     * Method parses the cool script concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return list of          {@link AScript} objects
     */
    private List<AScript> parseScript(Object subject, String predicate) {
        AScript sc;
        String name;
        String description;
        String commandString;
        String sync;
        String tmp;
        List<AScript> al = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                sc = new AScript();

                name = getValue(x, "hasName");
                if (name != null) {
                    sc.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the script " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    sc.setDescription(description);
                }

                commandString = getValue(x, "hasCommandString");
                if (commandString != null) {
                    if (CoolTools.containsRTV(commandString)) {
                        sc.setCommandString(CoolTools.checkRtvs(commandString, rTVMap));
                    } else {
                        sc.setCommandString(commandString);
                    }
                }

                tmp = getValue(x, "hasExitCode");
                if (tmp != null) {
                    try {
                        sc.setExitCode(Integer.valueOf(tmp));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                sync = getValue(x, "isSynchronous");
                if (sync != null) {
                    sc.setSync(sync);
                }

                tmp = getValue(x, "hasTimeout");
                if (tmp != null) {
                    try {
                        sc.setTimeout(Integer.valueOf(tmp));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                al.add(sc);
            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    /**
     * Method parses the cool hmi concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return gui             {@link HMI} objects
     */
    private HMI parseHmi(Object subject, String predicate) {
        HMI h = null;
        String name;
        String description;
        String isWebBased;
        String tmps;
        List<APanel> panels;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                h = new HMI();

                name = getValue(x, "hasName");
                if (name != null) {
                    h.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the hmi " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    h.setDescription(description);
                }

                isWebBased = getValue(x, "isWebBased");
                if (isWebBased != null) {
                    h.setWebBased(isWebBased);
                }

                tmps = getValue(x, "hasRows");
                if (tmps != null) {
                    try {
                        h.setRowsNumber(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                tmps = getValue(x, "hasColumns");
                if (tmps != null) {
                    try {
                        h.setColumnsNumber(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                panels = parsePanel(x, "hasPanel");
                if (panels != null && !panels.isEmpty()) {
                    h.setPanels(panels);
                }

            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return h;
    }

    /**
     * Method parses the cool panel concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return list of          {@link APanel} objects
     */
    private List<APanel> parsePanel(Object subject, String predicate) {
        APanel p;
        String name;
        String description;
        String color;
        String title;
        List<AWidget> widgets;
        List<APanel> al = new ArrayList<>();
        String tmps;

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                p = new APanel();

                name = getValue(x, "hasName");
                if (name != null) {
                    p.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the panel " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    p.setDescription(description);
                }

                color = getValue(x, "hasColor");
                if (color != null) {
                    p.setColor(color);
                }

                title = getValue(x, "hasTitle");
                if (title != null) {
                    p.setTitle(title);
                }

                tmps = getValue(x, "hasNumber");
                if (tmps != null) {
                    try {
                        p.setNumber(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                widgets = parseWidget(x, "hasWidget");
                if (widgets != null && !widgets.isEmpty()) {
                    p.setWidgets(widgets);
                }

                al.add(p);
            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }

    /**
     * Method parses the cool widget concept.
     *
     * @param subject   object of the cool concept
     * @param predicate String of the cool predicate name
     * @return list of          {@link AWidget} objects
     */
    private List<AWidget> parseWidget(Object subject, String predicate) {
        AWidget w;
        String name;
        String description;
        String title;
        String tmps;
        String type;
        String subType;
        String icon;
        String editable;
        List<AService> actionServices;
        List<AProcess> actionProcesses;
        List<AState> actionStates;
        List<AWidget> al = new ArrayList<>();

        String tq = "SELECT ?x "
                + "WHERE(<"
                + subject.toString()
                + ">, <"
                + CoolConstants.COOL_CORE
                + ""
                + predicate
                + ">,?x )";
        try {
            Query query = QueryFactory.create(tq);
            QueryExecution qe = QueryExecutionFactory.create(query, gModel);

            ResultSet resultSet = qe.execSelect();

            for (; resultSet.hasNext(); ) {
                QuerySolution result = resultSet.nextSolution();
                Object x = result.getResource("x");


                if (x == null) {
                    break;
                }
                w = new AWidget();

                name = getValue(x, "hasName");
                if (name != null) {
                    w.setName(name);
                } else {
                    System.out.println("COOL: Name is not defined for the widget " + x.toString());
                    return null;
                }

                description = getValue(x, "hasDescription");
                if (description != null) {
                    w.setDescription(description);
                }

                title = getValue(x, "hasTitle");
                if (title != null) {
                    w.setTitle(title);
                }

                tmps = getValue(x, "hasNumber");
                if (tmps != null) {
                    try {
                        w.setNumber(Integer.valueOf(tmps));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                type = getValue(x, "hasType");
                if (type != null) {
                    w.setType(type);
                }

                subType = getValue(x, "hasSubType");
                if (subType != null) {
                    w.setSubType(subType);
                }

                editable = getValue(x, "isEditable");
                if (editable != null) {
                    w.setEditable(editable);
                }

                icon = getValue(x, "hasIcon");
                if (icon != null) {
                    w.setIcon(icon);
                }

                actionStates = parseStates(x, "moveToState");
                if (actionStates != null && !actionStates.isEmpty()) {
                    w.setActionStates(actionStates);
                }

                actionProcesses = parseProcess(x, "startsProcess");
                if (actionProcesses != null && !actionProcesses.isEmpty()) {
                    w.setActionProcesses(actionProcesses);
                }

                actionServices = parseServices(x, "startsService");
                if (actionServices != null && !actionServices.isEmpty()) {
                    w.setActionServices(actionServices);
                }

                al.add(w);
            }
            qe.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return al;
    }
}
