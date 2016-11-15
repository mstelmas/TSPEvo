package org.wmh;

import org.wmh.generators.GraphType;

public class GraphGen {
    public static void main(String[] args) {
        GraphGeneratorsFactory.weightedUndirectedGraphGeneratorsFactory()
                .buildGraphGenerator(GraphType.COMPLETE)
                .withVertexCount(16)
                .generate();
    }
}
