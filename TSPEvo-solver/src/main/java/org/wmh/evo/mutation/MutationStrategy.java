package org.wmh.evo.mutation;

import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

public interface MutationStrategy<G extends Gene<?, G>> {
    Chromosome<G> mutate(final Chromosome<G> solution);
}
