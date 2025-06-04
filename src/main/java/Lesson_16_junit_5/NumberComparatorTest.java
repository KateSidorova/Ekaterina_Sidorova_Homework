package Lesson_16_junit_5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberComparatorTest {

    @Test
    public void testCompareGreater() {
        assertEquals("5 больше 3", NumberComparator.compare(5, 3));
    }

    @Test
    public void testCompareLess() {
        assertEquals("2 меньше 4", NumberComparator.compare(2, 4));
    }

    @Test
    public void testCompareEqual() {
        assertEquals("7 равно 7", NumberComparator.compare(7, 7));
    }
}