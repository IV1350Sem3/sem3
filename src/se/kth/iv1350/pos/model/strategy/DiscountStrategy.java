package se.kth.iv1350.pos.model.strategy;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.SaleDTO;

/**
 * A strategy for calculating discounts.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount for the specified sale.
     *
     * @param sale The sale information for which to calculate the discount.
     * @param customerID The ID of the customer.
     * @return The discount amount.
     */
    Amount calculateDiscount(SaleDTO sale, String customerID);
}
