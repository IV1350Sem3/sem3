package se.kth.iv1350.pos.model.observer;

import se.kth.iv1350.pos.model.SaleDTO;

/**
 * Observer for Sale state changes.
 */
public interface SaleObserver {
    /**
     * Called whenever the Sale’s state changes.
     * @param saleDto A snapshot of the Sale.
     */
    void saleUpdated(SaleDTO saleDto);
}
