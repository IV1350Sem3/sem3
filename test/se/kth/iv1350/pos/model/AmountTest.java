package se.kth.iv1350.pos.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class AmountTest {

    @Test
    public void testAdd() {
        Amount a = new Amount(10);
        Amount b = new Amount(5);
        Amount result = a.add(b);
        assertEquals("10 + 5 should be 15", 15, result.getAmount(), 0.001);
    }

    @Test
    public void testSubtract() {
        Amount a = new Amount(10);
        Amount b = new Amount(5);
        Amount result = a.subtract(b);
        assertEquals("10 - 5 should be 5", 5, result.getAmount(), 0.001);
    }

    @Test
    public void testMultiplyDouble() {
        Amount a = new Amount(10);
        Amount result = a.multiply(0.5);
        assertEquals("10 * 0.5 should be 5", 5, result.getAmount(), 0.001);
    }

    @Test
    public void testMultiplyInt() {
        Amount a = new Amount(10);
        Amount result = a.multiply(3);
        assertEquals("10 * 3 should be 30", 30, result.getAmount(), 0.001);
    }
}
