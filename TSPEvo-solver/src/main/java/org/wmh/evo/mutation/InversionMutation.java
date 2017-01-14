package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.wmh.evo.mutation.MutationUtils.extractMax;
import static org.wmh.evo.mutation.MutationUtils.extractMin;
import static org.wmh.evo.mutation.MutationUtils.generatePairOfUniqueIndices;

public class InversionMutation<G extends Gene<?, G>> implements MutationStrategy<G> {

    @Override
    public Chromosome<G> mutate(final Chromosome<G> solution) {
        final List<G> genes = new ArrayList<>(solution.getGenes());

        final Pair<Integer, Integer> indicesBoundToReverse = generatePairOfUniqueIndices(0, genes.size());

        final List<G> sublistToReverse = genes.subList(extractMin(indicesBoundToReverse), extractMax(indicesBoundToReverse) + 1);
        Collections.reverse(sublistToReverse);

        replaceWithReversedList(genes, sublistToReverse, indicesBoundToReverse);

        return solution.newInstance(genes);
    }

    private void replaceWithReversedList(final List<G> originalList, final List<G> sublistToReverse, final Pair<Integer, Integer> bounds) {
        for (int i = extractMin(bounds), j = 0; i < sublistToReverse.size(); i++, j++) {
            originalList.set(i, sublistToReverse.get(j));
        }
    }
}
