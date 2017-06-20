package org.wmh.evo.core.strategies;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.EvolutionParams;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;


@RequiredArgsConstructor
public class GAStrategy<T extends Gene<?, T>, C extends Number & Comparable<? super C>> implements EvolutionStrategy<T, C> {

    private final EvolutionParams<T, C> evolutionParams;

    @Override
    public Population<T, C> evolve(final Population<T, C> population) {
        final Population<T, C> offspringPopulation = evolutionParams.getSelector().select(population);
        final Population<T, C> crossedOffspringPopulation = evolutionParams.getCrosser().cross(offspringPopulation, 0);
        final Population<T, C> mutatedAndCrossedOffspringPopulation = evolutionParams.getMutator().mutate(crossedOffspringPopulation);

        return evolutionParams.getSuccessionStrategy().join(population, mutatedAndCrossedOffspringPopulation);
    }
}
