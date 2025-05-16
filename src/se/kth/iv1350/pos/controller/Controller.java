package se.kth.iv1350.pos.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.AccountingSystem;
import se.kth.iv1350.pos.integration.DatabaseFailureException;
import se.kth.iv1350.pos.integration.DiscountSystem;
import se.kth.iv1350.pos.integration.InventorySystem;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.CashRegister;
import se.kth.iv1350.pos.model.DiscountDTO;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.ItemNotFoundException;
import se.kth.iv1350.pos.model.Payment;
import se.kth.iv1350.pos.model.ReceiptDTO;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.observer.TotalRevenueObserver;

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
    private Amount totalRevenue;
    private List<TotalRevenueObserver> revenueObservers = new ArrayList<>();

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
        this.totalRevenue = new Amount(0);
    }

    /**
     * Registers an observer to be notified when total revenue changes.
     *
     * @param observer The observer to register.
     */
    public void addRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Removes an observer so it will no longer be notified of total revenue changes.
     *
     * @param observer The observer to remove.
     */
    public void removeRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.remove(observer);
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
     * @throws ItemNotFoundException if the item with the specified identifier was not found.
     * @throws OperationFailedException if the item could not be added due to a technical error.
     */
    public String enterItem(String itemIdentifier) throws ItemNotFoundException, OperationFailedException {
        try {
            ItemDTO item = inventorySystem.getItem(itemIdentifier);
            currentSale.addItem(item);
            return item.getName() + ", " + item.getPrice() + ", Running total: " + currentSale.getRunningTotal();
        } catch (DatabaseFailureException e) {
            throw new OperationFailedException("Could not register item. Please try again.", e);
        }
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

        // Update total revenue and notify observers
        totalRevenue = totalRevenue.add(currentSale.getTotal());
        notifyObservers();

        return payment.getChange();
    }

    private void notifyObservers() {
        for (TotalRevenueObserver observer : revenueObservers) {
            observer.updateTotalRevenue(totalRevenue);
        }
    }
}
