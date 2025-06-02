package se.kth.iv1350.pos.util;

import java.util.Random;

/**
 * Adapter for Random using inheritance.
 * Adds functionality to generate random numbers within specific ranges.
 */
public class EnhancedRandomInheritance extends Random {

    /**
     * Creates a new instance.
     */
    public EnhancedRandomInheritance() {
        super();
    }

    /**
     * Creates a new instance with specified seed.
     */
    public EnhancedRandomInheritance(long seed) {
        super(seed);
    }

    /**
     * Generates a random number between min and max (inclusive).
     */
    public int nextIntBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random double between min and max.
     */
    public double nextDoubleBetween(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return nextDouble() * (max - min) + min;
    }

    /**
     * Generates a random boolean with specified probability of being true.
     */
    public boolean nextBooleanWithProbability(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("probability must be between 0.0 and 1.0");
        }
        return nextDouble() < probability;
    }
}
