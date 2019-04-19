package ru.job4j.services;

import java.util.Iterator;

/**
 * Итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenIterator(final int[] numbs){
        this.values = numbs;
    }

    @Override
    public boolean hasNext() {
        return ir.asNext();
    }

    @Override
    public Integer next() {
        int result;
        do {
            result = values[index++];
        } while (result%2!=0);
        return result;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, 4, 3, 1, 7 ,12, 9};
        EvenIterator ei = new EvenIterator(ints);
        System.out.println(ei.hasNext());
        System.out.println(ei.hasNext());
        System.out.println(ei.hasNext());
        System.out.println(ei.hasNext());
        System.out.println(ei.next());//2
        System.out.println(ei.hasNext());
        System.out.println(ei.next());//4
        System.out.println(ei.hasNext());
        System.out.println(ei.next());//12
        System.out.println(ei.hasNext());

    }
}
