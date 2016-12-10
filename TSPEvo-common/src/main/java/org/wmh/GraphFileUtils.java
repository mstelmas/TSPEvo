package org.wmh;

import javaslang.control.Try;
import org.wmh.graph.AbstractGraph;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphFileUtils {
    public static Try<Void> exportToTextFile(final AbstractGraph graph, final String fileName) {
        return Try.run(() -> Files.write(Paths.get(fileName), graph.toString().getBytes()));
    }
}
