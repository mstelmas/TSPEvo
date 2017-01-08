package org.wmh;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.EvoSolver;
import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Phenotype;
import org.wmh.evo.crossing.Crosser;
import org.wmh.evo.crossing.ModifiedCrossOver;
import org.wmh.evo.mutation.Mutator;
import org.wmh.evo.mutation.SwapMutation;
import org.wmh.evo.selection.RouletteWheelSelection;
import org.wmh.evo.selection.Selector;
import org.wmh.graph.AbstractGraph;
import org.wmh.graph.Edge;
import org.wmh.graph.WeightedUndirectedGraph;
import org.wmh.tsp.TspEvoHelper;
import org.wmh.tsp.domain.City;
import org.wmh.tsp.domain.RandomTspPopulationProvider;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {
    public static final int SOLVER_ITERATIONS = 20;

    // TODO: "Custom" executor service
    public static void main(String[] args) throws Exception {
//        final AbstractGraph graph = GraphImporter.importGraph("/home/code/TSPEvo/examples/bays29.txt");
        final AbstractGraph graph = buildSimpleTempGraph();
        final TspEvoHelper tspEvoHelper = TspEvoHelper.with(graph);

        final List<CompletableFuture<Phenotype<City, Double>>> solutionFutures = IntStream.range(0, SOLVER_ITERATIONS)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> solve(graph)))
                .collect(Collectors.toList());

        final Pair<Chromosome<City>, Long> optimalSolution = asProcessedSequence(solutionFutures)
                .thenApply(phenotypes -> phenotypes.stream()
                        .map(solution -> Pair.of(solution.getChromosome(), tspEvoHelper.calculatePathLength(solution.getChromosome())))
                        .sorted(Comparator.comparing(Pair::getValue))
                        .findFirst()
                        .get()
                ).get();

        System.out.println(String.format("Optimal TSP path has length: %d", optimalSolution.getValue()));
        System.out.println(optimalSolution.getKey());
    }

    private static<T> CompletableFuture<List<T>> asProcessedSequence(final List<CompletableFuture<T>> taskFutures) {
        return CompletableFuture.allOf(taskFutures.toArray(new CompletableFuture[taskFutures.size()]))
                .thenApply(v -> taskFutures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
    }

    private static Phenotype<City, Double> solve(@NonNull final AbstractGraph graph) {
        return EvoSolver.<City, Double>builder()
                .withPopulationSize(50)
                .withSelection(new Selector<>(new RouletteWheelSelection<City, Double>()))
                .withCrossover(new Crosser<>(new ModifiedCrossOver<City>(), 0.35))
                .withMutation(new Mutator<>(new SwapMutation<City>(), 0.1))
                .withPopulationGenerator(new RandomTspPopulationProvider<>(graph))
                .build()
                .solve();
    }

    private static AbstractGraph buildSimpleTempGraph() {
        final AbstractGraph g = new WeightedUndirectedGraph(5);
        g.addEdge(new Edge(0, 1, 2));
        g.addEdge(new Edge(0, 2, 3));
        g.addEdge(new Edge(0, 3, 2));
        g.addEdge(new Edge(0, 4, 3));

        g.addEdge(new Edge(1, 2, 3));
        g.addEdge(new Edge(1, 3, 4));
        g.addEdge(new Edge(1, 4, 1));

        g.addEdge(new Edge(2, 3, 2));
        g.addEdge(new Edge(2, 4, 4));

        g.addEdge(new Edge(3, 4, 5));
        return g;
    }
}
