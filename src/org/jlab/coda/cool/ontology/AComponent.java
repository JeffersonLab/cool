package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: Component.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public class AComponent extends AOntologyConcept implements Serializable, Comparable<AComponent> {
    private int id;
    private int priority;
    private int reportingInterval = 1000;
    private int eventLimit;
    private int streamCount;
    private int streamId;
    private double x;
    private double y;
    private double w;
    private double h;
    private String name = CoolConstants.UDF;
    private String classPath = CoolConstants.UDF;
    private String className = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String type = CoolConstants.UDF;
    private String code = CoolConstants.UDF;
    private String session = CoolConstants.UDF;
    private String runType = CoolConstants.UDF;
    private String coda2Component = CoolConstants.UDF;
    private String codaComponent = CoolConstants.TRUE;
    private String userConfig = CoolConstants.UDF;
    private String host = CoolConstants.UDF;
    private AOption option;
    private HMI gui;
    private APlugin plugin;
    private List<ALink> link = new ArrayList<>();
    private List<AState> states = new ArrayList<>();
    private List<AProcess> processes = new ArrayList<>();
    private List<AService> services = new ArrayList<>();
    private List<String> defaultOptionsDir = new ArrayList<>();
    private List<String> linkedComponentNames = new ArrayList<>();


    /**
     * Constructor.
     */
    public AComponent() {
        setOntology("afecs");
        setConceptName("Component");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasClassPath", 1, false, "String");
        addPrimitiveSlot("hasClassName", 1, false, "String");
        addConceptSlot("hasID", 1, false, "Integer");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("hasType", 1, true, "String");
        addPrimitiveSlot("hasCode", 1, true, "String");
        addPrimitiveSlot("hasPriority", 1, true, "Integer");
        addPrimitiveSlot("hasSession", 1, true, "String");
        addPrimitiveSlot("hasRunType", 1, true, "String");
        addConceptSlot("hasOption", 1, true, "Option");
        addPrimitiveSlot("representsCoda2Component", 1, true, "String");
        addPrimitiveSlot("isCodaComponent", 1, true, "String");
        addPrimitiveSlot("hasReportingInterval", 1, true, "Integer");
        addConceptSlot("hasState", 2, true, "AState");
        addConceptSlot("hasProcess", 2, true, "AProcess");
        addConceptSlot("providesService", 2, true, "AService");
        addConceptSlot("hasHmi", 1, true, "HMI");
        addConceptSlot("hasCommunicationPlugin", 1, true, "APlugin");
        addConceptSlot("XCo", 1, true, "Double");
        addConceptSlot("YCo", 1, true, "Double");
        addConceptSlot("WCo", 1, true, "Double");
        addConceptSlot("HCo", 1, true, "Double");
        addConceptSlot("linkedTo", 2, true, "String");
        addConceptSlot("hasUserConfig", 2, true, "String");
        addConceptSlot("usesLink", 2, true, "ALink");
    }

    /**
     * Get the name of the component.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the component.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get component class path.
     *
     * @return String
     */
    public String getClassPath() {
        return classPath;
    }

    /**
     * Set component class path.
     *
     * @param classPath String
     */
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    /**
     * Get component class name.
     *
     * @return String
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set component class name.
     *
     * @param className String
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Get component type.
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Set component type.
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set cod associated with this component.
     *
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Get component session.
     *
     * @return String
     */
    public String getSession() {
        return session;
    }

    /**
     * Set component session.
     *
     * @param session String
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Get run-type.
     *
     * @return String
     */
    public String getRunType() {
        return runType;
    }

    /**
     * Set run-type.
     *
     * @param runType String
     */
    public void setRunType(String runType) {
        this.runType = runType;
    }

    /**
     * Get control state machine priority of the component.
     *
     * @return int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Set control state machine priority of the component.
     *
     * @param priority int
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Get reporting interval.
     *
     * @return int
     */
    public int getReportingInterval() {
        return reportingInterval;
    }

    /**
     * Set reporting interval.
     *
     * @param reportingInterval int
     */
    public void setReportingInterval(int reportingInterval) {
        this.reportingInterval = reportingInterval;
    }

    /**
     * Get all defined states ofg this component.
     *
     * @return ArrayList of AState objects
     */
    public List<AState> getStates() {
        return states;
    }

    /**
     * Set component states.
     *
     * @param states ArrayList of AState objects
     */
    public void setStates(List<AState> states) {
        this.states = states;
    }

    /**
     * Define and add a state to this component.
     *
     * @param s AState object
     */
    public void addState(AState s) {
        this.states.add(s);
    }

    /**
     * Get the names of all defined states for this component.
     *
     * @return String array
     */
    public String[] getStateNames() {
        List<String> st = new ArrayList<>();
        for (AState s : states) {
            st.add(s.getName());
        }
        return st.toArray(new String[st.size()]);
    }


    /**
     * Get this component attached process list.
     *
     * @return ArrayList of AProcess objects
     */
    public List<AProcess> getProcesses() {
        return processes;
    }

    /**
     * Set component attached process list.
     *
     * @param processes ArrayList of AProcess objects
     */
    public void setProcesses(List<AProcess> processes) {
        this.processes = processes;
    }

    /**
     * Get attached process names.
     *
     * @return String array
     */
    public String[] getProcessNames() {
        List<String> st = new ArrayList<>();
        for (AProcess p : processes) {
            st.add(p.getName());
        }
        return st.toArray(new String[st.size()]);
    }


    /**
     * Get description of this component.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set component description.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get option.
     *
     * @return AOption object
     */
    public AOption getOption() {
        return option;
    }

    /**
     * Set option.
     *
     * @param option AOption object
     */
    public void setOption(AOption option) {
        this.option = option;
    }


    /**
     * Get this component as a coda2Component.
     *
     * @return String. If it is equal to CoolConstants.UDF then it is not a coda2 component
     */
    public String getCoda2Component() {
        return coda2Component;
    }

    /**
     * Set this component as a coda2component.
     *
     * @param coda2Component String
     */
    public void setCoda2Component(String coda2Component) {
        this.coda2Component = coda2Component;
    }

    /**
     * Check to see if this is a coda component (coda-3 and above).
     *
     * @return If returned string is equal to CoolConstants.UDF then it is not a coda component
     */
    public String getCodaComponent() {
        return codaComponent;
    }

    /**
     * Set component as a coda component.
     *
     * @param codaComponent String
     */
    public void setCodaComponent(String codaComponent) {
        this.codaComponent = codaComponent;
    }

    /**
     * Set GUI for this component.
     *
     * @param gui HMI object
     */
    public void setGui(HMI gui) {
        this.gui = gui;
    }


    /**
     * Get component communication plugin.
     *
     * @return APlugin object
     */
    public APlugin getPlugin() {
        return plugin;
    }

    /**
     * Set communication plugin for this component.
     *
     * @param plugin APlugin object
     */
    public void setPlugin(APlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Get supported services.
     *
     * @return ArrayList of AService objects
     */
    public List<AService> getServices() {
        return services;
    }

    /**
     * Set services for this component.
     *
     * @param services ArrayList of AService objects
     */
    public void setServices(List<AService> services) {
        this.services = services;
    }

    /**
     * Add a service.
     *
     * @param service AService object
     */
    public void addService(AService service) {
        this.services.add(service);
    }


    /**
     * Get component ID.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Set component ID.
     *
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define links for this component.
     *
     * @param link ArrayList of ALink objects
     */
    public void setLinks(List<ALink> link) {
        this.link = link;
    }


    /**
     * Get user define config for this component.
     *
     * @return String
     */
    public String getUserConfig() {
        return userConfig;
    }

    /**
     * Define a config for this component.
     *
     * @param userConfig String
     */
    public void setUserConfig(String userConfig) {
        this.userConfig = userConfig;
    }

    /**
     * Get event limit for this component. Note: CODA control specific.
     *
     * @return int
     */
    public int getEventLimit() {
        return eventLimit;
    }

    /**
     * Set event limit for this component. Note: CODA control specific.
     *
     * @param eventLimit int
     */
    public void setEventLimit(int eventLimit) {
        this.eventLimit = eventLimit;
    }

    /**
     * Get number of IO streams. Note: CODA control system supervisor component specific.
     *
     * @return int
     */
    public int getStreamCount() {
        return streamCount;
    }

    /**
     * Set number of IO streams. Note: CODA control system supervisor component specific.
     *
     * @param streamCount int
     */
    public void setStreamCount(int streamCount) {
        this.streamCount = streamCount;
    }

    /**
     * Get stream ID, in case this component is an IO component.
     *
     * @return int
     */
    public int getStreamId() {
        return streamId;
    }

    /**
     * Set stream ID, in case this component is an IO component.
     *
     * @param streamId int
     */
    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    /**
     * Get component attached code. ( e.g. readout list)
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get host where this component will run.
     *
     * @return String
     */
    public String getHost() {
        return host;
    }

    /**
     * Set host where this component will run.
     *
     * @param host String
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get default dir where component specific option files will be located (e.g. emu xml files).
     *
     * @return ArrayList of strings
     */
    public List<String> getDefaultOptionsDir() {
        return defaultOptionsDir;
    }

    /**
     * Add default dir where component specific option files will be located (e.g. emu xml files).
     *
     * @param d String
     */
    public void addDefaultOptionDir(String d) {
        defaultOptionsDir.add(d);
    }

    /**
     * Get X coordinate of this component's location on the control system visualization graph.
     *
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * Set X coordinate of this component's location on the control system visualization graph.
     *
     * @param x double
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get Y coordinate of this component's location on the control system visualization graph.
     *
     * @return double
     */
    public double getY() {
        return y;
    }

    /**
     * Set Y coordinate of this component's location on the control system visualization graph.
     *
     * @param y double
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get width of this component's location on the control system visualization graph.
     *
     * @return double
     */
    public double getW() {
        return w;
    }

    /**
     * Set width of this component's location on the control system visualization graph.
     *
     * @param w double
     */
    public void setW(double w) {
        this.w = w;
    }

    /**
     * Get height of this component's location on the control system visualization graph.
     *
     * @return double
     */
    public double getH() {
        return h;
    }

    /**
     * Set height of this component's location on the control system visualization graph.
     *
     * @param h double
     */
    public void setH(double h) {
        this.h = h;
    }

    /**
     * Get linked components names.
     *
     * @return ArrayList of Strings
     */
    public List<String> getLinkedComponentNames() {
        return linkedComponentNames;
    }

    /**
     * Set linked components names.
     *
     * @param linkedComponentNames ArrayList of String objects
     */
    public void setLinkedComponentNames(List<String> linkedComponentNames) {
        this.linkedComponentNames = linkedComponentNames;
    }

    @Override
    public String toString() {
        String s = "\n Agent_Data "
                +
                "  \n-------------"
                +
                "  \nname              = " + name
                +
                ", \ntype              = " + type
                +
                ", \nsession           = " + session
                +
                ", \nrunType           = " + runType
                +
                ", \nreportingInterval = " + reportingInterval
                +
                ", \npriority          = " + priority
                +
                ", \nuserConfig        = " + userConfig
                +
                "\n";
        StringBuilder sb = new StringBuilder(s);
        for (ALink link : link) {
            sb.append("source comp = ");
            sb.append(link.getSourceComponentName());
            sb.append(" destination comp = ");
            sb.append(link.getDestinationComponentName());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Ascending ordering in case we sort arrays of AComponents.
     * For descending ordering simply do: comparedPriority-priority.
     */
    @Override
    public int compareTo(AComponent comp) {
        int comparedPriority = comp.getPriority();
        return priority - comparedPriority;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AComponent) {
            return compareTo((AComponent) obj) == 0;
        } else {
            return false;
        }
    }
}
