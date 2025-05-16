package se.kth.iv1350.pos.model.strategy;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.Sale;

/**
 * A discount strategy that applies a percentage discount.
 */
public class PercentageDiscountStrategy implements DiscountStrategy {
    private double discountPercentage;

    /**
     * Creates a new instance with the specified discount percentage.
     *
     * @param discountPercentage The discount percentage (0.1 for 10%).
     */
    public PercentageDiscountStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Amount calculateDiscount(Sale sale, String customerID) {
        return sale.getTotal().multiply(discountPercentage);
    }
}
