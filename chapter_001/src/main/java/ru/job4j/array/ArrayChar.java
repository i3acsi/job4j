package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int temp;
        int length = array.length;
        for (int i = 0; i < (int) (array.length / 2); i++) {
            temp = array[length - i - 1];
            array[length - i - 1] = array[i];
            array[i] = temp;
        }
        return array;
    }

}