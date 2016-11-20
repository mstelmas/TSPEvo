package org.wmh;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

import org.apache.commons.lang3.Range;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EdgeWeightGenerator {
    private static final Random random = new Random();

    public static double uniform() {
        return random.nextDouble();
    }

    public static int uniform(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Upper bound of the uniform interval must be positive");
        }

        return random.nextInt(n);
    }

    public static int uniform(final int a, final int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range for uniform interval");
        }

        return a + uniform(b - a);
    }

    public static int uniform(final Range<Integer> range) {
        return range.getMinimum() + uniform(range.getMaximum() - range.getMinimum());
    }
}
