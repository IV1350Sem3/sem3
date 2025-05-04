package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Sale;

/**
 * Represents the inventory system.
 */
public class InventorySystem {
    private Map<String, ItemDTO> inventory;

    /**
     * Creates a new instance with some dummy items.
     */
    public InventorySystem() {
        inventory = new HashMap<>();
        inventory.put("milk", new ItemDTO("milk", "Milk 1L", new Amount(10.90), 0.12));
        inventory.put("bread", new ItemDTO("bread", "Whole Grain Bread", new Amount(25.90), 0.12));
        inventory.put("cheese", new ItemDTO("cheese", "Cheese 500g", new Amount(69.90), 0.12));
    }

    /**
     * Gets information about an item.
     *
     * @param itemIdentifier The item identifier.
     * @return Information about the item, or null if the item was not found.
     */
    public ItemDTO getItem(String itemIdentifier) {
        return inventory.get(itemIdentifier);
    }

    /**
     * Updates the inventory with the sold items.
     *
     * @param sale The sale containing the sold items.
     */
    public void updateInventory(Sale sale) {
        // This would update the inventory in a real system
        System.out.println("Inventory updated");
    }
}
