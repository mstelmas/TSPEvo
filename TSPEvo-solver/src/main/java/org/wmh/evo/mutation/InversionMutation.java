package org.wmh.evo.mutation;

import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.EvoUtils;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InversionMutation<G extends Gene<?, G>> implements MutationStrategy<G> {

    private EvoUtils evoUtils = new EvoUtils();

    @Override
    public Chromosome<G> mutate(final Chromosome<G> solution) {
        final List<G> genes = new ArrayList<>(solution.getGenes());

        final Pair<Integer, Integer> indicesBoundToReverse = evoUtils.generatePairOfUniqueIndices(0, genes.size());

        final List<G> sublistToReverse = genes.subList(evoUtils.extractMin(indicesBoundToReverse), evoUtils.extractMax(indicesBoundToReverse) + 1);
        Collections.reverse(sublistToReverse);

        return solution.newInstance(genes);
    }
}
