package se.kth.iv1350.pos.util;

import java.util.Random;

/**
 * Adapter for Random using composition.
 * Adds functionality to generate random numbers within specific ranges.
 */
public class EnhancedRandomComposition {
    private final Random random;

    /**
     * Creates a new instance.
     */
    public EnhancedRandomComposition() {
        this.random = new Random();
    }

    /**
     * Creates a new instance with specified seed.
     */
    public EnhancedRandomComposition(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Generates a random number between min and max (inclusive).
     */
    public int nextIntBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Generates a random double between min and max.
     */
    public double nextDoubleBetween(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
        return random.nextDouble() * (max - min) + min;
    }

    /**
     * Generates a random boolean with specified probability of being true.
     */
    public boolean nextBooleanWithProbability(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("probability must be between 0.0 and 1.0");
        }
        return random.nextDouble() < probability;
    }

    // Expose some original Random methods
    public int nextInt() {
        return random.nextInt();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }
}
