package org.jlab.coda.cool.ontology;


import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;

/**
 * COOL ontology concept: Channel definition.
 *
 * @author gurjyan
 *         Date: 02.26.18
 * @version 4.x
 */
public class AChannel extends AOntologyConcept implements Serializable {
    private String name;
    private Object value;
    private Object minValue;
    private Object maxValue;
    private Object setValue;
    private int valueType;
    private String unit;
    private String description;

    /**
     * Constructor.
     */
    public AChannel() {
        setOntology("afecs");
        setConceptName("Channel");

        addPrimitiveSlot("hasName", 1, false, "String");
        addConceptSlot("hasValue", 1, true, "Object");
        addConceptSlot("hasMinValue", 1, true, "Object");
        addConceptSlot("hasMaxValue", 1, true, "Object");
        addConceptSlot("hasSetValue", 1, true, "Object");
        addPrimitiveSlot("hasValueType", 1, true, "Integer");
        addPrimitiveSlot("hasUnit", 1, true, "String");
        addPrimitiveSlot("hasDescription", 1, true, "String");
    }

    /**
     * Get the name of the channel.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the channel.
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the vale of the channel.
     *
     * @return Object
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the vale of the channel.
     *
     * @param value Object
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Set the lower limit of the channel.
     *
     * @param minValue Object
     */
    public void setMinValue(Object minValue) {
        this.minValue = minValue;
    }

    /**
     * Set the upper limit of the channel.
     *
     * @param maxValue Object
     */
    public void setMaxValue(Object maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Return the set value of the channel.
     *
     * @return Object
     */
    public Object getSetValue() {
        return setValue;
    }

    /**
     * Defines the set value of the channel.
     *
     * @param setValue Object
     */
    public void setSetValue(Object setValue) {
        this.setValue = setValue;
    }

    /**
     * Get the type of the channel value.
     *
     * @return Integer
     */
    public int getValueType() {
        return valueType;
    }

    /**
     * Sewt the type of the channel value.
     *
     * @param valueType Integer
     */
    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    /**
     * Swet the channel value unit of measurements.
     *
     * @param unit String
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Get the description of the channel.
     *
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("channel name           = ").append(name).append("\n");
        sb.append("--------------------------------\n");
        String type = CoolConstants.UDF;
        switch (valueType) {
            case CoolConstants.BYTE:
                type = "byte";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.BYTE_ARRAY:
                type = "byte array";
                byte[] v = (byte[]) value;
                sb.append("channel current value  = ");
                for (byte aV : v) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            case CoolConstants.SHORT:
                type = "short";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.SHORT_ARRAY:
                type = "short array";
                short[] vs = (short[]) value;
                sb.append("channel current value  = ");
                for (short aV : vs) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            case CoolConstants.INT:
                type = "int";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.INT_ARRAY:
                type = "int array";
                int[] vi = (int[]) value;
                sb.append("channel current value  = ");
                for (int aV : vi) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            case CoolConstants.FLOAT:
                type = "float";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.FLOAT_ARRAY:
                type = "float array";
                float[] vf = (float[]) value;
                sb.append("channel current value  = ");
                for (float aV : vf) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            case CoolConstants.DOUBLE:
                type = "double";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.DOUBLE_ARRAY:
                type = "double array";
                double[] vd = (double[]) value;
                sb.append("channel current value  = ");
                for (double aV : vd) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            case CoolConstants.STRING:
                type = "string";
                sb.append("channel current value  = ").append(value).append("\n");
                break;
            case CoolConstants.STRING_ARRAY:
                type = "string array";
                String[] vss = (String[]) value;
                sb.append("channel current value  = ");
                for (String aV : vss) {
                    sb.append(aV).append(" ");
                }
                sb.append("\n");
                break;
            default:
                break;
        }
        sb.append("channel value type     = ").append(type).append("\n");
        sb.append("channel description    = ").append(description).append("\n");
        sb.append("channel value unit     = ").append(unit).append("\n");
        sb.append("channel min value      = ").append(minValue).append("\n");
        sb.append("channel max value      = ").append(maxValue).append("\n");
        sb.append("channel set value      = ").append(setValue).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean stat = false;
        if (obj instanceof AChannel) {
            stat = true;
            AChannel c1 = (AChannel) obj;
            if (c1.getName().equals(getName()) && c1.getValueType() == getValueType()) {
                switch (c1.getValueType()) {
                    case CoolConstants.BYTE:
                        Byte s1 = (Byte) c1.getSetValue();
                        Byte v1 = (Byte) getValue();
                        if (s1.byteValue() != v1.byteValue()) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.SHORT:
                        Short s2 = (Short) c1.getSetValue();
                        Short v2 = (Short) getValue();
                        if (s2.shortValue() != v2.shortValue()) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.INT:
                        Integer s3 = (Integer) c1.getSetValue();
                        Integer v3 = (Integer) getValue();
                        if (s3.intValue() != v3.intValue()) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.DOUBLE:
                        Double s4 = (Double) c1.getSetValue();
                        Double v4 = (Double) getValue();
                        if (s4.doubleValue() != v4.doubleValue()) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.FLOAT:
                        Float s5 = (Float) c1.getSetValue();
                        Float v5 = (Float) getValue();
                        if (s5.floatValue() != v5.floatValue()) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.STRING:
                        if (!c1.getSetValue().equals(getValue())) {
                            stat = false;
                        }
                        break;
                    case CoolConstants.BYTE_ARRAY:
                        byte[] a1 = (byte[]) c1.getSetValue();
                        byte[] b1 = (byte[]) getValue();
                        for (int i = 0; i < a1.length; i++) {
                            if (a1[i] != b1[i]) {
                                stat = false;
                            }
                        }
                        break;
                    case CoolConstants.SHORT_ARRAY:
                        short[] a2 = (short[]) c1.getSetValue();
                        short[] b2 = (short[]) getValue();
                        for (int i = 0; i < a2.length; i++) {
                            if (a2[i] != b2[i]) {
                                stat = false;
                            }
                        }
                        break;
                    case CoolConstants.INT_ARRAY:
                        int[] a3 = (int[]) c1.getSetValue();
                        int[] b3 = (int[]) getValue();
                        for (int i = 0; i < a3.length; i++) {
                            if (a3[i] != b3[i]) {
                                stat = false;
                            }
                        }
                        break;
                    case CoolConstants.DOUBLE_ARRAY:
                        double[] a4 = (double[]) c1.getSetValue();
                        double[] b4 = (double[]) getValue();
                        for (int i = 0; i < a4.length; i++) {
                            if (a4[i] != b4[i]) {
                                stat = false;
                            }
                        }
                        break;
                    case CoolConstants.FLOAT_ARRAY:
                        float[] a5 = (float[]) c1.getSetValue();
                        float[] b5 = (float[]) getValue();
                        for (int i = 0; i < a5.length; i++) {
                            if (a5[i] != b5[i]) {
                                stat = false;
                            }
                        }
                        break;
                    case CoolConstants.STRING_ARRAY:
                        String[] a6 = (String[]) c1.getSetValue();
                        String[] b6 = (String[]) getValue();
                        for (int i = 0; i < a6.length; i++) {
                            if (!a6[i].equals(b6[i])) {
                                stat = false;
                            }
                        }
                        break;
                    default:
                        stat = false;
                }
            }
        }
        return stat;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + minValue.hashCode();
        result = 31 * result + maxValue.hashCode();
        result = 31 * result + setValue.hashCode();
        result = 31 * result + valueType;
        result = 31 * result + unit.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

}
