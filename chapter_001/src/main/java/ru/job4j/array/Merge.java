package ru.job4j.array;

public class Merge {
    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,67,122,889};
        int[] b = new int[]{-10, -8 ,-2, 0, 2, 23, 44, 221};
        int[]c = add(a, b);
        for (int x:c){
            System.out.println(x);
        }
    }

    public static int[] add(int[] a, int[] b) {
    int aL = a.length;
    int bL = b.length;
    int[] c = new int[aL + bL];
    int i = 0, j = 0;
    while (i < aL || j < bL)
        c[i + j] = (i < aL && (j == bL || a[i] < b[j])) ? a[i++] : b[j++];

    return c;
    }
}
