package se.kth.iv1350.pos.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.model.ItemNotFoundException;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller(RegistryCreator.getInstance());
        controller.startNewSale();
    }

    @Test
    public void testEnterExistingItem() throws ItemNotFoundException, OperationFailedException {
        String result = controller.enterItem("milk");
        assertTrue("Result should contain milk", result.contains("milk"));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testEnterNonExistingItem() throws ItemNotFoundException, OperationFailedException {
        controller.enterItem("nonexistent");
    }

    @Test(expected = OperationFailedException.class)
    public void testDatabaseFailure() throws ItemNotFoundException, OperationFailedException {
        controller.enterItem("database-failure");
    }
}
