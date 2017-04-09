package org.wmh.evo;

import org.junit.Test;
import org.wmh.tsp.domain.Tour;
import org.wmh.tsp.domain.TspTourConverter;
import org.wmh.tsp.exceptions.InvalidTspTourException;
import org.wmh.graph.Edge;

import java.util.Collections;

import static org.wmh.evo.TspTestTours.ACYCLIC_TSP_TOUR;
import static org.wmh.evo.TspTestTours.BROKEN_TSP_TOUR;

public class TourTest {
    private static final TspTourConverter tspTourConverter = new TspTourConverter();

    @Test(expected = InvalidTspTourException.class)
    public void shouldThrowIfBrokenInvalidTspTour() throws Exception {
        Tour.of(tspTourConverter.convert(BROKEN_TSP_TOUR));
    }

    @Test(expected = InvalidTspTourException.class)
    public void shouldThrowIfTspTourIsAcyclic() throws Exception {
        Tour.of(tspTourConverter.convert(ACYCLIC_TSP_TOUR));
    }

    @Test(expected = InvalidTspTourException.class)
    public void shouldThrowIfInvalidTspTourLength() throws Exception {
        Tour.of(tspTourConverter.convert(Collections.singletonList(new Edge(1, 1, 2))));
    }
}
