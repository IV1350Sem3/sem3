package se.kth.iv1350.pos.model;

import java.util.List;

/**
 * Contains information about a sale for data transfer between layers.
 */
public class SaleDTO {
    private final List<ItemDTO> items;
    private final Amount runningTotal;
    private final ItemDTO lastAddedItem;

    /**
     * Creates a new instance.
     *
     * @param items The items in the sale.
     * @param runningTotal The running total of the sale.
     * @param lastAddedItem The last item added to the sale (can be null).
     */
    public SaleDTO(List<ItemDTO> items, Amount runningTotal, ItemDTO lastAddedItem) {
        this.items = items;
        this.runningTotal = runningTotal;
        this.lastAddedItem = lastAddedItem;
    }

    /**
     * Gets the items in the sale.
     *
     * @return The items.
     */
    public List<ItemDTO> getItems() {
        return items;
    }

    /**
     * Gets the running total of the sale.
     *
     * @return The running total.
     */
    public Amount getRunningTotal() {
        return runningTotal;
    }

    /**
     * Gets the last added item.
     *
     * @return The last added item, or null if no item was recently added.
     */
    public ItemDTO getLastAddedItem() {
        return lastAddedItem;
    }
}
