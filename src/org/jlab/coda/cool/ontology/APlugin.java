
package org.jlab.coda.cool.ontology;

import java.io.Serializable;

/**
 * COOL ontology concept: plugin.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class APlugin extends AOntologyConcept implements Serializable {
    private String name;
    private String description;
    private String className;

    /**
     * Constructor.
     */
    public APlugin() {
        setOntology("afecs");
        setConceptName("APlugin");
        addPrimitiveSlot("hasName", 1, true, "String");
        addPrimitiveSlot("hasDescription", 1, false, "String");
        addPrimitiveSlot("hasClass", 1, true, "String");
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
     * Get plugin class name.
     *
     * @return String
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set plugin class name.
     *
     * @param className String
     */
    public void setClassName(String className) {
        this.className = className;
    }
}
