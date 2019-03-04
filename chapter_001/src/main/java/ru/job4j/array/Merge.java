package ru.job4j.array;

/**
 * Merge two increasing arrays into the one.
 *
 * @author Vasiliy Gasevskiy
 * @version 2
 */

public class Merge {

    /**
     * Method add
     *
     * @param first - first array.
     * @param second - second array
     * @return result - result array
     */

    public int[] add(int[] first, int[] second) {
        if (first == null || second == null) {
            return (first == null) ? second : first;
        }
        int firstLength = first.length;
        int secondLength = second.length;
        int[] result = new int[firstLength + secondLength];
        int i = 0, j = 0;
        while (i < firstLength || j < secondLength) {
            result[i + j] = (i < firstLength && (j == secondLength || first[i] < second[j])) ? first[i++] : second[j++];
        }
        return result;
    }
}
