package org.wmh.evo.crossing;

import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

public interface CrossOverStrategy<G extends Gene<?, G>> {
    Chromosome<G> cross(final Chromosome<G> chromosome1, final Chromosome<G> chromosome2);
}
