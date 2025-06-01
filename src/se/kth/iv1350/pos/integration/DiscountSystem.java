package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.DiscountDTO;
import se.kth.iv1350.pos.model.SaleDTO;
import se.kth.iv1350.pos.model.strategy.DiscountStrategy;
import se.kth.iv1350.pos.model.strategy.PercentageDiscountStrategy;
import se.kth.iv1350.pos.model.strategy.FixedAmountDiscountStrategy;

/**
 * Represents the discount system.
 */
public class DiscountSystem {
    private final Map<String, DiscountStrategy> discountStrategies = new HashMap<>();

    public DiscountSystem() {
        discountStrategies.put("12345", new PercentageDiscountStrategy(0.1));
        discountStrategies.put("67890", new PercentageDiscountStrategy(0.2));
        discountStrategies.put("11111", new FixedAmountDiscountStrategy(new Amount(50)));
    }

    public DiscountDTO getDiscount(String customerID, SaleDTO sale) {
        DiscountStrategy strategy = discountStrategies.get(customerID);
        if (strategy == null) {
            return new DiscountDTO(customerID, 0.0);
        }
        Amount discountAmt = strategy.calculateDiscount(sale, customerID);
        double rate = discountAmt.getAmount() / sale.getRunningTotal().getAmount();
        return new DiscountDTO(customerID, rate);
    }
}
