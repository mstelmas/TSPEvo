package org.wmh.evo;

import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public interface PopulationProvider<G extends Gene<?, G>, C extends Comparable<? super C>> {
    Population<G, C> provide(final int size);
}
