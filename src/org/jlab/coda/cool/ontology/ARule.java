
package org.jlab.coda.cool.ontology;

import java.io.Serializable;

/**
 * COOL ontology concept: rule.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class ARule extends AOntologyConcept implements Serializable {

    private String name;
    private String description;
    private String code;

    /**
     * Constructor.
     */
    public ARule() {
        setOntology("afecs");
        // the name of the concept
        setConceptName("Rule");
        // slot name
        addPrimitiveSlot("hasName", 1, false, "String");
        // slot endwhile
        addPrimitiveSlot("hasCode", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
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
     * get description.
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
     * Get code.
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code.
     *
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ARule{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", code='" + code + '\''
                + '}';
    }
}
