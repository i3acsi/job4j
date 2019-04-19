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
    private IteratorArray ir;

    public EvenIterator(final int[] numbs) {
        for (int value : numbs) {
            if (value % 2 == 0) {
                index++;
            }
        }
        this.values = new int[index];
        index = 0;
        for (int value : numbs) {
            if (value % 2 == 0) {
                this.values[index++] = value;
            }
        }
        ir = new IteratorArray(this.values);
    }

    @Override
    public boolean hasNext() {
        return ir.hasNext();
    }

    @Override
    public Integer next() {
        if (!ir.hasNext()) {
            throw new NoSuchElementException();
        }
        return (Integer) ir.next();
    }
}
