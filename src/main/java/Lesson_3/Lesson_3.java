package Lesson_3;

public class Lesson_3 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSing();
        printColor();
        compareNumbers();
        System.out.println(isSumInRange(7, 25));
        printNumberSign(-3);
        System.out.println(isNegative(0));
        printString("Hooray", 5);
        int year = 2025;
        if (isLeapYear(year)) {
            System.out.println(year + " является високосным годом.");
        } else {
            System.out.println(year + " не является високосным годом.");
        }
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Исходный массив: ");
        printArray10(array);
        System.out.print("Измененный массив: ");
        printArray10(array);
        int[] filledArray = new int[100];
        System.out.print("Заполненный массив: ");
        printArray11(filledArray);
        int[] multiplyArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 11};
        System.out.print("Умноженный: ");
        printArray12(multiplyArray);
        int n = 5;
        int[][] DiagonalArray = new int[n][n];
        printArray13(DiagonalArray);
        int len = 5;
        int initialValue = 10;
        int[] resultArray = createArray(len, initialValue);
        System.out.print("Одномерный массив типа int: ");
        for (int value : resultArray) {
            System.out.print(value + " ");
        }
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSing() {
        int a = 5;
        int b = 1;

        if (a + b > 0) {
            System.out.println("Сумма положительная");
        } else if (a + b < 0) {
            System.out.println("Сумма отрицательная");
        } else {
            System.out.println("Сумма равна нулю");
        }
    }

    public static void printColor() {
        int value = -5;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value >= 1 && value <= 100) {
            System.out.println("Желтый");
        } else { // value > 100
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 5;
        int b = 15;

        if (a >= b) {
            System.out.println("a >= b"); // Если a больше или равно b
        } else {
            System.out.println("a < b"); // Если a меньше b
        }
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void printString(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    public static void printArray10(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printArray11(int[] filledArray) {
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = i + 1;
        }
        for (int value : filledArray) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void printArray12(int[] multiplyArray) {
        for (int i = 0; i < multiplyArray.length; i++) {
            if (multiplyArray[i] < 6) {
                multiplyArray[i] *= 2;
            }
        }
        for (int num : multiplyArray) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printArray13(int[][] DiagonalArray) {
        int n = 5;
        for (int i = 0; i < n; i++) {
            DiagonalArray[i][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(DiagonalArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }
}