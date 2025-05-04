package se.kth.iv1350.pos.integration;

/**
 * Creates instances of external systems.
 */
public class RegistryCreator {
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private DiscountSystem discountSystem;
    private Printer printer;

    /**
     * Creates a new instance.
     */
    public RegistryCreator() {
        inventorySystem = new InventorySystem();
        accountingSystem = new AccountingSystem();
        discountSystem = new DiscountSystem();
        printer = new Printer();
    }

    /**
     * Gets the inventory system.
     *
     * @return The inventory system.
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Gets the accounting system.
     *
     * @return The accounting system.
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Gets the discount system.
     *
     * @return The discount system.
     */
    public DiscountSystem getDiscountSystem() {
        return discountSystem;
    }

    /**
     * Gets the printer.
     *
     * @return The printer.
     */
    public Printer getPrinter() {
        return printer;
    }
}
