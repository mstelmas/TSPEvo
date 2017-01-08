package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SwapMutation<G extends Gene<?, G>> implements MutationStrategy<G> {

    @Override
    public Chromosome<G> mutate(Chromosome<G> solution) {
        final List<G> genes = new ArrayList<>(solution.getGenes());

        final Pair<Integer, Integer> indicesToSwap = generatePairOfUniqueIndices(0, genes.size());

        Collections.swap(genes, indicesToSwap.getLeft(), indicesToSwap.getRight());

        return solution.newInstance(genes);
    }

    private Pair<Integer, Integer> generatePairOfUniqueIndices(final int lowerBound, final int upperBound) {
        final List<Integer> randomUniqueIntegers = ThreadLocalRandom.current()
                .ints(lowerBound, upperBound)
                .distinct()
                .limit(2)
                .boxed()
                .collect(Collectors.toList());

        return Pair.of(randomUniqueIntegers.get(0), randomUniqueIntegers.get(1));
    }
}
