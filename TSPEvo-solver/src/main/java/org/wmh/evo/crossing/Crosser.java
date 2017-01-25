package org.wmh.evo.crossing;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Phenotype;
import org.wmh.evo.core.domain.Population;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class Crosser<G extends Gene<?, G>, C extends Comparable<? super C>> {
    private final CrossOverStrategy<G> crossOverStrategy;
    private final double crossRate;

    public Population<G, C> cross(final Population<G, C> population, final long evolutionIteration) {
        final Population<G, C> newPopulation = population.copy();
        final List<Phenotype<G, C>> phenotypes = newPopulation.getPopulation();

        Collections.shuffle(phenotypes, ThreadLocalRandom.current());

        final Iterator<Phenotype<G, C>> iterator = phenotypes.iterator();
        while (iterator.hasNext()) {
            Phenotype<G, C> parent1 = iterator.next();

            if (iterator.hasNext()) {
                Phenotype<G, C> parent2 = iterator.next();
                crossIfRatio(parent1, parent2, evolutionIteration);
            }
        }

//        assert population.size() == phenotypes.size();

        return population.newInstance(phenotypes);
    }

    private void crossIfRatio(Phenotype<G, C> parent1, Phenotype<G, C> parent2, final long evolutionIteration) {
        if (Math.random() <= crossRate) {
            final Pair<Chromosome<G>, Chromosome<G>> childrenChromosomes = doubleCrossParentChromosomes(parent1.getChromosome(), parent2.getChromosome());
            parent1 = parent1.newInstance(childrenChromosomes.getLeft(), evolutionIteration);
            parent2 = parent2.newInstance(childrenChromosomes.getRight(), evolutionIteration);
        }
    }

    private Pair<Chromosome<G>, Chromosome<G>> doubleCrossParentChromosomes(final Chromosome<G> parentChromosome1, final Chromosome<G> parentChromosome2) {
        return Pair.of(
                crossOverStrategy.cross(parentChromosome1, parentChromosome2),
                crossOverStrategy.cross(parentChromosome1, parentChromosome2)
        );
    }

//    public Population<G, C> cross(final Population<G, C> population, final long evolutionIteration) {
//        final Population<G, C> newPopulation = population.copy();
//        final List<Phenotype<G, C>> phenotypes = newPopulation.getPopulation();
//
//        Collections.shuffle(phenotypes, ThreadLocalRandom.current());
//
//        final List<Phenotype<G, C>> result = new ArrayList<>();
//
//        final Iterator<Phenotype<G, C>> iterator = phenotypes.iterator();
//
//        while (iterator.hasNext()) {
//            final Phenotype<G, C> parent1 = iterator.next();
//
//            if (iterator.hasNext()) {
//                final Phenotype<G, C> parent2 = iterator.next();
//
//                if (Math.random() <= crossRate) {
//                    result.add(parent1.newInstance(crossOverStrategy.cross(parent1.getChromosome(), parent2.getChromosome()), evolutionIteration));
//                    result.add(parent1.newInstance(crossOverStrategy.cross(parent2.getChromosome(), parent1.getChromosome()), evolutionIteration));
//                } else {
//                    result.add(parent1);
//                    result.add(parent2);
//                }
//
//            } else {
//                result.add(parent1);
//            }
//        }
//
//        assert result.size() == phenotypes.size();
//
//        return newPopulation.newInstance(result);
//    }
}
