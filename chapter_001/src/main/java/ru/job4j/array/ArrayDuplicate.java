package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] words) {
        int count = 0;
        String compare;
        int length = words.length;
        for (int i = 0; i < length; i++) {
            compare = words[i];
            if (compare == null) {
                continue;
            }
            for (int j = 1 + i; j < length; j++) {
                if (words[j] == compare) {
                    words[j] = words[length - 1 - count];
                    words[length - 1 - count] = null;
                    j--;
                    count++;
                }
            }
        }
        return Arrays.copyOf(words,length - count);
    }

}
