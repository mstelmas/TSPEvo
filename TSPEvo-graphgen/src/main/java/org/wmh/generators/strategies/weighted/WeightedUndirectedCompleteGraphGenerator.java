package org.wmh.generators.strategies.weighted;

import org.wmh.EdgeWeightGenerator;
import org.wmh.generators.AbstractGraphGenerator;
import org.wmh.graph.AbstractGraph;
import org.wmh.graph.Edge;
import org.wmh.graph.WeightedUndirectedGraph;

public class WeightedUndirectedCompleteGraphGenerator extends AbstractGraphGenerator {
    public AbstractGraph generate() {
        final AbstractGraph graph = new WeightedUndirectedGraph(vertexCount);

        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                graph.addEdge(new Edge(i, j, EdgeWeightGenerator.uniform(range)));
            }
        }

        return graph;
    }
}
