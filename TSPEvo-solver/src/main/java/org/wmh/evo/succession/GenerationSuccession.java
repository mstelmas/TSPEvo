package org.wmh.evo.succession;

import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public class GenerationSuccession<T extends Gene<?, T>, C extends Number & Comparable<? super C>> implements SuccessionStrategy<T, C> {

    @Override
    public Population<T, C> join(final Population<T, C> initialPopulation, final Population<T, C> offspringsPopulation) {

        if (offspringsPopulation.size() != initialPopulation.size()) {
            throw new IllegalStateException("Cannot perform generation succession - offsprings & initial population sizes do not match!");
        }

        return offspringsPopulation;
    }
}
