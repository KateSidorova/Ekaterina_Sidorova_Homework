package Lesson_16_junit_5;

public class Factorial {
    public static long compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative number");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}