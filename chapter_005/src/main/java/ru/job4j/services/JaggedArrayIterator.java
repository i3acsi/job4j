package ru.job4j.services;

import java.util.Iterator;


public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int cols = 0, rows = 0;

    public JaggedArrayIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return (rows < array.length && cols < array[rows].length);
    }

    @Override
    public Object next() {
        Object result = array[rows][cols];
        if (cols < array[rows].length - 1) {
            cols++;
        } else {
            rows++;
            cols = 0;
        }
        return result;
    }
}

