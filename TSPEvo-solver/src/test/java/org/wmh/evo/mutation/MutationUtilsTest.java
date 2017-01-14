package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MutationUtilsTest {
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 10;

    @Test
    public void shouldGeneratePairOfUniqueIndices() throws Exception {
        final Pair<Integer, Integer> generatedIndices = MutationUtils.generatePairOfUniqueIndices(LOWER_BOUND, UPPER_BOUND);

        assertThat(generatedIndices.getLeft()).isNotEqualTo(generatedIndices.getRight());
    }

    @Test
    public void shouldGenerateIndicesWithinBounds() throws Exception {
        final Pair<Integer, Integer> generatedIndices = MutationUtils.generatePairOfUniqueIndices(LOWER_BOUND, UPPER_BOUND);

        assertThat(generatedIndices.getLeft()).isBetween(LOWER_BOUND, UPPER_BOUND);
        assertThat(generatedIndices.getRight()).isBetween(LOWER_BOUND, UPPER_BOUND);
    }
}
