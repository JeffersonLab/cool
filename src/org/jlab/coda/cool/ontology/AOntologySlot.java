
package org.jlab.coda.cool.ontology;

import java.io.Serializable;

/**
 * COOL ontology slot.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AOntologySlot implements Serializable {

    private String name;
    private boolean isOptional;
    private boolean isJavaObject;
    private boolean isOntologyConcept;
    private int cardinality;
    private String javaObjectType;
    private String conceptType;

    /**
     * Set name.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define if slot is optional.
     *
     * @param optional boolean
     */
    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    /**
     * Set slot cardinality.
     *
     * @param cardinality int ( 1 is single, 2 is multiple)
     */
    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    /**
     * Set Java object type.
     *
     * @param javaObjectType String
     */
    public void setJavaObjectType(String javaObjectType) {
        this.javaObjectType = javaObjectType;
    }

    /**
     * Set concept type.
     *
     * @param conceptType String
     */
    public void setConceptType(String conceptType) {
        this.conceptType = conceptType;
    }

    /**
     * Define if slot is represented by a Java object.
     *
     * @param javaObject boolean
     */
    public void setJavaObject(boolean javaObject) {
        isJavaObject = javaObject;
    }

    /**
     * Define if slot is an ontology concept.
     *
     * @param ontologyConcept boolean
     */
    public void setOntologyConcept(boolean ontologyConcept) {
        isOntologyConcept = ontologyConcept;
    }

    /**
     * Get slot name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if slot is optional.
     *
     * @return boolean
     */
    public boolean isOptional() {
        return isOptional;
    }

    /**
     * Returns true if slot is represented dby a Java object.
     *
     * @return boolean
     */
    public boolean isJavaObject() {
        return isJavaObject;
    }

    /**
     * Returns true if slot is an ontology concept.
     *
     * @return boolean
     */
    public boolean isOntologyConcept() {
        return isOntologyConcept;
    }

    /**
     * Get cardinality of a slot.
     *
     * @return int
     */
    public int getCardinality() {
        return cardinality;
    }

    /**
     * Get type of concept representing Java object type.
     *
     * @return String
     */
    public String getJavaObjectType() {
        return javaObjectType;
    }

    /**
     * Get concept type.
     *
     * @return String
     */
    public String getConceptType() {
        return conceptType;
    }
}
