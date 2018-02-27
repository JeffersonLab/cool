package org.jlab.coda.cool.ontology;


import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;

/**
 * COOL ontology concept: ALink.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public class ALink extends AOntologyConcept implements Serializable {
    private String name = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String transport = "ET";
    private String sourceComponentName = CoolConstants.UDF;
    private String sourceHost = CoolConstants.UDF;
    private String destinationComponentName = CoolConstants.UDF;
    private String destinationHost = CoolConstants.UDF;
    private int sourcePort;
    private int destinationPort;
    private int destinationETNumberEvents;
    private int destinationETEventSize;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    /**
     * Constructor.
     */
    public ALink() {
        setOntology("afecs");
        setConceptName("ALink");
        addPrimitiveSlot("hasName", 1, true, "String");
        addPrimitiveSlot("hasDescription", 1, false, "String");
        addPrimitiveSlot("hasTransport", 1, false, "String");
        addPrimitiveSlot("sourceComponentName", 1, false, "String");
        addPrimitiveSlot("sourceHost", 1, false, "String");
        addPrimitiveSlot("sourcePort", 1, false, "Integer");
        addPrimitiveSlot("destinationComponentName", 1, false, "String");
        addPrimitiveSlot("destinationHost", 1, false, "String");
        addPrimitiveSlot("destinationPort", 1, false, "Integer");
        addPrimitiveSlot("destinationETNumberEvents", 1, false, "Integer");
        addPrimitiveSlot("destinationETEventSize", 1, false, "Integer");
    }

    /**
     * Set the name of the link.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the description of the link.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set link's destination component name.
     *
     * @param destinationComponentName String
     */
    public void setDestinationComponentName(String destinationComponentName) {
        this.destinationComponentName = destinationComponentName;
    }

    /**
     * Set destination ET event size. In this case destination link is CODA ET ring.
     *
     * @param destinationETEventSize int
     */
    public void setDestinationETEventSize(int destinationETEventSize) {
        this.destinationETEventSize = destinationETEventSize;
    }

    /**
     * Set destination ET number of events. In this case destination link is CODA ET ring.
     *
     * @param destinationETNumberEvents int
     */
    public void setDestinationETNumberEvents(int destinationETNumberEvents) {
        this.destinationETNumberEvents = destinationETNumberEvents;
    }

    /**
     * Set ink's destination host name.
     *
     * @param destinationHost String
     */
    public void setDestinationHost(String destinationHost) {
        this.destinationHost = destinationHost;
    }

    /**
     * Set link's destination port number.
     *
     * @param destinationPort int
     */
    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    /**
     * Set link's source component name.
     *
     * @param sourceComponentName String
     */
    public void setSourceComponentName(String sourceComponentName) {
        this.sourceComponentName = sourceComponentName;
    }

    /**
     * Set link's source host name.
     *
     * @param sourceHost String
     */
    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    /**
     * Set link's source port number.
     *
     * @param sourcePort int
     */
    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    /**
     * Set link's transport.
     *
     * @param transport String
     */
    public void setTransport(String transport) {
        this.transport = transport;
    }

    /**
     * Set link visualization X coordinate of the end point.
     *
     * @param endX double
     */
    public void setEndX(double endX) {
        this.endX = endX;
    }

    /**
     * Set link visualization Y coordinate of the end point.
     *
     * @param endY double
     */
    public void setEndY(double endY) {
        this.endY = endY;
    }

    /**
     * Set link visualization X coordinate of the start point.
     *
     * @param startX double
     */
    public void setStartX(double startX) {
        this.startX = startX;
    }

    /**
     * Set link visualization Y coordinate of the start point.
     *
     * @param startY double
     */
    public void setStartY(double startY) {
        this.startY = startY;
    }

    /**
     * Get link name.
     *
     * @return String
     */
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
     * Get link's transport (ET, cMsg, xMsg, etc.).
     *
     * @return String
     */
    public String getTransport() {
        return transport;
    }

    /**
     * Get link's source component name.
     *
     * @return String
     */
    public String getSourceComponentName() {
        return sourceComponentName;
    }

    /**
     * Get link's source component host name.
     *
     * @return String
     */
    public String getSourceHost() {
        return sourceHost;
    }

    /**
     * Get link's source port number.
     *
     * @return int
     */
    public int getSourcePort() {
        return sourcePort;
    }

    /**
     * Get link's destination component name.
     *
     * @return String
     */
    public String getDestinationComponentName() {
        return destinationComponentName;
    }

    /**
     * Get destination component host name.
     *
     * @return String
     */
    public String getDestinationHost() {
        return destinationHost;
    }

    /**
     * Get destination component port number.
     *
     * @return int
     */
    public int getDestinationPort() {
        return destinationPort;
    }

    /**
     * Get destination ET number of events. In this case source component is linked to ET ring.
     *
     * @return int
     */
    public int getDestinationETNumberEvents() {
        return destinationETNumberEvents;
    }

    /**
     * Get destination ET event size. In this case source component is linked to ET ring.
     *
     * @return int
     */
    public int getDestinationETEventSize() {
        return destinationETEventSize;
    }
}
