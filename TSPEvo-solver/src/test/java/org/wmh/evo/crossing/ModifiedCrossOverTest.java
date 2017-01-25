package org.wmh.evo.crossing;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.tsp.domain.City;
import org.wmh.tsp.domain.Tour;

import java.util.List;

public class ModifiedCrossOverTest {
    private static final CrossOverStrategy crossOverStrategy = new ModifiedCrossOver();

    public static final List<City> TSP_TOUR_CITIES1 = ImmutableList.of(
            City.of(0), City.of(1), City.of(2), City.of(3),
            City.of(4), City.of(5), City.of(6), City.of(7)
    );

    public static final List<City> TSP_TOUR_CITIES2 = ImmutableList.of(
            City.of(7), City.of(6), City.of(5), City.of(4),
            City.of(3), City.of(2), City.of(1), City.of(0)
    );

    private static final Chromosome<City> CHROMOSOME1 = Tour.of(TSP_TOUR_CITIES1);
    private static final Chromosome<City> CHROMOSOME2 = Tour.of(TSP_TOUR_CITIES2);

    @Test
    public void shouldSwapMutateChromosome() throws Exception {
    }
}