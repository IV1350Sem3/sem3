package se.kth.iv1350.pos.integration;

/**
 * Creates instances of external systems. This class is a singleton.
 */
public class RegistryCreator {
    private final InventorySystem inventorySystem   = new InventorySystem();
    private final AccountingSystem accountingSystem = new AccountingSystem();
    private final DiscountSystem discountSystem     = new DiscountSystem();
    private final Printer printer                   = new Printer();

    private RegistryCreator() { }

    // Initialization-on-demand holder
    private static class Holder {
        static final RegistryCreator INSTANCE = new RegistryCreator();
    }

    public static RegistryCreator getInstance() {
        return Holder.INSTANCE;
    }

    public InventorySystem getInventorySystem() { return inventorySystem; }
    public AccountingSystem  getAccountingSystem() { return accountingSystem; }
    public DiscountSystem    getDiscountSystem() { return discountSystem; }
    public Printer           getPrinter()        { return printer; }
}
