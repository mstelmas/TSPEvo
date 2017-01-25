package org.wmh.evo.selection;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

@RequiredArgsConstructor
public class Selector<G extends Gene<?, G>, C extends Number & Comparable<? super C>> {
    private final SelectionStrategy<G, C> selectionStrategy;

    public Population<G, C> select(final Population<G, C> population) {
        return selectionStrategy.select(population);
    }
}
