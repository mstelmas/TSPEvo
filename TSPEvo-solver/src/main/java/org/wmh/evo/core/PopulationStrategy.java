package org.wmh.evo.core;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public interface PopulationStrategy<T extends Gene<?, T>, C extends Comparable<? super C>> {
    Phenotype<T, C> getFittest();
    void add(final Phenotype<T, C> phenotype);
    Phenotype<T, C> getPhenotypeAt(final int index);
    Stream<Phenotype<T, C>> stream();
    void sort();
    void sort(Comparator<C> comparator);
    int size();

    Population<T, C> newInstance(final List<Phenotype<T, C>> phenotypes);
}
