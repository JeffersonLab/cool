
package org.jlab.coda.cool.util;

/**
 * CODA component types.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public enum CodaTypes {
    /** User component.*/
    USR(10),
    /** Slow control component.*/
    SLC(110),
    /** Wnc component.*/
    WNC(210),
    /** Event recorder component.*/
    ER(310),
    /** Farm control system component.*/
    FCS(410),
    /** Primary event builder component.*/
    PEB(510),
    /** Secondary event builder component.*/
    SEB(610),
    /** Event builder component.*/
    EB(710),
    /** Coda event builder component.*/
    CDEB(810),
    /** Data concentrator component.*/
    DC(910),
    /** readout controller component.*/
    ROC(1010),
    /** GT component.*/
    GT(1110),
    /** Trigger supervisor component.*/
    TS(1210),
    /** Supervisor component.*/
    SMS(1310),
    /** RCS component.*/
    RCS(1410),
    /** File component.*/
    FILE(1510);

    private int p;

    /**
     * Constructs coda type enum based on priority.
     * @param s priority
     */
    CodaTypes(int s) {
        p = s;
    }

    /**
     * Returns priority of coda type enum object.
     * @return priority
     */
    public int priority() {
        return p;
    }
}
