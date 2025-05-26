package Lesson_16_testng;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    @Test
    public void testCompareGreater() {
        assertEquals(NumberComparator.compare(5, 3), "5 больше 3");
    }

    @Test
    public void testCompareLess() {
        assertEquals(NumberComparator.compare(2, 4), "2 меньше 4");
    }

    @Test
    public void testCompareEqual() {
        assertEquals(NumberComparator.compare(7, 7), "7 равно 7");
    }
}