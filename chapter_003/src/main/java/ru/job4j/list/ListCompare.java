package ru.job4j.list;

public class ListCompare {
    public int compare(String left, String right) {
        char[] first = left.toCharArray();
        char[] second = right.toCharArray();
        int firstLength = first.length;
        int secondLength = second.length;
        int result = 0;
        for (int i = 0; i < (Math.min(firstLength, secondLength)); i++) {
            result = Character.compare(first[i], second[i]);
            if (result != 0) {
                break;
            }
        }
        if (firstLength != secondLength && result == 0) {
            result = (firstLength < secondLength) ? -1 : 1;
        }
        return result;
    }
}
