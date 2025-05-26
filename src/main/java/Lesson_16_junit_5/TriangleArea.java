package Lesson_16_junit_5;

public class TriangleArea {
    public static double area(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }
        return 0.5 * base * height;
    }
}