package org.wmh.tsp;

import lombok.NonNull;
import org.wmh.evo.FitnessEvaluator;
import org.wmh.evo.core.Chromosome;
import org.wmh.graph.AbstractGraph;
import org.wmh.tsp.domain.City;

public class TspFitnessEvaluator implements FitnessEvaluator<City> {
    private final TspEvoHelper tspEvoHelper;

    public TspFitnessEvaluator(@NonNull final AbstractGraph graph) {
        this.tspEvoHelper = TspEvoHelper.with(graph);
    }

    @Override
    public double evaluate(final Chromosome<City> chromosome) {
        return 1.0 / tspEvoHelper.calculatePathLength(chromosome);
    }
}
