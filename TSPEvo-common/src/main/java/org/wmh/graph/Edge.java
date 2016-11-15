package org.wmh.graph;

import lombok.Getter;

public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    @Getter private final double weight;

    public Edge(final int v, final int w, final double weight) {
        validateVertexNonNegativity(v);
        validateVertexNonNegativity(w);
        validateEdgeWeight(weight);

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Edge does not contain vertex with id: " + vertex);
        }

    }

    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.getWeight());
    }

    private void validateVertexNonNegativity(final int v) {
        if (v < 0) {
            throw new IndexOutOfBoundsException("Vertex id cannot be negative");
        }
    }

    private void validateEdgeWeight(final double weight) {
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
    }
}
