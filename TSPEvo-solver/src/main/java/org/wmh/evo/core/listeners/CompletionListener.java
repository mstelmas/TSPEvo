package org.wmh.evo.core.listeners;


import org.wmh.evo.core.domain.Gene;
import org.wmh.evo.core.domain.Population;

public interface CompletionListener<T extends Gene<?, T>, C extends Comparable<? super C>> {
    void onFinish(final Population<T, C> population);
}
