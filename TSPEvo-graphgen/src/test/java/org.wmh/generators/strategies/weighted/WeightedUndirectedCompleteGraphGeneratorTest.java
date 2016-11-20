package org.wmh.generators.strategies.weighted;

import org.apache.commons.lang3.Range;
import org.junit.Test;
import org.wmh.generators.AbstractGraphGenerator;
import org.wmh.graph.AbstractGraph;
import org.wmh.graph.Edge;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class WeightedUndirectedCompleteGraphGeneratorTest {
    private static final int NUM_OF_VERTICES = 20;
    private static final int MIN_WEIGHT = 1;
    private static final int MAX_WEIGHT = 200;
    private static final Range<Integer> WEIGHTS_RANGE = Range.between(MIN_WEIGHT, MAX_WEIGHT);

    private static final AbstractGraphGenerator weightedUndirectedCompleteGraphGenerator =
            new WeightedUndirectedCompleteGraphGenerator().withVertexCount(NUM_OF_VERTICES).withWeightsInRange(WEIGHTS_RANGE);

    @Test
    public void shouldGenerateCompleteGraph() throws Exception {
        final AbstractGraph graph = weightedUndirectedCompleteGraphGenerator.generate();

        assertThat(graph.getV()).isEqualTo(NUM_OF_VERTICES);
        assertThat(graph.getE()).isEqualTo((NUM_OF_VERTICES * (NUM_OF_VERTICES - 1)) / 2);
        assertThat(allEdgeWeightsAreWithinRange(graph, WEIGHTS_RANGE)).isTrue();
    }

    private boolean allEdgeWeightsAreWithinRange(final AbstractGraph graph, final Range<Integer> range) {
        for (int i = 0; i < graph.getV(); i++) {
            final Iterator<Edge> adjEdgesIterator = graph.adj(i).iterator();

            while (adjEdgesIterator.hasNext()) {
                if (!range.contains(adjEdgesIterator.next().getWeight())) {
                    return false;
                }
            }
        }
        return true;
    }
}