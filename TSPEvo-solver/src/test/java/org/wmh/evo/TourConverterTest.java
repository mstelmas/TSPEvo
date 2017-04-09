package org.wmh.evo;

import org.junit.Test;
import org.wmh.tsp.domain.City;
import org.wmh.tsp.domain.TspTourConverter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TourConverterTest {
    private final TspTourConverter tspTourConverter = new TspTourConverter();

    @Test
    public void shouldConvertTspTourEdgesToCities() {
        List<City> tspCities = tspTourConverter.convert(TspTestTours.TSP_TOUR);

        assertThat(tspCities).hasSize(8);
        assertThat(tspCities).extracting(City::getValue).containsExactly(0, 1, 2, 3, 4, 5, 6, 7);
    }
}
