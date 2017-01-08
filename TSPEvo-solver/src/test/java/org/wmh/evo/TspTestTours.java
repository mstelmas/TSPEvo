package org.wmh.evo;

import com.google.common.collect.ImmutableList;
import org.wmh.tsp.domain.City;
import org.wmh.graph.Edge;

import java.util.List;

public class TspTestTours {
    public static final List<Edge> TSP_TOUR = ImmutableList.of(
            new Edge(0, 1, 1), new Edge(1, 2, 2), new Edge(2, 3, 3),
            new Edge(3, 4, 4), new Edge(4, 5, 5), new Edge(5, 6, 6),
            new Edge(6, 7, 7), new Edge(7, 0, 8)
    );

    public static final List<City> TSP_TOUR_CITIES = ImmutableList.of(
            City.of(0), City.of(1), City.of(2), City.of(3),
            City.of(4), City.of(5), City.of(6), City.of(7)
    );

    public static final List<Edge> BROKEN_TSP_TOUR = ImmutableList.of(
            new Edge(0, 1, 1), new Edge(1, 2, 2), new Edge(2, 3, 3),
            new Edge(3, 4, 4), new Edge(4, 6, 5), new Edge(5, 6, 6),
            new Edge(6, 7, 7), new Edge(7, 0, 8)
    );

    public static final List<Edge> ACYCLIC_TSP_TOUR = ImmutableList.of(
            new Edge(0, 1, 1), new Edge(1, 2, 2), new Edge(2, 3, 3),
            new Edge(3, 4, 4), new Edge(4, 5, 5), new Edge(5, 6, 6),
            new Edge(6, 7, 7), new Edge(7, 2, 8)
    );
}
