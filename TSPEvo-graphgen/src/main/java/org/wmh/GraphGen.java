package org.wmh;

import org.apache.commons.lang3.Range;
import org.wmh.generators.GraphType;

public class GraphGen {
    public static void main(String[] args) {
        GraphGeneratorsFactory.weightedUndirectedGraphGeneratorsFactory()
                .buildGraphGenerator(GraphType.COMPLETE)
                .withVertexCount(16)
                .withWeightsInRange(Range.between(1, 100))
                .generate();
    }
}
