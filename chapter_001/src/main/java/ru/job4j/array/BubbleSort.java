package ru.job4j.array;

/**
 * Сортировка пузырьком.
 */

public class BubbleSort {
    public int[] sortedArr(int[] array) {
        int temp;
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1 - j; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        return array;
    }
}
