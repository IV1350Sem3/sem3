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
import se.kth.iv1350.pos.model.SaleDTO;
import se.kth.iv1350.pos.model.observer.TotalRevenueObserver;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private final InventorySystem inventorySystem;
    private final AccountingSystem accountingSystem;
    private final DiscountSystem discountSystem;
    private final Printer printer;
    private final CashRegister cashRegister;
    private Sale currentSale;
    private Amount totalRevenue = new Amount(0);
    private final List<TotalRevenueObserver> revenueObservers = new ArrayList<>();

    public Controller(RegistryCreator creator) {
        this.inventorySystem = creator.getInventorySystem();
        this.accountingSystem  = creator.getAccountingSystem();
        this.discountSystem    = creator.getDiscountSystem();
        this.printer           = creator.getPrinter();
        this.cashRegister      = new CashRegister();
    }

    public void addRevenueObserver(TotalRevenueObserver obs) {
        revenueObservers.add(obs);
    }
    public void removeRevenueObserver(TotalRevenueObserver obs) {
        revenueObservers.remove(obs);
    }

    public void startNewSale() {
        this.currentSale = new Sale();
    }

    public SaleDTO enterItem(String itemIdentifier)
            throws ItemNotFoundException, OperationFailedException {
        try {
            ItemDTO item = inventorySystem.getItem(itemIdentifier);
            currentSale.addItem(item);
            return currentSale.createSaleDTO(item);

        } catch (DatabaseFailureException dbEx) {
            // wrap the low-level message and rethrow
            throw new OperationFailedException(
                    "Could not register item. Please try again.", dbEx);
        }
    }

    public Amount endSale() {
        return currentSale.getTotal();
    }

    public Amount applyDiscount(String customerID) {
        SaleDTO saleDTO = currentSale.createSaleDTO(null);
        DiscountDTO discount = discountSystem.getDiscount(customerID, saleDTO);
        return currentSale.applyDiscount(discount);
    }

    public Amount pay(Amount paidAmount) {
        Payment payment = new Payment(paidAmount, currentSale.getTotal());
        cashRegister.addPayment(payment);

        SaleDTO saleDTO = currentSale.createSaleDTO(null);
        accountingSystem.updateAccounting(saleDTO);
        inventorySystem.updateInventory(saleDTO);

        ReceiptDTO receipt = currentSale.generateReceipt();
        printer.printReceipt(receipt);

        totalRevenue = totalRevenue.add(currentSale.getTotal());
        notifyObservers();  // remains for revenue observers only

        return payment.getChange();
    }

    private void notifyObservers() {
        for (TotalRevenueObserver obs : revenueObservers) {
            obs.updateTotalRevenue(totalRevenue);
        }
    }
}
