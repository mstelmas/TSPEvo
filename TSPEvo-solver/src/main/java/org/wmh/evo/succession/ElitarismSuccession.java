package org.wmh.evo.succession;

import lombok.RequiredArgsConstructor;
import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Phenotype;
import org.wmh.evo.core.domain.Population;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ElitarismSuccession<T extends Gene<?, T>, C extends Number & Comparable<? super C>> implements SuccessionStrategy<T, C> {
    private final int k;

    @Override
    public Population<T, C> join(final Population<T, C> initialPopulation, final Population<T, C> offspringsPopulation) {

        final List<Phenotype<T, C>> collect1 = initialPopulation.stream()
                .sorted()
                .limit(k)
                .collect(Collectors.toList());

        final List<Phenotype<T, C>> collect = offspringsPopulation.stream()
                .sorted()
                .limit(initialPopulation.size() - k)
                .collect(Collectors.toList());


        collect1.addAll(collect);


        return initialPopulation.newInstance(collect1);
    }
}
