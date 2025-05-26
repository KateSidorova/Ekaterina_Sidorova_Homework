package Lesson_16_junit_5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @Test
    public void testFactorialPositive() {
        assertEquals(120, Factorial.compute(5));
        assertEquals(1, Factorial.compute(0));
        assertEquals(1, Factorial.compute(1));
    }

    @Test
    public void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Factorial.compute(-1);
        });
    }
}