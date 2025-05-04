package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one sale transaction.
 */
public class Sale {
    private LocalDateTime saleTime;
    private List<ItemDTO> items;
    private Amount runningTotal;
    private DiscountDTO discount;

    /**
     * Creates a new instance and sets the sale time.
     */
    public Sale() {
        this.saleTime = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.runningTotal = new Amount(0);
    }

    /**
     * Adds an item to the sale.
     *
     * @param item The item to add.
     */
    public void addItem(ItemDTO item) {
        boolean itemExists = false;
        for (ItemDTO existingItem : items) {
            if (existingItem.getIdentifier().equals(item.getIdentifier())) {
                existingItem.increaseQuantity();
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            items.add(item);
        }

        updateRunningTotal();
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
     * Gets the total price of the sale.
     *
     * @return The total price.
     */
    public Amount getTotal() {
        return runningTotal;
    }

    /**
     * Applies discount to the sale.
     *
     * @param discount The discount to apply.
     * @return The total after discount.
     */
    public Amount applyDiscount(DiscountDTO discount) {
        this.discount = discount;
        Amount discountAmount = runningTotal.multiply(discount.getDiscountRate());
        return runningTotal.subtract(discountAmount);
    }

    /**
     * Generates a receipt for the sale.
     *
     * @return The receipt.
     */
    public ReceiptDTO generateReceipt() {
        return new ReceiptDTO(saleTime, items, runningTotal, discount);
    }

    private void updateRunningTotal() {
        runningTotal = new Amount(0);
        for (ItemDTO item : items) {
            Amount itemTotal = item.getPrice().multiply(item.getQuantity());
            runningTotal = runningTotal.add(itemTotal);
        }
    }
}
