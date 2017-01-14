package org.wmh.evo.crossing;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;
import org.wmh.evo.core.Phenotype;
import org.wmh.evo.core.Population;

import java.util.List;

@RequiredArgsConstructor
public class Crosser<G extends Gene<?, G>, C extends Comparable<? super C>> {
    private final CrossOverStrategy<G> crossOverStrategy;
    private final double crossRate;

    public Population<G, C> cross(final Population<G, C> population, final long evolutionIteration) {
        final Population<G, C> newPopulation = population.copy();
        final List<Phenotype<G, C>> phenotypes = newPopulation.getPopulation();

        for (int i = 0; i < phenotypes.size(); i++) {
            for (int j = (i + 1); j < phenotypes.size(); j++) {
                final Chromosome<G> chromosome1 = phenotypes.get(i).getChromosome();
                final Chromosome<G> chromosome2 = phenotypes.get(j).getChromosome();

                if (Math.random() <= crossRate) {
                    final Chromosome<G> crossedChromosome = crossOverStrategy.cross(chromosome1, chromosome2);
                    phenotypes.set(j, phenotypes.get(j).newInstance(crossedChromosome, evolutionIteration));
                }
            }
        }
        return newPopulation;
    }
}
