package org.wmh.evo.mutation;

import org.junit.Test;
import org.wmh.evo.TspTestTours;
import org.wmh.evo.core.domain.AbstractChromosome;
import org.wmh.tsp.domain.Tour;

public class SwapMutationTest {
    private static final MutationStrategy swapMutation = new SwapMutation();
    private static final AbstractChromosome<?> CHROMOSOME = Tour.of(TspTestTours.TSP_TOUR_CITIES);

    @Test
    public void shouldSwapMutateChromosome() throws Exception {
    }
}
