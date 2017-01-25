package org.wmh.evo.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

@Getter
@RequiredArgsConstructor
public class EvolutionStage<T extends Gene<?, T>, C extends Comparable<? super C>> {
    private final int stage;
    private final Population<T, C> beforePopulation;
    private final Population<T, C> afterPopulation;
}
