package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.model.Amount;

/**
 * This class represents the view, which interacts with the user.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new instance.
     *
     * @param controller The controller that is used for operations.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Simulates user inputs that drives the system.
     */
    public void runFakeExecution() {
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

        System.out.println("Ending sale");
        Amount total = controller.endSale();
        System.out.println("Total: " + total);

        System.out.println("Applying discount for customer ID: 12345");
        Amount totalWithDiscount = controller.applyDiscount("12345");
        System.out.println("Total with discount: " + totalWithDiscount);

        System.out.println("Processing payment of 100");
        Amount change = controller.pay(new Amount(100));
        System.out.println("Change: " + change);
    }
}
