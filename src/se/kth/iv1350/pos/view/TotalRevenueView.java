package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.observer.TotalRevenueObserver;

/**
 * Shows the total income of all sales on the user interface.
 */
public class TotalRevenueView implements TotalRevenueObserver {

    /**
     * Creates a new instance.
     */
    public TotalRevenueView() {
        // Empty constructor
    }

    @Override
    public void updateTotalRevenue(Amount totalRevenue) {
        System.out.println("*** DISPLAY: Total revenue is now: " + totalRevenue + " ***");
    }
}
