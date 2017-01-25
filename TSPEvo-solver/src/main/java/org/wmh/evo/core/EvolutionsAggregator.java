package org.wmh.evo.core;

import lombok.NonNull;
import org.wmh.evo.core.domain.Gene;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO: synch?
public class EvolutionsAggregator<T extends Gene<?, T>, C extends Comparable<? super C>> {
    private final Map<Integer, EvolutionStage<T, C>> evolutionStages = new HashMap<>();

    public void addEvolutionAtStage(@NonNull final EvolutionStage<T, C> evolutionStage) {
        final int stage = evolutionStage.getStage();
        if (stage < 0) {
            throw new IllegalStateException("Stage number cannot be negative");
        }

        getEvolutionAtStage(stage).ifPresent(es -> System.out.println("Evolution at stage: " + stage + " is present. It will be overwritten"));

        evolutionStages.put(stage, evolutionStage);
    }

    public Optional<EvolutionStage<T, C>> getEvolutionAtStage(final int stage) {
        if (stage > evolutionStages.size() || stage < 0) {
            throw new IllegalStateException("Requested stage number exceeds available stages");
        }

        return Optional.ofNullable(evolutionStages.get(stage));
    }

    public Optional<EvolutionStage<T, C>> getLastEvolutionStage() {
        return getEvolutionAtStage(getCount() - 1);
    }

    public int getCount() {
        return evolutionStages.size();
    }
}
