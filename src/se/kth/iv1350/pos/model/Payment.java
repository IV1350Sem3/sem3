package se.kth.iv1350.pos.model;

/**
 * Represents a payment in a sale.
 */
public class Payment {
    private Amount paidAmount;
    private Amount totalPrice;

    /**
     * Creates a new instance.
     *
     * @param paidAmount The amount paid by the customer.
     * @param totalPrice The total price of the sale.
     */
    public Payment(Amount paidAmount, Amount totalPrice) {
        this.paidAmount = paidAmount;
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the amount paid.
     *
     * @return The paid amount.
     */
    public Amount getPaidAmount() {
        return paidAmount;
    }

    /**
     * Gets the total price.
     *
     * @return The total price.
     */
    public Amount getTotalPrice() {
        return totalPrice;
    }

    /**
     * Calculates the change to return to the customer.
     *
     * @return The change.
     */
    public Amount getChange() {
        return paidAmount.subtract(totalPrice);
    }
}
