package org.wmh.evo;

import org.wmh.graph.AbstractGraph;
import org.wmh.graph.Edge;
import org.wmh.graph.WeightedUndirectedGraph;

public class TspTestGraph {
    private static AbstractGraph buildSimpleTempGraph() {
        final AbstractGraph g = new WeightedUndirectedGraph(5);
        g.addEdge(new Edge(0, 1, 2));
        g.addEdge(new Edge(0, 2, 3));
        g.addEdge(new Edge(0, 3, 2));
        g.addEdge(new Edge(0, 4, 3));

        g.addEdge(new Edge(1, 2, 3));
        g.addEdge(new Edge(1, 3, 4));
        g.addEdge(new Edge(1, 4, 1));

        g.addEdge(new Edge(2, 3, 2));
        g.addEdge(new Edge(2, 4, 4));

        g.addEdge(new Edge(3, 4, 5));
        return g;
    }
}
