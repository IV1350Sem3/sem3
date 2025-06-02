import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.RegistryCreator;
import se.kth.iv1350.pos.startup.Main;
import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.ReceiptDTO;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.view.TotalRevenueView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OutputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethodOutput() {
        String[] args = {};
        Main.main(args);
        String output = outContent.toString();

        assertTrue("Main should print starting new sale", output.contains("Starting new sale"));
        assertTrue("Main should print entering items", output.contains("Entering item:"));
        assertTrue("Main should print total revenue", output.contains("Total revenue"));
    }

    @Test
    public void testViewOutput() {
        Controller controller = new Controller(RegistryCreator.getInstance());
        View view = new View(controller);

        view.runFakeExecution();
        String output = outContent.toString();

        assertTrue("View should print starting new sale", output.contains("Starting new sale"));
        assertTrue("View should print item information", output.contains("Running total:"));
        assertTrue("View should print errors for invalid items", output.contains("ERROR:"));
    }

    @Test
    public void testReceiptPrintout() {
        Printer printer = new Printer();
        ReceiptDTO receipt = new ReceiptDTO(
                LocalDateTime.now(),
                new ArrayList<>(),
                new Amount(100),
                null
        );

        printer.printReceipt(receipt);
        String output = outContent.toString();

        assertTrue("Receipt should contain header", output.contains("Printing receipt:"));
        assertTrue("Receipt should contain RECEIPT", output.contains("RECEIPT"));
        assertTrue("Receipt should contain time", output.contains("Time:"));
        assertTrue("Receipt should contain total", output.contains("TOTAL:"));
    }

    @Test
    public void testTotalRevenueViewOutput() {
        TotalRevenueView revenueView = new TotalRevenueView();
        revenueView.updateTotalRevenue(new Amount(50));

        String output = outContent.toString();
        assertTrue("Should display total revenue", output.contains("Total revenue is now:"));
        assertTrue("Should contain amount", output.contains("50"));
    }
}
