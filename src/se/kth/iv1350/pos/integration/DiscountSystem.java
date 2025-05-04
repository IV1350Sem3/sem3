package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.pos.model.DiscountDTO;
import se.kth.iv1350.pos.model.Sale;

/**
 * Represents the discount system.
 */
public class DiscountSystem {
    private Map<String, Double> discounts;

    /**
     * Creates a new instance with some dummy discounts.
     */
    public DiscountSystem() {
        discounts = new HashMap<>();
        discounts.put("12345", 0.1); // 10% discount
        discounts.put("67890", 0.2); // 20% discount
    }

    /**
     * Gets the discount for a customer.
     *
     * @param customerID The ID of the customer.
     * @param sale The current sale.
     * @return The discount for the customer.
     */
    public DiscountDTO getDiscount(String customerID, Sale sale) {
        Double discountRate = discounts.get(customerID);
        if (discountRate == null) {
            discountRate = 0.0;
        }
        return new DiscountDTO(customerID, discountRate);
    }
}
