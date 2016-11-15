package org.wmh.generators.factories;

import org.wmh.generators.AbstractGraphGenerator;
import org.wmh.generators.GraphType;
import org.wmh.generators.strategies.weighted.WeightedUndirectedCompleteGraphGenerator;

public class WeightedUndirectedGraphGeneratorsFactory implements AbstractGraphGeneratorsFactory {
    public AbstractGraphGenerator buildGraphGenerator(final GraphType graphType) {
        switch (graphType) {
            case COMPLETE:
                return new WeightedUndirectedCompleteGraphGenerator();
            default:
                throw new IllegalStateException("AbstractGraphGenerator for weighted graph of type: " + graphType + " not found");
        }
    }
}
