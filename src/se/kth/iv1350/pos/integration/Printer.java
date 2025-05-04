package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.ReceiptDTO;

/**
 * Represents a printer.
 */
public class Printer {

    /**
     * Creates a new instance.
     */
    public Printer() {
        // Empty constructor
    }

    /**
     * Prints a receipt.
     *
     * @param receipt The receipt to print.
     */
    public void printReceipt(ReceiptDTO receipt) {
        System.out.println("Printing receipt:");
        System.out.println(receipt);
    }
}
