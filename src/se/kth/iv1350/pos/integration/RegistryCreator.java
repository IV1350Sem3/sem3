package se.kth.iv1350.pos.integration;

/**
 * Creates instances of external systems. This class is a singleton.
 */
public class RegistryCreator {
    private static RegistryCreator instance;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private DiscountSystem discountSystem;
    private Printer printer;

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private RegistryCreator() {
        inventorySystem = new InventorySystem();
        accountingSystem = new AccountingSystem();
        discountSystem = new DiscountSystem();
        printer = new Printer();
    }

    /**
     * Gets the single instance of this class.
     *
     * @return The single instance of this class.
     */
    public static RegistryCreator getInstance() {
        if (instance == null) {
            instance = new RegistryCreator();
        }
        return instance;
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
