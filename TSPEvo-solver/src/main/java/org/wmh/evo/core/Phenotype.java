package org.wmh.evo.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class Phenotype<T extends Gene<?, T>, C extends Comparable<? super C>> implements Comparable<Phenotype<T, C>>, Runnable {

    private final Function<? super Chromosome<T>, ? extends C> fitnessFunction;

    @Getter private final Chromosome<T> chromosome;
    @Getter private final long generation;

    private boolean alreadyEvaluated = false;
    private C fitness;

    public C getFitness() {
        evaluate();
        return fitness;
    }

    public Phenotype<T, C> newInstance(final Chromosome<T> chromosome) {
        return new Phenotype<>(fitnessFunction, chromosome, generation);
    }

    private void evaluate() {
        if (!alreadyEvaluated) {
            fitness = fitnessFunction.apply(chromosome);
            alreadyEvaluated = true;
        }
    }

    @Override
    public int compareTo(Phenotype<T, C> o) {
        return 0;
    }

    @Override
    public void run() {
        evaluate();
    }
}
