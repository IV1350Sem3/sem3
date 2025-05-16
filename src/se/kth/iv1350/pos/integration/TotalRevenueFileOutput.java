package se.kth.iv1350.pos.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.pos.model.Amount;
import se.kth.iv1350.pos.model.observer.TotalRevenueObserver;

/**
 * Writes the total income of all sales to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
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
    public void updateTotalRevenue(Amount totalRevenue) {
        StringBuilder builder = new StringBuilder();
        builder.append(createTime());
        builder.append(", Total revenue: ");
        builder.append(totalRevenue);
        revenueFile.println(builder);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
