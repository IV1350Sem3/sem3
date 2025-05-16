package se.kth.iv1350.pos.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.ItemNotFoundException;

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
     * @return Information about the item.
     * @throws ItemNotFoundException if the item with the specified identifier was not found.
     * @throws DatabaseFailureException if the database could not be called.
     */
    public ItemDTO getItem(String itemIdentifier) throws ItemNotFoundException {
        // Simulate database failure for a specific hardcoded item
        if (itemIdentifier.equals("database-failure")) {
            throw new DatabaseFailureException("Could not connect to inventory database");
        }

        ItemDTO item = inventory.get(itemIdentifier);
        if (item == null) {
            throw new ItemNotFoundException(itemIdentifier);
        }
        return item;
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

