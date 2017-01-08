package org.wmh.evo.selection;

import org.wmh.evo.core.Gene;
import org.wmh.evo.core.Population;

public interface SelectionStrategy<G extends Gene<?, G>, C extends Comparable<? super C>> {
    Population<G, C> select(final Population<G, C> population);
}
