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

import java.io.Serializable;
import java.util.ArrayList;

public class HMI extends AOntologyConcept implements Serializable {
    private String name;
    private String description;
    private ArrayList<APanel> panel = new ArrayList<APanel>();
    private String isWebBased;
    private int rowsNumber, columnsNumber;


    public HMI(){
        setOntology("afecs");
        setConceptName("HMI");
        // slot hasName
        addPrimitiveSlot( "hasName",1,true,"String");
        // slot hasColor
        addPrimitiveSlot( "hasDescription",1,false,"String");
        addPrimitiveSlot( "isWebBased",1,false,"String");
        addPrimitiveSlot( "hasRows",1,false,"Integer");
        addPrimitiveSlot( "hasColumns",1,false,"Integer");
        addConceptSlot( "hasPanel",2,false,"APanel");
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPanels(ArrayList<APanel> panel) {
        this.panel = panel;
    }

    public void setWebBased(String webBased) {
        isWebBased = webBased;
    }


    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }
}
