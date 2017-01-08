package org.wmh.evo.selection;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.Gene;
import org.wmh.evo.core.Population;

@RequiredArgsConstructor
public class Selector<G extends Gene<?, G>, C extends Comparable<? super C>> {
    private final SelectionStrategy<G, C> selectionStrategy;

    public Population<G, C> select(final Population<G, C> population) {
        return selectionStrategy.select(population);
    }
}
