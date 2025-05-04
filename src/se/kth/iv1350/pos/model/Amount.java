package se.kth.iv1350.pos.model;

/**
 * Represents an immutable amount of money.
 */
public class Amount {
    private final double amount;

    /**
     * Creates a new instance representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Adds the specified amount to this amount.
     *
     * @param other The amount to add.
     * @return The result of the addition.
     */
    public Amount add(Amount other) {
        return new Amount(this.amount + other.amount);
    }

    /**
     * Subtracts the specified amount from this amount.
     *
     * @param other The amount to subtract.
     * @return The result of the subtraction.
     */
    public Amount subtract(Amount other) {
        return new Amount(this.amount - other.amount);
    }

    /**
     * Multiplies this amount with the specified factor.
     *
     * @param factor The factor to multiply with.
     * @return The result of the multiplication.
     */
    public Amount multiply(double factor) {
        return new Amount(this.amount * factor);
    }

    /**
     * Multiplies this amount with the specified quantity.
     *
     * @param quantity The quantity to multiply with.
     * @return The result of the multiplication.
     */
    public Amount multiply(int quantity) {
        return new Amount(this.amount * quantity);
    }

    /**
     * Returns a string representation of the amount.
     *
     * @return The amount as a string.
     */
    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }
}
