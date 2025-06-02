package se.kth.iv1350.pos.util;

/**
 * Demonstrates the inheritance vs composition adapters.
 */
public class RandomAdapterDemo {

    public static void main(String[] args) {
        System.out.println("=== Random Adapter Demo ===\n");

        // Test inheritance-based adapter
        System.out.println("--- Inheritance-based adapter ---");
        EnhancedRandomInheritance inheritanceRandom = new EnhancedRandomInheritance(42);

        System.out.println("Random int between 1-10: " + inheritanceRandom.nextIntBetween(1, 10));
        System.out.println("Random double between 0.5-1.5: " + inheritanceRandom.nextDoubleBetween(0.5, 1.5));
        System.out.println("Random boolean (70% true): " + inheritanceRandom.nextBooleanWithProbability(0.7));
        System.out.println("Original nextInt(): " + inheritanceRandom.nextInt(100));

        // Test composition-based adapter
        System.out.println("\n--- Composition-based adapter ---");
        EnhancedRandomComposition compositionRandom = new EnhancedRandomComposition(42);

        System.out.println("Random int between 1-10: " + compositionRandom.nextIntBetween(1, 10));
        System.out.println("Random double between 0.5-1.5: " + compositionRandom.nextDoubleBetween(0.5, 1.5));
        System.out.println("Random boolean (70% true): " + compositionRandom.nextBooleanWithProbability(0.7));
        System.out.println("Original nextInt(): " + compositionRandom.nextInt(100));
    }
}
