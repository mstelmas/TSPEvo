package org.wmh.evo.selection;

import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public interface SelectionStrategy<G extends Gene<?, G>, C extends Number & Comparable<? super C>> {
    Population<G, C> select(final Population<G, C> population);
    Population<G, C> select(final Population<G, C> population, final int size);
}
