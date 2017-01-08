package org.wmh.evo;

import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

public interface FitnessEvaluator<G extends Gene<?, G>> {
    double evaluate(final Chromosome<G> chromosome);
}
