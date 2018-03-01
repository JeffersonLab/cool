
package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: system.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class ASystem extends AOntologyConcept implements Serializable, Cloneable {

    private List<AControl> controls = new ArrayList<>();
    private AOption option;
    private String name;
    private HMI gui;

    /**
     * Constructor.
     */
    public ASystem() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("System");
        // slot hasName
        addPrimitiveSlot("hasName", 1, false, "String");
        // slot hasOption
        addConceptSlot("hasOption", 1, true, "AOption");
        // slot hasControl
        addConceptSlot("hasControl", 2, true, "AControl");
        addConceptSlot("hasHmi", 1, true, "HMI");
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            if (CoolConstants.DEBUG.get()) {
                e.printStackTrace();
            }
        }
        ASystem ac = new ASystem();
        ac.setName(name);
        ac.setControls(controls);
        ac.setOption(option);
        return ac;
    }

    /**
     * Get controls concepts of a system.
     *
     * @return List of Control objects.
     */
    public List<AControl> getControls() {
        return controls;
    }

    /**
     * Set controls concepts of a system.
     *
     * @param controls List of Control objects.
     */
    public void setControls(List<AControl> controls) {
        this.controls = controls;
    }

    /**
     * Get option concept.
     *
     * @return AOption object
     */
    public AOption getOption() {
        return option;
    }

    /**
     * Set option concept.
     *
     * @param option AOPtion object
     */
    public void setOption(AOption option) {
        this.option = option;
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
     * Get GUI of this control system.
     *
     * @return HMI pbject
     */
    public HMI getGui() {
        return gui;
    }

    /**
     * Set GUI for this control system.
     *
     * @param gui HMI object
     */
    public void setGui(HMI gui) {
        this.gui = gui;
    }
}
