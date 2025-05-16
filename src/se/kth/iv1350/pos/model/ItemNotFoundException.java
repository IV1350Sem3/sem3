package se.kth.iv1350.pos.model;

/**
 * Thrown when searching for an item identifier that doesn't exist in the inventory catalog.
 */
public class ItemNotFoundException extends Exception {
    private final String itemIdentifier;

    /**
     * Creates a new instance with a message specifying which item wasn't found.
     *
     * @param itemIdentifier The identifier of the item that wasn't found.
     */
    public ItemNotFoundException(String itemIdentifier) {
        super("Could not find item with identifier: " + itemIdentifier);
        this.itemIdentifier = itemIdentifier;
    }

    /**
     * @return The identifier of the item that wasn't found.
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }
}
