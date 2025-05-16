package se.kth.iv1350.pos.model.observer;

import se.kth.iv1350.pos.model.Amount;

/**
 * A listener interface for receiving notifications about total revenue changes.
 */
public interface TotalRevenueObserver {
    /**
     * Called when the total revenue has been updated.
     *
     * @param totalRevenue The new total revenue.
     */
    void updateTotalRevenue(Amount totalRevenue);
}
