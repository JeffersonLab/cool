/*
 *   Copyright (c) 2017.  Jefferson Lab (JLab). All rights reserved. Permission
 *   to use, copy, modify, and distribute  this software and its documentation for
 *   governmental use, educational, research, and not-for-profit purposes, without
 *   fee and without a signed licensing agreement.
 *
 *   IN NO EVENT SHALL JLAB BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL
 *   INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING
 *   OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF JLAB HAS
 *   BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   JLAB SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE. THE CLARA SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 *   PROVIDED HEREUNDER IS PROVIDED "AS IS". JLAB HAS NO OBLIGATION TO PROVIDE
 *   MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 *   This software was developed under the United States Government license.
 *   For more information contact author at gurjyan@jlab.org
 *   Department of Experimental Nuclear Physics, Jefferson Lab.
 */

package org.jlab.coda.cool.ontology;


import org.jlab.coda.cool.util.AConstants;

import java.io.Serializable;
import java.util.ArrayList;

public class AComponent extends AOntologyConcept implements Serializable, Comparable<AComponent> {
    // rdf/cool defined fields
    private String                name                 = AConstants.udf;
    private String                classPath            = AConstants.udf;
    private String                className            = AConstants.udf;
    private int                   id;
    private String                description          = AConstants.udf;
    private String                type                 = AConstants.udf;
    private String                code                 = AConstants.udf;
    private int                   priority             = 0;
    private String                session              = AConstants.udf;
    private String                runType              = AConstants.udf;
    private AOption               option;
    private String                coda2Component       = AConstants.udf;
    private String                codaComponent        = AConstants.seton;
    private ArrayList<ALink>      link                 = new ArrayList<>();
    private int                   reportingInterval    = 1000;
    private ArrayList<AState>     states               = new ArrayList<>();
    private ArrayList<AProcess>   processes            = new ArrayList<>();
    private ArrayList<AService>   services             = new ArrayList<>();
    private HMI                   gui;
    private APlugin               plugin;
    private String                userConfig           = AConstants.udf;



    public AComponent(){
        setOntology(     "afecs");
        setConceptName("Component");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasClassPath", 1, false, "String");
        addPrimitiveSlot("hasClassName", 1, false, "String");
        addConceptSlot("hasID", 1, false, "Integer");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("hasType",                 1, true, "String");
        addPrimitiveSlot("hasCode",                 1, true, "String");
        addPrimitiveSlot("hasPriority",             1, true, "Integer");
        addPrimitiveSlot("hasSession", 1, true, "String");
        addPrimitiveSlot("hasRunType", 1, true, "String");
        addConceptSlot("hasOption", 1, true, "Option");
        addPrimitiveSlot("representsCoda2Component", 1, true, "String");
        addPrimitiveSlot("isCodaComponent", 1, true, "String");
        addPrimitiveSlot("hasReportingInterval", 1, true, "Integer");
        addConceptSlot("hasState", 2, true, "AState");
        addConceptSlot(  "hasProcess",              2, true, "AProcess");
        addConceptSlot(  "providesService",         2, true, "AService");
        addConceptSlot(  "hasHmi",                  1, true, "HMI");
        addConceptSlot(  "hasCommunicationPlugin",  1, true, "APlugin");
        addConceptSlot(  "XCo",                     1, true, "Double");
        addConceptSlot(  "YCo",                     1, true, "Double");
        addConceptSlot(  "WCo",                     1, true, "Double");
        addConceptSlot(  "HCo",                     1, true, "Double");
        addConceptSlot(  "linkedTo", 2, true, "String");
        addConceptSlot(  "hasUserConfig", 2, true, "String");
        addConceptSlot(  "usesLink", 2, true, "ALink");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getReportingInterval() {
        return reportingInterval;
    }

    public void setReportingInterval(int reportingInterval) {
        this.reportingInterval = reportingInterval;
    }

    public ArrayList<AState> getStates() {
        return states;
    }

    public void setStates(ArrayList<AState> states) {
        this.states = states;
    }

    public void addState(AState s){
        this.states.add(s);
    }

    public String[] getStateNames(){
        String st[] = new String[states.size()];
        int i=0;
        for(AState s:states){
            st[i] = s.getName();
            i++;
        }
        return  st;
    }


    public ArrayList<AProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<AProcess> processes) {
        this.processes = processes;
    }

    public String[] getProcessNames(){
        String st[] = new String[processes.size()];
        int i=0;
        for(AProcess p:processes){
            st[i] = p.getName();
            i++;
        }
        return  st;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AOption getOption() {
        return option;
    }

    public void setOption(AOption option) {
        this.option = option;
    }

    public String getCoda2Component() {
        return coda2Component;
    }

    public void setCoda2Component(String coda2Component) {
        this.coda2Component = coda2Component;
    }

    public String getCodaComponent() {
        return codaComponent;
    }

    public void setCodaComponent(String codaComponent) {
        this.codaComponent = codaComponent;
    }

    public void setGui(HMI gui) {
        this.gui = gui;
    }


    public APlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(APlugin plugin) {
        this.plugin = plugin;
    }

    public ArrayList<AService> getServices() {
        return services;
    }

    public void setServices(ArrayList<AService> services) {
        this.services = services;
    }

    public void addService(AService service) {
        this.services.add(service);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLinks(ArrayList<ALink> link) {
        this.link = link;
    }


    public String getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(String userConfig) {
        this.userConfig = userConfig;
    }


    @Override
    public String toString() {
        String s =  "\n Agent_Data " +
                "  \n-------------" +
                "  \nname              = " + name +
                ", \ntype              = " + type +
                ", \nsession           = " + session +
                ", \nrunType           = " + runType +
                ", \nreportingInterval = "  + reportingInterval +
                ", \npriority          = "  + priority +
                ", \nuserConfig        = "  + userConfig +
                "\n";
        StringBuilder sb = new StringBuilder(s);
        for(ALink link: link){
            sb.append("source comp = "+link.getSourceComponentName()).
                    append(" destination comp = "+link.getDestinationComponentName()).
                    append("\n");
        }
        return sb.toString();
    }

    @Override
    /**
     * <p>
     *     Ascending ordering in case we sort arrays of AComponents
     *     For descending ordering simply do:
     *     comparedPriority-priority
     * </p>
     */
    public int compareTo(AComponent comp) {
        int comparedPriority = comp.getPriority();
        return priority - comparedPriority;
    }
}
