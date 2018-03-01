
package org.jlab.coda.cool.ontology;


import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: Graphical panel.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class APanel extends AOntologyConcept implements Serializable, Cloneable {
    private String name;
    private String description;
    private String color;
    private String title;
    private int number;
    private List<AWidget> widgets = new ArrayList<>();

    /**
     * Constructor.
     */
    public APanel() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("Panel");
        // slot hasName
        addPrimitiveSlot("hasName", 1, true, "String");
        // slot hasColor
        addPrimitiveSlot("hasColor", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, false, "String");
        // slot hasTitle
        addPrimitiveSlot("hasTitle", 1, false, "String");
        // slot hasNumber
        addPrimitiveSlot("hasNumber", 1, false, "String");
        // slot widget
        addConceptSlot("hasWidget", 2, false, "AWidget");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            if (CoolConstants.DEBUG.get()) {
                e.printStackTrace();
            }
        }
        APanel b = new APanel();
        b.setName(getName());
        b.setColor(getColor());
        b.setTitle(getTitle());
        b.setNumber(getNumber());
        return b;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Set panel name.
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
     * Get color.
     *
     * @return String
     */
    public String getColor() {
        return color;
    }

    /**
     * Set color.
     *
     * @param color String
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get title.
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get run number.
     *
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set run number.
     *
     * @param number int
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get widgets of this panel.
     *
     * @return Lint of AWidget objects
     */
    public List<AWidget> getWidgets() {
        return widgets;
    }

    /**
     * Set widgets.
     *
     * @param widgets List of AWidget objects
     */
    public void setWidgets(List<AWidget> widgets) {
        this.widgets = widgets;
    }

    /**
     * Add widget.
     *
     * @param w AWidget object
     */
    public void addWidget(AWidget w) {
        widgets.add(w);
    }
}
