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
     * @param a - first array.
     * @param b - second array
     * @return c - result array
     */

    public int[] add(int[] a, int[] b) {
        if (a == null || b == null) {
            return (a == null) ? b : a;
        }
        int aL = a.length;
        int bL = b.length;
        int[] c = new int[aL + bL];
        int i = 0, j = 0;
        while (i < aL || j < bL) {
            c[i + j] = (i < aL && (j == bL || a[i] < b[j])) ? a[i++] : b[j++];
        }
        return c;
    }
}
