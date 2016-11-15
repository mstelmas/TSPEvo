package org.wmh.graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    @Getter private final int V;
    @Getter private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(final int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices cannot be negative");
        }

        this.V = V;
        this.E = 0;

        adj = (List<Edge>[]) new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(final Edge edge) {
        final int v = edge.either();
        final int w = edge.other(v);

        validateVertex(v);
        validateVertex(w);

        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    private void validateVertex(final int v) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException("Vertex id should be between 0 and " + (V - 1));
        }
    }
}
