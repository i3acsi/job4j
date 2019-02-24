package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int count = 0;
        String temp;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            temp = array[i];
            if (temp == null) {
                continue;
            }
            for (int j = 1 + i; j < length; j++) {
                if (array[j] == temp) {
                    array[j] = array[length - 1 - count];
                    array[length - 1 - count] = null;
                    j--;
                    count++;
                }
            }
        }
        System.out.println(count);
        return Arrays.copyOf(array, length - count);
    }
}
