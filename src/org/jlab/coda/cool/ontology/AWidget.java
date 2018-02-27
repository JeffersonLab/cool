/*
 *   Copyright (c) 2017.  Jefferson Lab (JLab). All rights reserved. Permission
 *   to use, copy, modify, and distribute  this software and its documentation for
 *   governmental use, educational, research, and not-for-profit purposes, without
 *   fee and without a signed licensing agreement.
 *
 *   IN NO EVENT SHALL JLAB BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL
 *   INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING
 *   OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF JLAB HAS
 *   BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   JLAB SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE. THE CLARA SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 *   PROVIDED HEREUNDER IS PROVIDED "AS IS". JLAB HAS NO OBLIGATION TO PROVIDE
 *   MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 *   This software was developed under the United States Government license.
 *   For more information contact author at gurjyan@jlab.org
 *   Department of Experimental Nuclear Physics, Jefferson Lab.
 */

package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: widget.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AWidget extends AOntologyConcept implements Serializable, Cloneable {
    private int number;
    private String name;
    private String description;
    private String title;
    private String type;
    private String subType;
    private String icon;
    private String editable;
    private List<AService> actionServices = new ArrayList<>();
    private List<AProcess> actionProcesses = new ArrayList<>();
    private List<AState> actionStates = new ArrayList<>();

    /**
     * Constructor.
     */
    public AWidget() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("Widget");
        // slot hasName
        addPrimitiveSlot("hasName", 1, true, "String");
        // slot hasType
        addPrimitiveSlot("hasType", 1, true, "String");
        // slot hasSubType
        addPrimitiveSlot("hasDescription", 1, false, "String");
        addPrimitiveSlot("hasSubType", 1, false, "String");
        // slot hasTitle
        addPrimitiveSlot("hasTitle", 1, false, "String");
        // slot hasIcon
        addPrimitiveSlot("hasIcon", 1, false, "String");
        // slot isEditable
        addPrimitiveSlot("isEditable", 1, false, "String");
        // slot hasNumber
        addPrimitiveSlot("hasNumber", 1, false, "Integer");
        // slot startsService
        addConceptSlot("startsService", 2, false, "AService");
        // slot startsProcess
        addConceptSlot("startsProcess", 2, false, "AProcess");
        // slot movesToStates
        addConceptSlot("moveToState", 2, false, "AState");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            if (CoolConstants.debug.get()) {
                e.printStackTrace();
            }
        }
        AWidget b = new AWidget();
        b.setName(getName());
        b.setType(getType());
        b.setSubType(getSubType());
        b.setTitle(getTitle());
        b.setIcon(getIcon());
        b.setEditable(getEditable());
        b.setNumber(getNumber());
        b.setActionServices(getActionServices());
        b.setActionProcesses(getActionProcesses());
        b.setActionStates(getActionStates());
        return b;
    }

    /**
     * Get widget number.
     *
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set widget number.
     *
     * @param number int
     */
    public void setNumber(int number) {
        this.number = number;
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
     * Get type.
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get sub type.
     *
     * @return String
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Set sub type.
     *
     * @param subType String
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /**
     * Get icon for the widget.
     *
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Get icon for the widget.
     *
     * @param icon String
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * See if the widget is editable.
     *
     * @return String
     */
    public String getEditable() {
        return editable;
    }

    /**
     * Set widget editable.
     *
     * @param editable String
     */
    public void setEditable(String editable) {
        this.editable = editable;
    }

    /**
     * Get widget action services.
     *
     * @return List of AService objects
     */
    public List<AService> getActionServices() {
        return actionServices;
    }

    /**
     * Set widget action services.
     *
     * @param actionServices List of AService objects
     */
    public void setActionServices(List<AService> actionServices) {
        this.actionServices = actionServices;
    }

    /**
     * Get widget action processes.
     *
     * @return List of AProcess objects
     */
    public List<AProcess> getActionProcesses() {
        return actionProcesses;
    }

    /**
     * Set widget action processes.
     *
     * @param actionProcesses List of AProcess objects
     */
    public void setActionProcesses(List<AProcess> actionProcesses) {
        this.actionProcesses = actionProcesses;
    }

    /**
     * Get widget action states.
     *
     * @return List of AState objects
     */
    public List<AState> getActionStates() {
        return actionStates;
    }

    /**
     * Set widget action states.
     *
     * @param actionStates List of AState objects
     */
    public void setActionStates(List<AState> actionStates) {
        this.actionStates = actionStates;
    }
}
