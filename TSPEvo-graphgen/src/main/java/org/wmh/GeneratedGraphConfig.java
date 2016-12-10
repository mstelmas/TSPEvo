package org.wmh;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class GeneratedGraphConfig {
    private String outputFileName;
    private int vertices;
    private int edges;
}
