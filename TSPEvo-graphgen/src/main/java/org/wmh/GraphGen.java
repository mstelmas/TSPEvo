package org.wmh;

import javaslang.control.Try;
import lombok.RequiredArgsConstructor;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.wmh.generators.GraphType;
import org.wmh.graph.AbstractGraph;
import org.wmh.graph.WeightedUndirectedGraph;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GraphGen {

    private final Map<String, Option> options;

    public GraphGen(final Option[] optionsList) {
        options = Arrays.stream(optionsList).collect(Collectors.toMap(Option::getArgName, Function.identity()));
    }

    public static void main(String[] args) {

        final Options commandLineOptions = buildCommandLineOptions();

        final CommandLineParser commandLineParser = new DefaultParser();

        Try.of(() -> commandLineParser.parse(commandLineOptions, args, true))
                .onFailure((e) -> {
                    System.out.println((String.format("Exception during command line arguments parsing: %s\n", e.getMessage())));
                    System.exit(1);
                })
                .andThen((commandLine -> {
                    new GraphGen(commandLine.getOptions()).generate();
                }));

        final AbstractGraph graph = GraphGeneratorsFactory.weightedUndirectedGraphGeneratorsFactory()
                .buildGraphGenerator(GraphType.COMPLETE)
                .withVertexCount(16)
                .withWeightsInRange(Range.between(1, 100))
                .generate();

        GraphFileUtils.exportToTextFile(graph, "saved_graph.txt")
                .onSuccess((e) -> System.out.println("Saved successfully"))
                .onFailure((e) -> System.out.println("Error while saving"));
    }

    public void generate() {
        buildGeneratedGraphConfigFromOptions()
                .onFailure((e) -> System.out.println("Could not params"))
                .mapTry(this::buildGraphFromConfig)
                .onFailure((e) -> System.out.println("Could not build graph"))
                .andThenTry((generatedGraphWithConfig) -> GraphFileUtils.exportToTextFile(generatedGraphWithConfig.getKey(), generatedGraphWithConfig.getValue().getOutputFileName()));

    }

    private Pair<AbstractGraph, GeneratedGraphConfig> buildGraphFromConfig(final GeneratedGraphConfig generatedGraphConfig) {
        return Pair.of(new WeightedUndirectedGraph(generatedGraphConfig.getVertices()), generatedGraphConfig);
    }

    private Try<GeneratedGraphConfig> buildGeneratedGraphConfigFromOptions() {
        return Try.of(() -> {
            final GeneratedGraphConfig generatedGraphConfig = GeneratedGraphConfig.builder()
                    .outputFileName(options.get("outputFile").getValue())
                    .vertices(Integer.valueOf(options.get("verticesAmount").getValue()))
                    .build();

            Optional.ofNullable(options.get("edgesAmount").getValue())
                    .ifPresent(s -> generatedGraphConfig.setEdges(Integer.valueOf(s)));

            return generatedGraphConfig;
        });
    }

    private static Options buildCommandLineOptions() {
        final Options options = new Options();
        options.addOption(
                Option.builder("o")
                        .longOpt("output-file")
                        .desc("Output file path for a generated graph")
                        .hasArg()
                        .argName("outputFile")
                        .required()
                        .build()
        );

        options.addOption(
                Option.builder("v")
                        .longOpt("vertices")
                        .desc("Number of vertices in generated graph")
                        .hasArg()
                        .argName("verticesAmount")
                        .required()
                        .build()
        );

        options.addOption(
                Option.builder("e")
                        .longOpt("edges")
                        .desc("Number of edges in generated graph")
                        .hasArg()
                        .argName("edgesAmount")
                        .build()
        );
        return options;
    }
}
