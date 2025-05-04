package se.kth.iv1350.pos.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SaleTest {
    private Sale sale;
    private ItemDTO milk;
    private ItemDTO bread;

    @Before
    public void setUp() {
        sale = new Sale();
        milk = new ItemDTO("milk", "Milk 1L", new Amount(10.90), 0.12);
        bread = new ItemDTO("bread", "Whole Grain Bread", new Amount(25.90), 0.12);
    }

    @Test
    public void testAddItem() {
        sale.addItem(milk);
        assertEquals("Running total should be 10.90", 10.90, sale.getRunningTotal().getAmount(), 0.001);
    }

    @Test
    public void testAddSameItemTwice() {
        sale.addItem(milk);
        sale.addItem(milk);
        assertEquals("Quantity should be increased", 2, milk.getQuantity());
        assertEquals("Running total should be 21.80", 21.80, sale.getRunningTotal().getAmount(), 0.001);
    }

    @Test
    public void testAddMultipleItems() {
        sale.addItem(milk);
        sale.addItem(bread);
        assertEquals("Running total should be 36.80", 36.80, sale.getRunningTotal().getAmount(), 0.001);
    }

    @Test
    public void testApplyDiscount() {
        sale.addItem(milk);
        sale.addItem(bread);
        DiscountDTO discount = new DiscountDTO("12345", 0.1);
        Amount totalWithDiscount = sale.applyDiscount(discount);
        assertEquals("Total with 10% discount should be 33.12", 33.12, totalWithDiscount.getAmount(), 0.001);
    }
}
