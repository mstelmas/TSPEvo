package org.wmh.generators;

import org.apache.commons.lang3.Range;
import org.wmh.graph.AbstractGraph;

public abstract class AbstractGraphGenerator {
    protected int vertexCount;
    protected Range<Integer> range;

    public AbstractGraphGenerator withVertexCount(final int vertexCount) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException("Vertex size has to be positive");
        }

        this.vertexCount = vertexCount;
        return this;
    }

    public AbstractGraphGenerator withWeightsInRange(final Range<Integer> range) {
        if (range == null) {
            throw new IllegalArgumentException("Edge weights range cannot be null");
        }

        this.range = range;
        return this;
    }

    // TODO: template method for required parameters validation
    public abstract AbstractGraph generate();
}
