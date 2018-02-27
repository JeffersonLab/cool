
package org.jlab.coda.cool.ontology;

import java.util.List;

/**
 * COOL ontology interface.
 *
 * @author gurjyan
 *         Date: 02.27.18
 * @version 4.x
 */
public interface AOntology {

    /**
     * Get ontology name.
     * @return String
     */
    String getName();

    /**
     * Get the list of ontology concepts.
     * @return List object
     */
    List<AOntologyConcept> getConcepts();
}
