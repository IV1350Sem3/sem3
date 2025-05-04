package se.kth.iv1350.pos.model;

/**
 * Contains information about a discount.
 */
public class DiscountDTO {
    private final String customerID;
    private final double discountRate;

    /**
     * Creates a new instance.
     *
     * @param customerID The ID of the customer.
     * @param discountRate The discount rate.
     */
    public DiscountDTO(String customerID, double discountRate) {
        this.customerID = customerID;
        this.discountRate = discountRate;
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Gets the discount rate.
     *
     * @return The discount rate.
     */
    public double getDiscountRate() {
        return discountRate;
    }
}
