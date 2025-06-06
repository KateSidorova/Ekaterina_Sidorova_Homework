package Lesson_18;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TriangleAreaTest {

    @Test
    public void testArea() {
        assertEquals(TriangleArea.area(6, 4), 12.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidDimensions() {
        TriangleArea.area(-1, 5);
    }
}