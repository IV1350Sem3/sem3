package se.kth.iv1350.pos.model;

/**
 * Contains information about one item.
 */
public class ItemDTO {
    private final String identifier;
    private final String name;
    private final Amount price;
    private final double vatRate;
    private int quantity;

    /**
     * Creates a new instance.
     *
     * @param identifier The item identifier.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param vatRate The VAT rate of the item.
     */
    public ItemDTO(String identifier, String name, Amount price, double vatRate) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = 1;
    }

    /**
     * Gets the item identifier.
     *
     * @return The item identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price.
     */
    public Amount getPrice() {
        return price;
    }

    /**
     * Gets the VAT rate of the item.
     *
     * @return The VAT rate.
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increases the quantity by one.
     */
    public void increaseQuantity() {
        quantity++;
    }
}
