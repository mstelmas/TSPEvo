package org.wmh.evo;

import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;

public interface FitnessEvaluator<G extends Gene<?, G>> {
    double evaluate(final Chromosome<G> chromosome);
}
