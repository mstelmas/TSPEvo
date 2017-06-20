package org.wmh.evo.core.strategies;


import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public interface EvolutionStrategy<T extends Gene<?, T>, C extends Number & Comparable<? super C>> {
    Population<T, C> evolve(final Population<T, C> population);
}
