package ru.job4j.array;

public class FindLoop {
    public int indexOf(int[] data, int value) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                result = i;
                break;
            }
        }
        return result;
    }
}
