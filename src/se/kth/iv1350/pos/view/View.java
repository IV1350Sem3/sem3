package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.ItemDTO;
import se.kth.iv1350.pos.model.ItemNotFoundException;
import se.kth.iv1350.pos.model.SaleDTO;
import se.kth.iv1350.pos.util.LogHandler;

import java.io.IOException;

/**
 * This class represents the view, which interacts with the user.
 */
public class View {
    private final Controller controller;
    private final LogHandler logger;

    public View(Controller controller) {
        this.controller = controller;
        try {
            this.logger = new LogHandler();
        } catch (IOException e) {
            System.out.println("Could not create logger. Exception logging disabled: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void runFakeExecution() {
        controller.startNewSale();
        System.out.println("Starting new sale");

        // 1) Enter milk
        System.out.println("Entering item: milk");
        try {
            SaleDTO saleInfo = controller.enterItem("milk");
            System.out.println(formatItemInfo(saleInfo));
        } catch (ItemNotFoundException infe) {
            System.out.println("ERROR: " + infe.getMessage());
        } catch (OperationFailedException ofe) {
            System.out.println("ERROR: " + ofe.getMessage());
            logger.logException((Exception) ofe.getCause());
        }

        // 2) Enter bread
        System.out.println("Entering item: bread");
        try {
            SaleDTO saleInfo = controller.enterItem("bread");
            System.out.println(formatItemInfo(saleInfo));
        } catch (ItemNotFoundException infe) {
            System.out.println("ERROR: " + infe.getMessage());
        } catch (OperationFailedException ofe) {
            System.out.println("ERROR: " + ofe.getMessage());
            logger.logException((Exception) ofe.getCause());
        }

        // 3) Enter milk again
        System.out.println("Entering item: milk (again)");
        try {
            SaleDTO saleInfo = controller.enterItem("milk");
            System.out.println(formatItemInfo(saleInfo));
        } catch (ItemNotFoundException infe) {
            System.out.println("ERROR: " + infe.getMessage());
        } catch (OperationFailedException ofe) {
            System.out.println("ERROR: " + ofe.getMessage());
            logger.logException((Exception) ofe.getCause());
        }

        // 4) Non-existent item
        System.out.println("Entering non-existent item: cookies");
        try {
            SaleDTO saleInfo = controller.enterItem("cookies");
            System.out.println(formatItemInfo(saleInfo));
        } catch (ItemNotFoundException infe) {
            System.out.println("ERROR: " + infe.getMessage());
        } catch (OperationFailedException ofe) {
            System.out.println("ERROR: " + ofe.getMessage());
            logger.logException((Exception) ofe.getCause());
        }

        // 5) Database failure
        System.out.println("Entering item that causes database failure");
        try {
            SaleDTO saleInfo = controller.enterItem("database-failure");
            System.out.println(formatItemInfo(saleInfo));
        } catch (ItemNotFoundException infe) {
            System.out.println("ERROR: " + infe.getMessage());
        } catch (OperationFailedException ofe) {
            System.out.println("ERROR: " + ofe.getMessage());
            logger.logException((Exception) ofe.getCause());
        }

        // 6) End sale, apply discount, pay and print receipt
        System.out.println("Ending sale");
        Amount total = controller.endSale();
        System.out.println("Total: " + total);

        System.out.println("Applying discount for customer ID: 12345");
        Amount discounted = controller.applyDiscount("12345");
        System.out.println("Total with discount: " + discounted);

        System.out.println("Processing payment of 100");
        Amount change = controller.pay(new Amount(100));
        System.out.println("Change: " + change);
        // Receipt is printed inside controller.pay(...)
    }

    private String formatItemInfo(SaleDTO saleInfo) {
        ItemDTO lastItem = saleInfo.getLastAddedItem();
        return lastItem.getName()
                + ", " + lastItem.getPrice()
                + ", Running total: " + saleInfo.getRunningTotal();
    }
}
