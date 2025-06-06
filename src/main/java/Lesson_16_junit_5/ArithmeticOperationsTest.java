package Lesson_16_junit_5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        assertEquals(7, ArithmeticOperations.add(3, 4));
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, ArithmeticOperations.subtract(3, 4));
    }

    @Test
    public void testMultiply() {
        assertEquals(12, ArithmeticOperations.multiply(3, 4));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(8, 4));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(4, 0);
        });
    }
}