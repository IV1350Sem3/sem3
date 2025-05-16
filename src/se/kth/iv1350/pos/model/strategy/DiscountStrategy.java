package se.kth.iv1350.pos.model.strategy;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.Sale;

/**
 * A strategy for calculating discounts.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount for the specified sale.
     *
     * @param sale The sale for which to calculate the discount.
     * @param customerID The ID of the customer.
     * @return The discount amount.
     */
    Amount calculateDiscount(Sale sale, String customerID);
}
