package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.ItemNotFoundException;
import se.kth.iv1350.pos.util.LogHandler;
import java.io.IOException;

/**
 * This class represents the view, which interacts with the user.
 */
public class View {
    private Controller controller;
    private LogHandler logger;

    /**
     * Creates a new instance.
     *
     * @param controller The controller that is used for operations.
     */
    public View(Controller controller) {
        this.controller = controller;
        try {
            this.logger = new LogHandler();
        } catch (IOException e) {
            System.out.println("Could not create logger. Exception logging disabled: " + e.getMessage());
        }
    }


    /**
     * Simulates user inputs that drives the system.
     */
    public void runFakeExecution() {
        try {
            System.out.println("Starting new sale");
            controller.startNewSale();

            System.out.println("Entering item: milk");
            String itemInfo = controller.enterItem("milk");
            System.out.println(itemInfo);

            System.out.println("Entering item: bread");
            itemInfo = controller.enterItem("bread");
            System.out.println(itemInfo);

            System.out.println("Entering item: milk (again)");
            itemInfo = controller.enterItem("milk");
            System.out.println(itemInfo);

            System.out.println("Entering non-existent item: cookies");
            try {
                itemInfo = controller.enterItem("cookies");
                System.out.println(itemInfo);
            } catch (ItemNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

            System.out.println("Entering item that causes database failure");
            try {
                itemInfo = controller.enterItem("database-failure");
                System.out.println(itemInfo);
            } catch (OperationFailedException e) {
                System.out.println("ERROR: " + e.getMessage());
                logger.logException(e);
            }

            System.out.println("Ending sale");
            Amount total = controller.endSale();
            System.out.println("Total: " + total);

            System.out.println("Applying discount for customer ID: 12345");
            Amount totalWithDiscount = controller.applyDiscount("12345");
            System.out.println("Total with discount: " + totalWithDiscount);

            System.out.println("Processing payment of 100");
            Amount change = controller.pay(new Amount(100));
            System.out.println("Change: " + change);
        } catch (Exception e) {
            System.out.println("ERROR: Operation failed: " + e.getMessage());
            logger.logException(e);
        }

    }
}

