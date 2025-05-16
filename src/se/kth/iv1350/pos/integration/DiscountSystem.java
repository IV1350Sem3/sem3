package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.DiscountDTO;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.strategy.DiscountStrategy;
import se.kth.iv1350.pos.model.strategy.PercentageDiscountStrategy;
import se.kth.iv1350.pos.model.strategy.FixedAmountDiscountStrategy;

/**
 * Represents the discount system.
 */
public class DiscountSystem {
    private Map<String, DiscountStrategy> discountStrategies;

    /**
     * Creates a new instance with some dummy discounts.
     */
    public DiscountSystem() {
        discountStrategies = new HashMap<>();
        discountStrategies.put("12345", new PercentageDiscountStrategy(0.1)); // 10% discount
        discountStrategies.put("67890", new PercentageDiscountStrategy(0.2)); // 20% discount
        discountStrategies.put("11111", new FixedAmountDiscountStrategy(new Amount(50))); // 50 fixed discount
    }

    /**
     * Gets the discount for a customer.
     *
     * @param customerID The ID of the customer.
     * @param sale The current sale.
     * @return The discount for the customer.
     */
    public DiscountDTO getDiscount(String customerID, Sale sale) {
        DiscountStrategy strategy = discountStrategies.get(customerID);
        if (strategy == null) {
            return new DiscountDTO(customerID, 0.0);
        }

        Amount discountAmount = strategy.calculateDiscount(sale, customerID);
        double discountRate = discountAmount.getAmount() / sale.getTotal().getAmount();
        return new DiscountDTO(customerID, discountRate);
    }
}
