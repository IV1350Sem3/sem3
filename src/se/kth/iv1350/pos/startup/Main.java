package se.kth.iv1350.pos.startup;

import java.io.IOException;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.integration.TotalRevenueFileOutput;
import se.kth.iv1350.pos.view.TotalRevenueView;
import se.kth.iv1350.pos.view.View;

/**
 * Contains the main method. Starts the application.
 */
public class Main {
    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        RegistryCreator creator = RegistryCreator.getInstance();
        Controller controller = new Controller(creator);

        // Register observers for total revenue
        controller.addRevenueObserver(new TotalRevenueView());
        try {
            controller.addRevenueObserver(new TotalRevenueFileOutput());
        } catch (IOException e) {
            System.out.println("Could not create revenue file output: " + e.getMessage());
        }

        View view = new View(controller);
        view.runFakeExecution();
    }
}
