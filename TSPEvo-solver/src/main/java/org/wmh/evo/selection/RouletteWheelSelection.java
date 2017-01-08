package org.wmh.evo.selection;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.Gene;
import org.wmh.evo.core.Phenotype;
import org.wmh.evo.core.Population;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class RouletteWheelSelection<T extends Gene<?, T>, C extends Comparable<? super C>> implements SelectionStrategy<T, C> {
    private static final Random RANDOM = new Random();

    @Override
    public Population<T, C> select(Population<T, C> population) {
        final Population<T, C> copiedPopulation = population.copy();
        copiedPopulation.sort();

        final List<Phenotype<T, C>> offspringPopulation = IntStream.range(0, population.size())
                .mapToObj(i -> rouletteWheelPick(copiedPopulation))
                .collect(Collectors.toList());

        return population.newInstance(offspringPopulation);
    }

    public Phenotype<T, C> rouletteWheelPick(final Population<T, C> population) {
        return population.getPhenotypeAt(RANDOM.nextInt(population.size()));
    }
}
