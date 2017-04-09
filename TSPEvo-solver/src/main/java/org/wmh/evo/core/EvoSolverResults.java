package org.wmh.evo.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.wmh.evo.EvoSolver;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Phenotype;
import org.wmh.evo.core.domain.Population;

@Getter
@RequiredArgsConstructor
public class EvoSolverResults<T extends Gene<?, T>, C extends Comparable<? super C>> {
    private final EvoSolver<T, ?> evoSolver;
    private final EvolutionsAggregator<T, C> evolutionsAggregator = new EvolutionsAggregator<>();

    public Phenotype<T, C> getOptimalSolution() {
        return evolutionsAggregator.getLastEvolutionStage()
                .map(EvolutionStage::getAfterPopulation)
                .map(Population::getFittest)
                .orElseThrow(() -> new RuntimeException("No evolution at last stage found! :O"));
    }
}
