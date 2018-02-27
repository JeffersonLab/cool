
package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: process.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AProcess extends AOntologyConcept implements Serializable {
    private String name;
    private String before = CoolConstants.UDF;
    private String after = CoolConstants.UDF;
    private String status = CoolConstants.UDF;
    private String initiator = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String critical = CoolConstants.FALSE;
    private String sync = CoolConstants.FALSE;
    private int periodicity;
    private int delay = 3;
    private int timeout = CoolConstants.TIMEOUT;
    private int requestid;
    private List<AScript> scripts = new ArrayList<>();
    private List<APackage> sendPackages = new ArrayList<>();
    private List<APackage> receivePackages = new ArrayList<>();

    /**
     * Constructor.
     */
    public AProcess() {
        setOntology("afecs");
        // the name of the concept
        setConceptName("Process");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("isCritical", 1, true, "String");
        addPrimitiveSlot("before", 1, true, "String");
        addPrimitiveSlot("after", 1, true, "String");
        addPrimitiveSlot("hasIpc", 1, true, "String");
        addPrimitiveSlot("hasPeriodicity", 1, true, "Integer");
        addPrimitiveSlot("hasDelay", 1, true, "Integer");
        addPrimitiveSlot("hasTimeOut", 1, true, "Integer");
        addPrimitiveSlot("hasRequestId", 1, true, "Integer");
        addPrimitiveSlot("status", 1, true, "String");
        addPrimitiveSlot("isInitiator", 1, true, "String");
        addConceptSlot("runs", 2, true, "AScript");
        addConceptSlot("sends", 2, true, "APackage");
        addConceptSlot("receives", 2, true, "APackage");
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * See if process needs to be executed before a state machine transition.
     *
     * @return String
     */
    public String getBefore() {
        return before;
    }

    /**
     * Set the process to be executed before a state machine transition.
     *
     * @param before String
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * See if process needs to be executed after a state machine transition.
     *
     * @return String
     */
    public String getAfter() {
        return after;
    }

    /**
     * Set the process to be executed after a state machine transition.
     *
     * @param after String
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * Get process execution status.
     *
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set process execution status.
     *
     * @param status String
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * See if this is an initiator process.
     *
     * @return String
     */
    public String getInitiator() {
        return initiator;
    }

    /**
     * Set this as a initiator process.
     *
     * @param initiator String
     */
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    /**
     * Get process description.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set process description.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * See if this is a critical process.
     *
     * @return String
     */
    public String getCritical() {
        return critical;
    }

    /**
     * Set this is a critical process.
     *
     * @param critical String
     */
    public void setCritical(String critical) {
        this.critical = critical;
    }

    /**
     * See if this is a sync process.
     *
     * @return String
     */
    public String getSync() {
        return sync;
    }

    /**
     * Set this as a sync process.
     *
     * @param sync String
     */
    public void setSync(String sync) {
        this.sync = sync;
    }

    /**
     * Get periodicity of this process.
     *
     * @return int
     */
    public int getPeriodicity() {
        return periodicity;
    }

    /**
     * Set periodicity of this process.
     *
     * @param periodicity int
     */
    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

    /**
     * Get delay factor of this process.
     *
     * @return int
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Set delay factor of this process.
     *
     * @param delay int
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Get timeout for this process.
     *
     * @return int
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Set timeout for this process.
     *
     * @param timeout int
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Get process request ID.
     *
     * @return int
     */
    public int getRequestid() {
        return requestid;
    }

    /**
     * Set process request ID.
     *
     * @param requestid int
     */
    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    /**
     * Get list of external scripts attached to this process.
     *
     * @return List of AScript objects
     */
    public List<AScript> getScripts() {
        return scripts;
    }

    /**
     * Attach scripts to this process.
     *
     * @param scripts Lsit of AScript objects
     */
    public void setScripts(List<AScript> scripts) {
        this.scripts = scripts;
    }

    /**
     * Add a script to the process.
     *
     * @param script AScript object
     */
    public void addScript(AScript script) {
        scripts.add(script);
    }

    /**
     * Get list of send packages (pub-sub) of process.
     *
     * @return List of APackage objects
     */
    public List<APackage> getSendPackages() {
        return sendPackages;
    }

    /**
     * Set the list of send packages (pub-sub) of this process.
     *
     * @param sendPackages List of APackage objects
     */
    public void setSendPackages(List<APackage> sendPackages) {
        this.sendPackages = sendPackages;
    }

    /**
     * Add send package ( pub-sub) to the process.
     *
     * @param p APackage object
     */
    public void addSendPackage(APackage p) {
        sendPackages.add(p);
    }

    /**
     * Get list of received packages (pub-sub) of process.
     *
     * @return List of APackage objects
     */
    public List<APackage> getReceivePackages() {
        return receivePackages;
    }

    /**
     * Set the list of received packages (pub-sub) of this process.
     *
     * @param receivePackages List of APackage objects
     */
    public void setReceivePackages(List<APackage> receivePackages) {
        this.receivePackages = receivePackages;
    }

    /**
     * Add receive package ( pub-sub) to the process.
     *
     * @param p APackage object
     */
    public void addReceivePackage(APackage p) {
        receivePackages.add(p);
    }

}
