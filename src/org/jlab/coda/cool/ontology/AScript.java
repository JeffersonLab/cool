
package org.jlab.coda.cool.ontology;

import org.jlab.coda.cool.util.CoolConstants;

import java.io.Serializable;

/**
 * COOL ontology concept: script.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public class AScript extends AOntologyConcept implements Serializable {
    private String name = CoolConstants.UDF;
    private String description = CoolConstants.UDF;
    private String commandString = CoolConstants.UDF;
    private String sync = CoolConstants.UDF;
    private int exitCode = 777;
    private int timeout;

    /**
     * Constructor.
     */
    public AScript() {
        // this is a concept of org.jlab.coda.afecs.cool.ontology
        setOntology("afecs");
        // the name of the concept
        setConceptName("Script");
        addPrimitiveSlot("hasName", 1, false, "String");
        addPrimitiveSlot("hasDescription", 1, false, "String");
        addPrimitiveSlot("hasCommandString", 1, false, "String");
        addPrimitiveSlot("hasExitCode", 1, true, "String");
        addPrimitiveSlot("isSynchronous", 1, true, "String");
        addPrimitiveSlot("hasTimeout", 1, true, "String");
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
     * Get command string, i.e. shell command.
     *
     * @return String
     */
    public String getCommandString() {
        return commandString;
    }

    /**
     * Set command srtring, i.e. shell command.
     *
     * @param commandString String
     */
    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    /**
     * See if script needs to be executed in sync with the state machine.
     *
     * @return String
     */
    public String getSync() {
        return sync;
    }

    /**
     * Define this script to be in sync with the control state machine (it will stop further transitions).
     *
     * @param sync String
     */
    public void setSync(String sync) {
        this.sync = sync;
    }

    /**
     * Get script exit code.
     *
     * @return int
     */
    public int getExitCode() {
        return exitCode;
    }

    /**
     * Set script exit code.
     *
     * @param exitCode int
     */
    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    /**
     * Get script execution timeout (in case of sync script).
     *
     * @return int
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Set script execution timeout (in case of sync script).
     *
     * @param timeout int
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "AScript{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", commandString='" + commandString + '\''
                + ", sync='" + sync + '\''
                + ", exitCode=" + exitCode
                + ", timeout=" + timeout
                + '}';
    }
}
