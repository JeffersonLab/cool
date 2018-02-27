
package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology Package concept.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class APackage extends AOntologyConcept implements Serializable {
    private String name;
    private String description = CoolConstants.UDF;
    private String isForRcClient = CoolConstants.UDF;
    private String isForNativecMsg = CoolConstants.UDF;
    private String isForShellClient = CoolConstants.UDF;
    private String sendSubject = CoolConstants.UDF;
    private String sendType = CoolConstants.UDF;
    private String sendText = CoolConstants.UDF;
    private String receivedSubject = CoolConstants.UDF;
    private String receivedType = CoolConstants.UDF;
    private List<String> receivedText = new ArrayList<>();
    private List<AChannel> channels = new ArrayList<>();

    /**
     * Constructor.
     */
    public APackage() {
        setOntology("afecs");
        // the name of the concept
        setConceptName("Package");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("isForRcClient", 1, true, "String");
        addPrimitiveSlot("isForNativecMsg", 1, true, "String");
        addPrimitiveSlot("isForShellClient", 1, true, "String");
        addPrimitiveSlot("hasSendSubject", 1, true, "String");
        addPrimitiveSlot("hasSendType", 1, true, "String");
        addPrimitiveSlot("hasSendText", 1, true, "String");
        addPrimitiveSlot("hasReceivedSubject", 1, true, "String");
        addPrimitiveSlot("hasReceivedType", 1, true, "String");
        addPrimitiveSlot("hasReceivedText", 2, true, "String");
        addPrimitiveSlot("definesState", 1, true, "String");
        addConceptSlot("hasChannel", 2, true, "AChannel");
    }


    /**
     * Get name.
     *
     * @return String
     */
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
     * Define if this package transport being rc cMsg.
     *
     * @return String
     */
    public String getForRcClient() {
        return isForRcClient;
    }

    /**
     * Set package transport to be rc cMsg.
     *
     * @param forRcClient String
     */
    public void setForRcClient(String forRcClient) {
        this.isForRcClient = forRcClient;
    }

    /**
     * Define if package transport is native cMsg.
     *
     * @return String
     */
    public String getForNativecMsg() {
        return isForNativecMsg;
    }

    /**
     * Set package transport to be native cMsg.
     *
     * @param forNativecMsg String
     */
    public void setForNativecMsg(String forNativecMsg) {
        isForNativecMsg = forNativecMsg;
    }

    /**
     * Set package transport to be a shel execution.
     *
     * @param forShellClient String
     */
    public void setForShellClient(String forShellClient) {
        isForShellClient = forShellClient;
    }

    /**
     * Define if package transport is shell execution.
     *
     * @return String
     */
    public String getIsForShellClient() {
        return isForShellClient;
    }

    /**
     * Get package channels.
     *
     * @return List of AChannel objects
     */
    public List<AChannel> getChannels() {
        return channels;
    }

    /**
     * Set channeld of this package.
     *
     * @param channels List of AChannel objects
     */
    public void setChannels(List<AChannel> channels) {
        this.channels = channels;
    }

    /**
     * Add channel.
     *
     * @param c AChannel object
     */
    public void addChannel(AChannel c) {
        this.channels.add(c);
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
     * Get package send subject.
     *
     * @return String
     */
    public String getSendSubject() {
        return sendSubject;
    }

    /**
     * Set package send subject.
     *
     * @param sendSubject String
     */
    public void setSendSubject(String sendSubject) {
        this.sendSubject = sendSubject;
    }

    /**
     * Get package pub-sub type.
     *
     * @return String
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * Set package pub-sub type.
     *
     * @param sendType String
     */
    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    /**
     * Get package text.
     *
     * @return String
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * Set package text.
     *
     * @param sendText String
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    /**
     * Get package received pub-sub subject.
     *
     * @return String
     */
    public String getReceivedSubject() {
        return receivedSubject;
    }

    /**
     * Set package received pub-sub subject.
     *
     * @param receivedSubject String
     */
    public void setReceivedSubject(String receivedSubject) {
        this.receivedSubject = receivedSubject;
    }

    /**
     * get received package pub-sub type.
     *
     * @return String
     */
    public String getReceivedType() {
        return receivedType;
    }

    /**
     * Set received package pub-sub type.
     *
     * @param receivedType String
     */
    public void setReceivedType(String receivedType) {
        this.receivedType = receivedType;
    }

    /**
     * Get received package text.
     *
     * @return List of Strings
     */
    public List<String> getReceivedText() {
        return receivedText;
    }

    /**
     * Set received package text strings.
     *
     * @param receivedText List of String objects
     */
    public void setReceivedText(List<String> receivedText) {
        this.receivedText = receivedText;
    }

    /**
     * Get description.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }
}
