package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */

public class EvenIterator implements Iterator {
    private final int[] values;
    private int index = 0;

    public EvenIterator(final int[] numbs) {
        this.values = numbs;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                index = i;
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[index++];
    }
}