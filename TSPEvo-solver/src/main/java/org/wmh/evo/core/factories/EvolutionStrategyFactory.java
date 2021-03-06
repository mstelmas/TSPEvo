package org.wmh.evo.core.factories;


import org.wmh.evo.core.EvolutionParams;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.strategies.*;

public class EvolutionStrategyFactory {
    public static <T extends Gene<?, T>, C extends Number & Comparable<? super C>> EvolutionStrategy build(final EvolutionType evolutionType, final EvolutionParams<T, C> evolutionParams) {
        switch (evolutionType) {
            case GENETIC_ALGORITHM:
                return new GAStrategy<>(evolutionParams);
            case MI_PLUS_LAMBDA:
                return new MiPlusLambdaStrategy<>(evolutionParams);
            case MI_WITH_LAMBDA:
                return new MiWithLambdaStrategy<>(evolutionParams);
            default:
                throw new IllegalStateException("Evolution type: " + evolutionType + " not supported");
        }
    }
}
