package se.kth.iv1350.pos.model;

/**
 * Represents the cash register.
 */
public class CashRegister {
    private Amount balance;

    /**
     * Creates a new instance with an initial balance of zero.
     */
    public CashRegister() {
        this.balance = new Amount(0);
    }

    /**
     * Updates the balance with the paid amount.
     *
     * @param payment The payment that was made.
     */
    public void addPayment(Payment payment) {
        balance = balance.add(payment.getTotalPrice());
    }

    /**
     * Gets the current balance.
     *
     * @return The current balance.
     */
    public Amount getBalance() {
        return balance;
    }
}
