package org.wmh.generators.factories;

import org.wmh.generators.AbstractGraphGenerator;
import org.wmh.generators.GraphType;

public interface AbstractGraphGeneratorsFactory {
    AbstractGraphGenerator buildGraphGenerator(final GraphType graphType);
}
