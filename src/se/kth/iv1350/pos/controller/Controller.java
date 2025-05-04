package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DiscountSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.DiscountDTO;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.ReceiptDTO;
import se.kth.iv1350.pos.model.Sale;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private DiscountSystem discountSystem;
    private Printer printer;
    private CashRegister cashRegister;
    private Sale currentSale;

    /**
     * Creates a new instance.
     *
     * @param creator Used to get all external systems.
     */
    public Controller(RegistryCreator creator) {
        this.inventorySystem = creator.getInventorySystem();
        this.accountingSystem = creator.getAccountingSystem();
        this.discountSystem = creator.getDiscountSystem();
        this.printer = creator.getPrinter();
        this.cashRegister = new CashRegister();
    }

    /**
     * Starts a new sale.
     */
    public void startNewSale() {
        this.currentSale = new Sale();
    }

    /**
     * Enters an item to the current sale.
     *
     * @param itemIdentifier The identifier of the item to add.
     * @return Information about the entered item and running total.
     */
    public String enterItem(String itemIdentifier) {
        ItemDTO item = inventorySystem.getItem(itemIdentifier);
        if (item == null) {
            return "Item not found";
        }
        currentSale.addItem(item);
        return item.getName() + ", " + item.getPrice() + ", Running total: " + currentSale.getRunningTotal();
    }

    /**
     * Ends the current sale.
     *
     * @return The total price of the sale.
     */
    public Amount endSale() {
        return currentSale.getTotal();
    }

    /**
     * Applies discount based on customer ID.
     *
     * @param customerID The ID of the customer.
     * @return The total price after discount.
     */
    public Amount applyDiscount(String customerID) {
        DiscountDTO discount = discountSystem.getDiscount(customerID, currentSale);
        return currentSale.applyDiscount(discount);
    }

    /**
     * Handles payment for the current sale.
     *
     * @param paidAmount The amount paid by the customer.
     * @return The change to give back to the customer.
     */
    public Amount pay(Amount paidAmount) {
        Payment payment = new Payment(paidAmount, currentSale.getTotal());
        cashRegister.addPayment(payment);
        accountingSystem.updateAccounting(currentSale);
        inventorySystem.updateInventory(currentSale);
        ReceiptDTO receipt = currentSale.generateReceipt();
        printer.printReceipt(receipt);
        return payment.getChange();
    }
}
