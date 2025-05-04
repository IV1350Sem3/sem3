package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Contains information for a receipt.
 */
public class ReceiptDTO {
    private final LocalDateTime saleTime;
    private final List<ItemDTO> items;
    private final Amount totalPrice;
    private final DiscountDTO discount;

    /**
     * Creates a new instance.
     *
     * @param saleTime The time of the sale.
     * @param items The items that were sold.
     * @param totalPrice The total price of the sale.
     * @param discount The applied discount, if any.
     */
    public ReceiptDTO(LocalDateTime saleTime, List<ItemDTO> items, Amount totalPrice, DiscountDTO discount) {
        this.saleTime = saleTime;
        this.items = items;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    /**
     * Creates a string representation of the receipt.
     *
     * @return The receipt as a string.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RECEIPT\n");
        builder.append("Time: ").append(saleTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n\n");

        builder.append("ITEMS\n");
        for (ItemDTO item : items) {
            builder.append(item.getName()).append(", ")
                    .append(item.getQuantity()).append(" x ")
                    .append(item.getPrice()).append(" = ")
                    .append(item.getPrice().multiply(item.getQuantity())).append("\n");
        }

        builder.append("\nTOTAL: ").append(totalPrice).append("\n");

        if (discount != null) {
            builder.append("Discount: ").append(discount.getDiscountRate() * 100).append("%\n");
            Amount discountAmount = totalPrice.multiply(discount.getDiscountRate());
            Amount totalAfterDiscount = totalPrice.subtract(discountAmount);
            builder.append("Total after discount: ").append(totalAfterDiscount).append("\n");
        }

        return builder.toString();
    }
}
