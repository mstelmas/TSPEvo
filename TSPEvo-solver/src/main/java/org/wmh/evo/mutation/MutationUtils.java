package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MutationUtils {
    // lowerBound - inclusive
    // upperBound - exclusive
    static Pair<Integer, Integer> generatePairOfUniqueIndices(final int lowerBound, final int upperBound) {
        final List<Integer> randomUniqueIntegers = ThreadLocalRandom.current()
                .ints(lowerBound, upperBound)
                .distinct()
                .limit(2)
                .boxed()
                .collect(Collectors.toList());

        return Pair.of(randomUniqueIntegers.get(0), randomUniqueIntegers.get(1));
    }

    static int extractMin(final Pair<Integer, Integer> pair) {
        return Math.min(pair.getLeft(), pair.getRight());
    }

    static int extractMax(final Pair<Integer, Integer> pair) {
        return Math.max(pair.getLeft(), pair.getRight());
    }
}
