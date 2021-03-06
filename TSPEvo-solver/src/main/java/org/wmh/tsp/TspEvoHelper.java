package org.wmh.tsp;

import lombok.NonNull;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.graph.AbstractGraph;
import org.wmh.graph.Edge;
import org.wmh.tsp.domain.City;
import org.wmh.tsp.exceptions.InvalidTspTourException;

import java.util.List;
import java.util.stream.IntStream;

public class TspEvoHelper {
    private final long graphEdgeWeights[][];

    private TspEvoHelper(final AbstractGraph graph) {
        this.graphEdgeWeights = buildEdgeWeightMatrix(graph);
    }

    public static TspEvoHelper with(@NonNull final AbstractGraph graph) {
        return new TspEvoHelper(graph);
    }

    public long calculatePathLength(final Chromosome<? extends City> chromosome) {
        long pathLength = 0;

        for (int i = 0; i < chromosome.length(); i++) {
            final Integer pathVertex1 = chromosome.getGeneAt(i).getValue();
            final Integer pathVertex2 = chromosome.getGeneAt((i + 1) % chromosome.length()).getValue();
            pathLength += graphEdgeWeights[pathVertex1][pathVertex2];
        }

        return pathLength;
    }

    public static long[][] buildEdgeWeightMatrix(@NonNull final AbstractGraph graph) {
        final int V = graph.getV();
        final long[][] graphEdgeWeights = new long[V][V];

        IntStream.range(0, V).forEach(i -> graph.adj(i).forEach(edge ->
                graphEdgeWeights[edge.first()][edge.second()] = graphEdgeWeights[edge.second()][edge.first()] = edge.getWeight()));

        return graphEdgeWeights;
    }

    public static long calculatePathLength(final List<Edge> edgesPath) {
        return edgesPath.stream().mapToLong(Edge::getWeight).sum();
    }

    public static void validateTspTour(final List<Edge> tspTour) {
        checkForMinCycleLength(tspTour);
        checkForAcyclicTour(tspTour);
        checkForBrokenTour(tspTour);
    }

    private static void checkForBrokenTour(final List<Edge> tspTour) {
        for (int i = 0; i < tspTour.size() - 1; i++) {
            final Edge edge = tspTour.get(i);
            final Edge nextEdge = tspTour.get(i + 1);

            if (edge.second() != nextEdge.first()) {
                throw new InvalidTspTourException();
            }
        }
    }

    private static void checkForMinCycleLength(final List<Edge> tspTour) {
        if (tspTour.size() <= 1) {
            throw new InvalidTspTourException();
        }
    }

    private static void checkForAcyclicTour(final List<Edge> tspTour) {
        final Edge firstEdgeOfATour = tspTour.get(0);
        final Edge lastEdgeOfATour = tspTour.get(tspTour.size() - 1);

        if (firstEdgeOfATour.first() != lastEdgeOfATour.second()) {
            throw new InvalidTspTourException();
        }
    }
}
