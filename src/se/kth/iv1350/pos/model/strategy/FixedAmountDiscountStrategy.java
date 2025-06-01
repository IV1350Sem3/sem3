package se.kth.iv1350.pos.model.strategy;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.SaleDTO;

/**
 * A discount strategy that applies a fixed amount discount.
 */
public class FixedAmountDiscountStrategy implements DiscountStrategy {
    private Amount discountAmount;

    /**
     * Creates a new instance with the specified fixed discount amount.
     *
     * @param discountAmount The fixed discount amount.
     */
    public FixedAmountDiscountStrategy(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Amount calculateDiscount(SaleDTO sale, String customerID) {
        return discountAmount;
    }
}
