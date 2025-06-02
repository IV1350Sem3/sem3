package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.observer.TotalRevenueTemplate;

/**
 * Shows the total income of all sales on the user interface.
 */
public class TotalRevenueView extends TotalRevenueTemplate {

    /**
     * Creates a new instance.
     */
    public TotalRevenueView() {
        // Empty constructor
    }

    @Override
    protected void doShowTotalIncome() throws Exception {
        System.out.println("*** DISPLAY: Total revenue is now: " + getTotalRevenue() + " ***");
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Error displaying total revenue: " + e.getMessage());
    }
}
