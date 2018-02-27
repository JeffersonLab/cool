
package org.jlab.coda.cool.util;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Constants used in the Afecs control systems.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */

public final class CoolConstants {

    private CoolConstants() {
    }

    /**
     * Debug flag.
     */
    public static AtomicBoolean debug = new AtomicBoolean();

    /**
     * Constant undefined.
     */
    public static final String UDF = "undefined";
    /**
     * Constant true.
     */
    public static final String TRUE = "true";
    /**
     * Constant false.
     */
    public static final String FALSE = "false";
    /**
     * Constant yes.
     */
    public static final String YES = "yes";
    /**
     * Constant no.
     */
    public static final String NO = "no";

    // COOL language definition (taxonomy) cool.rdfs file url
    /**
     * Constant http://COOLHOME/.
     */
    public static final String COOL_HTTP_BASE = "http://COOLHOME/";
    /**
     * Constant http://AFECSHOMEDB/.
     */
    public static final String AFECSDB_HTTP_BASE = "http://AFECSHOMEDB/";
    /**
     * Constant schema/cool#.
     */
    public static final String COOL_CORE = AFECSDB_HTTP_BASE + "schema/cool#";

    // payload type enumerations
    /**
     * Enum for  BYTE.
     */
    public static final int BYTE = 201;
    /**
     * Enum for  SHORT.
     */
    public static final int SHORT = 202;
    /**
     * Enum for  INT.
     */
    public static final int INT = 203;
    /**
     * Enum for  FLOAT.
     */
    public static final int FLOAT = 204;
    /**
     * Enum for  DOUBLE.
     */
    public static final int DOUBLE = 205;
    /**
     * Enum for  STRING.
     */
    public static final int STRING = 206;
    /**
     * Enum for  BYTE_ARRAY.
     */
    public static final int BYTE_ARRAY = 207;
    /**
     * Enum for  SHORT_ARRAY.
     */
    public static final int SHORT_ARRAY = 208;
    /**
     * Enum for  INT_ARRAY.
     */
    public static final int INT_ARRAY = 209;
    /**
     * Enum for  FLOAT_ARRAY.
     */
    public static final int FLOAT_ARRAY = 210;
    /**
     * Enum for  DOUBLE_ARRAY.
     */
    public static final int DOUBLE_ARRAY = 211;
    /**
     * Enum for  STRING_ARRAY.
     */
    public static final int STRING_ARRAY = 212;
    /**
     * Constant timeout.
     */
    public static final int TIMEOUT = 60000; // in seconds
}
