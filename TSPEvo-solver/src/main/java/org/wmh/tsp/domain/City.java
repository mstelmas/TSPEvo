package org.wmh.tsp.domain;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.wmh.evo.core.Gene;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class City implements Gene<Integer, City> {
    private final Integer city;

    @Override
    public Integer getValue() {
        return city;
    }

    @Override
    public City newInstance(@NonNull final Integer value) {
        return new City(value);
    }

    @Override
    public City copy() {
        return newInstance(city);
    }

    public static City of(@NonNull final Integer value) {
        return new City(value);
    }
}
