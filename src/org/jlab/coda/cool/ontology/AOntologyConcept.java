
package org.jlab.coda.cool.ontology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AOntologyConcept implements Serializable {

    private String name;
    private String ontology;

    private List<AOntologySlot> slots = new ArrayList<>();

    /**
     * Set concept name.
     *
     * @param name String
     */
    public void setConceptName(String name) {
        this.name = name;
    }

    /**
     * Add slot to the concept.
     *
     * @param slot AOntologySlot object
     */
    public void addSlot(AOntologySlot slot) {
        this.slots.add(slot);
    }

    /**
     * Set ontology.
     *
     * @param ontology String
     */
    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    /**
     * Add a primitive slot to the concept.
     *
     * @param name        of the slot
     * @param cardinality single or multiple ( 1 is single, 2 is multiple)
     * @param optional    true = optional , false = mandatory
     * @param objType     the class name of the Java implementation of the primitive types.
     */
    public void addPrimitiveSlot(String name, int cardinality, boolean optional, String objType) {
        AOntologySlot os = new AOntologySlot();
        os.setName(name);
        os.setCardinality(cardinality);
        os.setOptional(optional);
        os.setJavaObject(true);
        os.setJavaObjectType(objType);
        addSlot(os);
    }

    /**
     * Add slot to the concept.
     *
     * @param name        of the slot
     * @param cardinality single or multiple ( 1 is single, 2 is multiple)
     * @param optional    true = optional , false = mandatory
     * @param objType     class name implementing concept
     */
    public void addConceptSlot(String name, int cardinality, boolean optional, String objType) {
        AOntologySlot os = new AOntologySlot();
        os.setName(name);
        os.setCardinality(cardinality);
        os.setOptional(optional);
        os.setOntologyConcept(true);
        os.setConceptType(objType);
        addSlot(os);
    }

    /**
     * Get concept name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get ontology of this concept.
     *
     * @return String
     */
    public String getOntology() {
        return ontology;
    }

    /**
     * Get concept slots.
     *
     * @return List of AOntologySlot objects
     */
    public List<AOntologySlot> getSlots() {
        return slots;
    }
}
