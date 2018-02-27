
package org.jlab.coda.cool.ontology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: HMI (human machine interface).
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class HMI extends AOntologyConcept implements Serializable {
    private int rowsNumber;
    private int columnsNumber;
    private String name;
    private String description;
    private String isWebBased;
    private List<APanel> panels = new ArrayList<>();


    /**
     * Constructor.
     */
    public HMI() {
        setOntology("afecs");
        setConceptName("HMI");
        // slot hasName
        addPrimitiveSlot("hasName", 1, true, "String");
        // slot hasColor
        addPrimitiveSlot("hasDescription", 1, false, "String");
        addPrimitiveSlot("isWebBased", 1, false, "String");
        addPrimitiveSlot("hasRows", 1, false, "Integer");
        addPrimitiveSlot("hasColumns", 1, false, "Integer");
        addConceptSlot("hasPanel", 2, false, "APanel");
    }

    /**
     * Get number of rows.
     *
     * @return int
     */
    public int getRowsNumber() {
        return rowsNumber;
    }

    /**
     * Set number of rows.
     *
     * @param rowsNumber int
     */
    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    /**
     * Get number of columns.
     *
     * @return int
     */
    public int getColumnsNumber() {
        return columnsNumber;
    }

    /**
     * Set number of columns.
     *
     * @param columnsNumber int
     */
    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
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
     * See if this hmi is web sed.
     *
     * @return String
     */
    public String getWebBased() {
        return isWebBased;
    }

    /**
     * Set this hmi as web based.
     *
     * @param isWebBased String
     */
    public void setWebBased(String isWebBased) {
        this.isWebBased = isWebBased;
    }

    /**
     * Get list of panels of this hmi.
     *
     * @return List of APanel objects
     */
    public List<APanel> getPanels() {
        return panels;
    }

    /**
     * Set list of panels for this hmi.
     *
     * @param panels List of APanel objects
     */
    public void setPanels(List<APanel> panels) {
        this.panels = panels;
    }
}
