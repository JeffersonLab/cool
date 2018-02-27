
package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;

/**
 * COOL ontology concept: service.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AService extends AOntologyConcept implements Serializable {

    private String owner = CoolConstants.UDF;
    private String name = CoolConstants.UDF;
    private String author = CoolConstants.UDF;
    private String version = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String inputObjectClass = CoolConstants.UDF;
    private String outputObjectClass = CoolConstants.UDF;
    private ARule stateMachineRule;


    /**
     * Constructor.
     */
    public AService() {
        setOntology("afecs");
        // the name of the concept
        setConceptName("Service");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
        addPrimitiveSlot("hasOwner", 1, true, "String");
        addConceptSlot("startsStatemachine", 1, true, "ARule");
    }

    /**
     * Get owner of this service.
     *
     * @return String
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set owner of this service.
     *
     * @param owner String
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
     * Get author of this service.
     *
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author of this service.
     *
     * @param author String
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get service version.
     *
     * @return String
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set service version.
     *
     * @param version String
     */
    public void setVersion(String version) {
        this.version = version;
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
     * Set description.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get input object class.
     *
     * @return String
     */
    public String getInputObjectClass() {
        return inputObjectClass;
    }

    /**
     * Set input object class.
     *
     * @param inputObjectClass String
     */
    public void setInputObjectClass(String inputObjectClass) {
        this.inputObjectClass = inputObjectClass;
    }

    /**
     * Get output object class.
     *
     * @return String
     */
    public String getOutputObjectClass() {
        return outputObjectClass;
    }

    /**
     * Set ouptput object class.
     *
     * @param outputObjectClass String
     */
    public void setOutputObjectClass(String outputObjectClass) {
        this.outputObjectClass = outputObjectClass;
    }

    /**
     * Get state machine rule.
     *
     * @return ARule object
     */
    public ARule getStateMachineRule() {
        return stateMachineRule;
    }

    /**
     * Set state machine rule.
     *
     * @param stateMachineRule ARule object.
     */
    public void setStateMachineRule(ARule stateMachineRule) {
        this.stateMachineRule = stateMachineRule;
    }
}
