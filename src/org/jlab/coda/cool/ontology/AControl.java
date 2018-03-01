package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: Control.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public class AControl extends AOntologyConcept implements Serializable, Cloneable {
    private List<AComponent> components = new ArrayList<>();
    private AComponent supervisor;
    private String name;
    private HMI gui;
    private AOption option;

    /**
     * Constructor.
     */
    public AControl() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("Control");
        // slot hasName
        addPrimitiveSlot("hasName", 1, false, "String");
        // slot hasComponent
        addConceptSlot("hasComponent", 2, true, "AComponent");
        // slot hasSupervisor
        addConceptSlot("hasSupervisor", 1, true, "AComponent");
        addConceptSlot("hasHmi", 1, true, "HMI");
        addConceptSlot("hasOption", 1, true, "AOption");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
            if (CoolConstants.DEBUG.get()) {
                e.printStackTrace();
            }
        }
        AControl ac = new AControl();
        ac.setName(name);
        ac.setComponents(components);
        ac.setSupervisor(supervisor);
        ac.setOption(option);
        ac.setGui(gui);
        return ac;
    }

    /**
     * Set control name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get control system components.
     * @return List of AComponent objects
     */
    public List<AComponent> getComponents() {
        return components;
    }

    /**
     * Set control system components.
     * @param components List of AComponent objects
     */
    public void setComponents(List<AComponent> components) {
        this.components = components;
    }

    /**
     * Get supervisor agent.
     * @return AComponent object
     */
    public AComponent getSupervisor() {
        return supervisor;
    }

    /**
     * Set supervisor agent.
     * @param supervisor AComponent object
     */
    public void setSupervisor(AComponent supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * Set user interface for this control.
     * @param gui HMI object
     */
    public void setGui(HMI gui) {
        this.gui = gui;
    }

    /**
     * Get control system options.
     * @return AOption object
     */
    public AOption getOption() {
        return option;
    }

    /**
     * Set control system options.
     * @param option AOption object
     */
    public void setOption(AOption option) {
        this.option = option;
    }
}
