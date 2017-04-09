package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.wmh.evo.EvoUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EvoUtilsTest {
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 10;
    private static final int N = 4;
    private static final Pair<Integer, Integer> BOUNDS_PAIR = Pair.of(UPPER_BOUND, LOWER_BOUND);

    private final EvoUtils evoUtils = new EvoUtils();

    @Test
    public void shouldGeneratePairOfUniqueIndices() throws Exception {
        final Pair<Integer, Integer> generatedIndices = evoUtils.generatePairOfUniqueIndices(LOWER_BOUND, UPPER_BOUND);

        assertThat(generatedIndices.getLeft()).isNotEqualTo(generatedIndices.getRight());
    }

    @Test
    public void shouldGenerateUniqueIndices() throws Exception {
        final List<Integer> uniqueIndices = evoUtils.generateUniqueIndices(LOWER_BOUND, UPPER_BOUND, N);

        assertThat(uniqueIndices).hasSize(N);
    }

    @Test
    public void shouldGenerateIndicesWithinBounds() throws Exception {
        final Pair<Integer, Integer> generatedIndices = evoUtils.generatePairOfUniqueIndices(LOWER_BOUND, UPPER_BOUND);

        assertThat(generatedIndices.getLeft()).isBetween(LOWER_BOUND, UPPER_BOUND);
        assertThat(generatedIndices.getRight()).isBetween(LOWER_BOUND, UPPER_BOUND);
    }

    @Test
    public void shouldReturnLowerBound() throws Exception {
        assertThat(evoUtils.extractMin(BOUNDS_PAIR)).isEqualTo(LOWER_BOUND);
    }

    @Test
    public void shouldReturnUpperBound() throws Exception {
        assertThat(evoUtils.extractMax(BOUNDS_PAIR)).isEqualTo(UPPER_BOUND);
    }
}
