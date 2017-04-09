package org.wmh.evo.succession;

import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public interface SuccessionStrategy<G extends Gene<?, G>, C extends Number & Comparable<? super C>> {
    Population<G, C> join(final Population<G, C> initialPopulation, final Population<G, C> offspringsPopulation);
}
