
package org.jlab.coda.cool.ontology;


import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;

/**
 * COOL ontology Option concept.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AOption extends AOntologyConcept implements Serializable {

    private String name = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String dataFile = CoolConstants.UDF;
    private String dataFilePrefix = CoolConstants.UDF;
    private String coda2Component = CoolConstants.UDF;
    private String autoStart = CoolConstants.UDF;
    private String startRun = CoolConstants.UDF;
    private String configFile = CoolConstants.UDF;
    private String configString = CoolConstants.UDF;
    private String downloadString = CoolConstants.UDF;
    private String prestartString = CoolConstants.UDF;
    private String goString = CoolConstants.UDF;
    private String endString = CoolConstants.UDF;
    private int dataLimit;
    private int eventLimit;

    /**
     * Constructor.
     */
    public AOption() {
        setOntology("afecs");
        setConceptName("Option");

        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasDataFile", 1, true, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("hasDataFilePrefix", 1, true, "String");
        addPrimitiveSlot("hasCoda2Component", 1, true, "String");
        addPrimitiveSlot("hasAutoStart", 1, true, "String");
        addPrimitiveSlot("hasStartRun", 1, true, "String");
        addPrimitiveSlot("hasConfigFile", 1, true, "String");
        addPrimitiveSlot("hasDataLimit", 1, true, "Integer");
        addPrimitiveSlot("hasEventLimit", 1, true, "Integer");
        addPrimitiveSlot("hasConfigFile", 1, true, "String");
        addPrimitiveSlot("hasConfigString", 1, true, "String");
        addPrimitiveSlot("hasDownloadString", 1, true, "String");
        addPrimitiveSlot("hasPrestartString", 1, true, "String");
        addPrimitiveSlot("hasGoString", 1, true, "String");
        addPrimitiveSlot("hasEndString", 1, true, "String");
    }


    /**
     * Set option concept name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set option's data file.
     *
     * @param dataFile String
     */
    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Set option's data file prefix.
     *
     * @param dataFilePrefix String
     */
    public void setDataFilePrefix(String dataFilePrefix) {
        this.dataFilePrefix = dataFilePrefix;
    }

    /**
     * Set slot auto-start.
     *
     * @param autoStart String
     */
    public void setAutoStart(String autoStart) {
        this.autoStart = autoStart;
    }

    /**
     * Set slot start-run.
     *
     * @param startRun String
     */
    public void setStartRun(String startRun) {
        this.startRun = startRun;
    }

    /**
     * Get config file.
     *
     * @return String
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * Set config file.
     *
     * @param configFile String
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * SGet data limit.
     *
     * @return int
     */
    public int getDataLimit() {
        return dataLimit;
    }

    /**
     * Set data limit.
     *
     * @param dataLimit int
     */
    public void setDataLimit(int dataLimit) {
        this.dataLimit = dataLimit;
    }

    /**
     * Get event limit.
     *
     * @return int
     */
    public int getEventLimit() {
        return eventLimit;
    }

    /**
     * Set event limit.
     *
     * @param eventLimit int
     */
    public void setEventLimit(int eventLimit) {
        this.eventLimit = eventLimit;
    }

    /**
     * Set description.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set coda2 component slot.
     *
     * @param coda2Component String
     */
    public void setCoda2Component(String coda2Component) {
        this.coda2Component = coda2Component;
    }

    /**
     * Get config string.
     *
     * @return String
     */
    public String getConfigString() {
        return configString;
    }

    /**
     * Set config string.
     *
     * @param configString String
     */
    public void setConfigString(String configString) {
        this.configString = configString;
    }

    /**
     * Get download string.
     *
     * @return String
     */
    public String getDownloadString() {
        return downloadString;
    }

    /**
     * Set download string.
     *
     * @param downloadString String
     */
    public void setDownloadString(String downloadString) {
        this.downloadString = downloadString;
    }

    /**
     * Get end string.
     *
     * @return String
     */
    public String getEndString() {
        return endString;
    }

    /**
     * Set end string.
     *
     * @param endString String
     */
    public void setEndString(String endString) {
        this.endString = endString;
    }

    /**
     * Get go string.
     *
     * @return String
     */
    public String getGoString() {
        return goString;
    }

    /**
     * Set go string.
     *
     * @param goString String
     */
    public void setGoString(String goString) {
        this.goString = goString;
    }

    /**
     * Get prestart string.
     *
     * @return String
     */
    public String getPrestartString() {
        return prestartString;
    }

    /**
     * Set prestart string.
     *
     * @param prestartString String
     */
    public void setPrestartString(String prestartString) {
        this.prestartString = prestartString;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Get description.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get data file.
     *
     * @return String
     */
    public String getDataFile() {
        return dataFile;
    }

    /**
     * Get data file prefix.
     *
     * @return String
     */
    public String getDataFilePrefix() {
        return dataFilePrefix;
    }

    /**
     * See if coda 2 component os requested.
     *
     * @return String
     */
    public String getCoda2Component() {
        return coda2Component;
    }

    /**
     * Get auto start.
     *
     * @return String
     */
    public String getAutoStart() {
        return autoStart;
    }

    /**
     * Get start run.
     *
     * @return String
     */
    public String getStartRun() {
        return startRun;
    }
}

