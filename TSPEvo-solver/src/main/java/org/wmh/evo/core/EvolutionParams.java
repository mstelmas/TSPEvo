package org.wmh.evo.core;

import lombok.Data;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.crossing.Crosser;
import org.wmh.evo.mutation.Mutator;
import org.wmh.evo.selection.Selector;
import org.wmh.evo.succession.SuccessionStrategy;


@Data
public class EvolutionParams<T extends Gene<?, T>, C extends Number & Comparable<? super C>> {
    private Integer mi;
    private Integer lambda;
    private Mutator<T, C> mutator;
    private Crosser<T, C> crosser;
    private Selector<T, C> selector;
    private SuccessionStrategy<T, C> successionStrategy;
}
