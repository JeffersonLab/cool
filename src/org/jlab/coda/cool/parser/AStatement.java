
package org.jlab.coda.cool.parser;

/**
 * COOL ontology concept: statement.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AStatement {
    private String left;
    private String right;
    private String actionOperator;


    /**
     * Get left operand of a statement.
     *
     * @return String
     */
    public String getLeft() {
        return left;
    }

    /**
     * Set left operand of a statement.
     *
     * @param left String
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * Get right operand of a statement.
     *
     * @return String
     */
    public String getRight() {
        return right;
    }

    /**
     * Set right operand of a statement.
     *
     * @param right String
     */
    public void setRight(String right) {
        this.right = right;
    }

    /**
     * Get action operator.
     *
     * @return String
     */
    public String getActionOperator() {
        return actionOperator;
    }

    /**
     * Set action operator.
     *
     * @param actionOperator String
     */
    public void setActionOperator(String actionOperator) {
        this.actionOperator = actionOperator;
    }

    @Override
    public String toString() {
        return "AStatement{"
                + "left='" + left + '\''
                + ", right='" + right + '\''
                + ", actionOperator='" + actionOperator + '\''
                + '}';
    }
}
