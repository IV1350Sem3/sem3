package se.kth.iv1350.pos.model.observer;

import se.kth.iv1350.pos.model.Amount;

/**
 * Abstract template class implementing the Template Method pattern for total revenue observers.
 */
public abstract class TotalRevenueTemplate implements TotalRevenueObserver {
    private Amount totalRevenue = new Amount(0);

    @Override
    public void updateTotalRevenue(Amount totalRevenue) {
        calculateTotalIncome(totalRevenue); // Calculate the total amount paid since the program started
        showTotalIncome();
    }

    private void calculateTotalIncome(Amount saleAmount) {
        totalRevenue = totalRevenue.add(saleAmount);
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome() throws Exception;
    protected abstract void handleErrors(Exception e);

    protected Amount getTotalRevenue() {
        return totalRevenue;
    }
}
