package se.kth.iv1350.pos.integration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.pos.model.ItemNotFoundException;

public class InventorySystemTest {
    private InventorySystem inventorySystem;

    @Before
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    @Test
    public void testGetExistingItem() throws ItemNotFoundException {
        assertNotNull("Should find milk", inventorySystem.getItem("milk"));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetNonExistingItem() throws ItemNotFoundException {
        inventorySystem.getItem("nonexistent");
    }

    @Test(expected = DatabaseFailureException.class)
    public void testDatabaseFailure() throws ItemNotFoundException {
        inventorySystem.getItem("database-failure");
    }
}
