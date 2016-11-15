package org.wmh.generators;

import org.wmh.graph.AbstractGraph;

public abstract class AbstractGraphGenerator {
    protected int vertexCount;

    public AbstractGraphGenerator withVertexCount(final int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Vertex size has to be positive");
        }

        this.vertexCount = vertexCount;
        return this;
    }

    public abstract AbstractGraph generate();
}
