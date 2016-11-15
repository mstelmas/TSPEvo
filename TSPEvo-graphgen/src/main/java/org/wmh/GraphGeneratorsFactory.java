package org.wmh;

import org.wmh.generators.factories.AbstractGraphGeneratorsFactory;
import org.wmh.generators.factories.WeightedUndirectedGraphGeneratorsFactory;

public class GraphGeneratorsFactory {
    private final static AbstractGraphGeneratorsFactory weightedUndirectedGraphGeneratorsFactory = new WeightedUndirectedGraphGeneratorsFactory();

    public static AbstractGraphGeneratorsFactory weightedUndirectedGraphGeneratorsFactory() {
        return weightedUndirectedGraphGeneratorsFactory;
    }
}
