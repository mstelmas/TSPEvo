package org.wmh;

import org.apache.commons.lang3.StringUtils;
import org.wmh.graph.Edge;
import org.wmh.graph.WeightedUndirectedGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphImporter {
    public static WeightedUndirectedGraph fromFullMatrix(final String path) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(path));

        final WeightedUndirectedGraph weightedUndirectedGraph = new WeightedUndirectedGraph(lines.size());

        for (int i = 0; i < lines.size(); i++) {
            final List<Integer> distances = numericalRow(lines.get(i));

            for (int j = i + 1; j < lines.size(); j++) {
                weightedUndirectedGraph.addEdge(new Edge(i, j, distances.get(j)));
            }
        }

        return weightedUndirectedGraph;
    }

    public static WeightedUndirectedGraph fromLowerHalfMatrix(final String path, final int v) throws IOException {
        final WeightedUndirectedGraph weightedUndirectedGraph = new WeightedUndirectedGraph(v);

        final String collect = Files.readAllLines(Paths.get(path)).stream()
                .collect(Collectors.joining(" "));
        final List<Integer> distances = numericalRow(collect);

        int currentPos = 0;

        for (int row = 0; row < v; row++) {
            final List<Integer> p = distances.subList(currentPos, currentPos + row + 1);

            for (int i = 0; i < p.size(); i++) {
                weightedUndirectedGraph.addEdge(new Edge(row, i, p.get(i)));
            }

            currentPos += p.size();
        }

        return weightedUndirectedGraph;
    }

    public static WeightedUndirectedGraph fromUpperHalfMatrix(final String path, final int v) throws IOException {
        final WeightedUndirectedGraph weightedUndirectedGraph = new WeightedUndirectedGraph(v);

        final String collect = Files.readAllLines(Paths.get(path)).stream()
                .collect(Collectors.joining(" "));
        final List<Integer> distances = numericalRow(collect);

        int currentPos = 0;

        for (int row = 0; row < v; row++) {
            final List<Integer> p = distances.subList(currentPos, currentPos + v - row);

            for (int i = 0; i < p.size(); i++) {
                weightedUndirectedGraph.addEdge(new Edge(row, row + i, p.get(i)));
            }

            currentPos += p.size();
        }

        return weightedUndirectedGraph;
    }

    private static List<Integer> numericalRow(final String row) {
        return Arrays.stream(row.trim().split(" "))
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
