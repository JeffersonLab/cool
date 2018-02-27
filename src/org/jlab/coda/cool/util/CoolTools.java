package org.jlab.coda.cool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Utility methods.
 *
 * @author gurjyan on 2.26.18.
 */
public final class CoolTools {

    private CoolTools() { }

    /**
     * Checks existence of undefined rtv in the string
     * (i.e. checks for existence of "%(" in teh string).
     *
     * @param input original input string
     * @return true or false
     */
    public static boolean containsRTV(String input) {
        return input.contains("%(");
    }

    /**
     * Checks rtvs in a input string and does
     * appropriate substitution with defined
     * rtv values.
     *
     * @param tmps    input string
     * @param setRTVs map containing rtvs and their values
     * @return substituted string
     */
    public static String checkRtvs(String tmps,
                                   Map<String, String> setRTVs) {

        String res = "unset-rtv";

        // define all rtv's in the user config string
        // replace those we know how to replace
        String[] rtvs = getRTVsInAString(tmps);

        String result = tmps;
        for (String s : rtvs) {

            if (s.trim().equals("%(rn)")) {
                res = result;
            } else {
                if (setRTVs.containsKey(s)) {
                    if (setRTVs.get(s).trim().startsWith("unset")) {
                        res = "unset-" + getRTVName(s);
                    } else {
                        result = rtvReplace(result, s, setRTVs.get(s));
                        res = result;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Returns Afecs rtv's found in a string.
     *
     * @param input string
     * @return array of rtvs in the string
     */
    private static String[] getRTVsInAString(String input) {
        List<String> l = new ArrayList<>();
        if (containsRTV(input)) {
            StringTokenizer st = new StringTokenizer(input, "%(");

            while (st.hasMoreTokens()) {
                String tmp = st.nextToken();
                if (tmp.contains(")")) {
                    String tmp2 = tmp.substring(0, tmp.indexOf(")"));
                    String rtv = "%(" + tmp2 + ")";
                    if (!l.contains(rtv)) {
                        l.add(rtv);
                    }
                }
            }
        }
        return l.toArray(new String[l.size()]);
    }

    /**
     * Analyses the rtv, i.e. %(xyz), and returns the name of it, i.e.
     * in the provided example this method will return the string xyz.
     *
     * @param rtv rtv syntax
     * @return rtv name
     */
    private static String getRTVName(String rtv) {
        String res = null;
        if (rtv.startsWith("%(env.")) {
            res = rtv.substring(rtv.indexOf(".") + 1, rtv.lastIndexOf(")"));
        } else if (rtv.startsWith("%(")) {
            res = rtv.substring(rtv.indexOf("(") + 1, rtv.lastIndexOf(")"));
        }
        return res;
    }

    /**
     * String replacement.
     *
     * @param input original input string
     * @param rtv   String within the original string to be replaced
     * @param value a new string value to be replaced instead
     * @return resulting string
     */
    private static String rtvReplace(String input, String rtv, String value) {
        String result = input;
        if (input.contains(rtv)) {
            result = input.replace(rtv, value);
        }
        return result;
    }
}
