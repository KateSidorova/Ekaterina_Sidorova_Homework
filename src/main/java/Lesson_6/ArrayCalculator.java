package Lesson_6;

public class ArrayCalculator {

    private static final int VALID_ARRAY_SIZE = 4;

    public static int calculateSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != VALID_ARRAY_SIZE) {
            throw new MyArraySizeException("Размер массива должен быть " + VALID_ARRAY_SIZE + "x" + VALID_ARRAY_SIZE);
        }

        for (String[] row : array) {
            if (row.length != VALID_ARRAY_SIZE) {
                throw new MyArraySizeException("Размер массива должен быть " + VALID_ARRAY_SIZE + "x" + VALID_ARRAY_SIZE);
            }
        }

        int sum = 0;
        for (int i = 0; i < VALID_ARRAY_SIZE; i++) {
            for (int j = 0; j < VALID_ARRAY_SIZE; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в данных массива в элементе [" + i + "][" + j + "]: нечисловое значение '" + array[i][j] + "'");
                }
            }
        }
        return sum;
    }
}