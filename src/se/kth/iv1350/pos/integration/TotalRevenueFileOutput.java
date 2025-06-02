package se.kth.iv1350.pos.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.pos.model.observer.TotalRevenueTemplate;

/**
 * Writes the total income of all sales to a file.
 */
public class TotalRevenueFileOutput extends TotalRevenueTemplate {
    private static final String REVENUE_FILE_NAME = "revenue.txt";
    private PrintWriter revenueFile;

    /**
     * Creates a new instance.
     *
     * @throws IOException If the file could not be created.
     */
    public TotalRevenueFileOutput() throws IOException {
        revenueFile = new PrintWriter(new FileWriter(REVENUE_FILE_NAME, true), true);
    }

    @Override
    protected void doShowTotalIncome() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(createTime());
        builder.append(", Total revenue: ");
        builder.append(getTotalRevenue());
        revenueFile.println(builder);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("Error writing to revenue file: " + e.getMessage());
        e.printStackTrace();
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
