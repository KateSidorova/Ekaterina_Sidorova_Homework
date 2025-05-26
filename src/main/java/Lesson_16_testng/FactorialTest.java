package Lesson_16_testng;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {

    @Test
    public void testFactorialPositive() {
        assertEquals(Factorial.compute(5), 120);
        assertEquals(Factorial.compute(0), 1);
        assertEquals(Factorial.compute(1), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        Factorial.compute(-1);
    }
}