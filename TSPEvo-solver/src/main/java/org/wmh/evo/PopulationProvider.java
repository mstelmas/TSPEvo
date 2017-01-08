package org.wmh.evo;

import org.wmh.evo.core.Gene;
import org.wmh.evo.core.Population;

public interface PopulationProvider<G extends Gene<?, G>, C extends Comparable<? super C>> {
    Population<G, C> provide(final int size);
}
