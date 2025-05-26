package Lesson_16_junit_5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaTest {

    @Test
    public void testArea() {
        assertEquals(12.0, TriangleArea.area(6, 4));
    }

    @Test
    public void testInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.area(-1, 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.area(5, 0);
        });
    }
}