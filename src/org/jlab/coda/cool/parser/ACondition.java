
package org.jlab.coda.cool.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * COOL ontology concept: condition.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class ACondition {

    private String keyWord;
    private List<String> conditionalOperators = new ArrayList<>();
    private List<AStatement> conditionalStatements = new ArrayList<>();
    private List<AStatement> actionStatements = new ArrayList<>();

    /**
     * Get condition key word.
     *
     * @return String
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * Set condition key word.
     *
     * @param keyWord String
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Get conditional operators.
     *
     * @return List of Strings
     */
    public List<String> getConditionalOperators() {
        return conditionalOperators;
    }

    /**
     * Set conditional operators.
     *
     * @param conditionalOperators List of Strings
     */
    public void setConditionalOperators(List<String> conditionalOperators) {
        this.conditionalOperators = conditionalOperators;
    }

    /**
     * Get conditional statements.
     *
     * @return List of AStatement objects
     */
    public List<AStatement> getConditionalStatements() {
        return conditionalStatements;
    }

    /**
     * Set conditional statements.
     *
     * @param conditionalStatements List of ACondition objects
     */
    public void setConditionalStatements(List<AStatement> conditionalStatements) {
        this.conditionalStatements = conditionalStatements;
    }

    /**
     * Get action statements.
     *
     * @return List of AStatement objects
     */
    public List<AStatement> getActionStatements() {
        return actionStatements;
    }

    /**
     * Set action statements.
     *
     * @param actionStatements List of AStatement objects
     */
    public void setActionStatements(List<AStatement> actionStatements) {
        this.actionStatements = actionStatements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(keyWord).append("\n");
        AStatement stmt;
        System.out.println("\n--------- New ACondition -------- ");
        for (int i = 0; i < conditionalStatements.size(); i++) {
            stmt = conditionalStatements.get(i);
            sb.append(stmt.getLeft())
                    .append(" ")
                    .append(stmt.getActionOperator())
                    .append(" ")
                    .append(stmt.getRight())
                    .append("\n");
            if (i < conditionalOperators.size()) {
                sb.append(conditionalOperators.get(i)).append("\n");
            }
        }
        for (AStatement actionStatement : actionStatements) {
            stmt = actionStatement;
            sb.append(" -> ").append(stmt.getLeft())
                    .append(" ")
                    .append(stmt.getActionOperator())
                    .append(" ")
                    .append(stmt.getRight())
                    .append("\n");
        }
        return sb.toString();
    }
}
