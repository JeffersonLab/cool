
package org.jlab.coda.cool.ontology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: state.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AState extends AOntologyConcept implements Serializable {
    private String name;
    private String description;
    private List<String> componentNames = new ArrayList<>();
    private List<AProcess> processes = new ArrayList<>();

    /**
     * Constructor.
     */
    public AState() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("State");
        //slot hasName
        addPrimitiveSlot("hasName", 1, false, "String");
        //slot hasDescription
        addPrimitiveSlot("hasDescription", 1, true, "String");
        // slot achievedThrough
        addConceptSlot("achievedThrough", 2, true, "AProcess");
        //slot belongsTo
        addPrimitiveSlot("addressedTo", 2, true, "String");
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
     * Get component names that this state is addressed to.
     *
     * @return List of Strings
     */
    public List<String> getComponentNames() {
        return componentNames;
    }

    /**
     * Set component names that this state is assigned to.
     *
     * @param componentNames List of Strings
     */
    public void setComponentNames(List<String> componentNames) {
        this.componentNames = componentNames;
    }

    /**
     * Get processes required to achieve this state.
     *
     * @return List of AProcess objects
     */
    public List<AProcess> getProcesses() {
        return processes;
    }

    /**
     * Set processes required to achieve this state.
     *
     * @param processes List AProcess objects
     */
    public void setProcesses(List<AProcess> processes) {
        this.processes = processes;
    }
}
