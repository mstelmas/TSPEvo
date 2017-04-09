package org.wmh.evo.mutation;

import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;

public interface MutationStrategy<G extends Gene<?, G>> {
    Chromosome<G> mutate(final Chromosome<G> solution);
}
