package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int index = 0;

    public SimpleArray(int size) {
        array = (T[]) new Object[size];
    }

    public void add(T item) {
        if (index == array.length) {
            throw new RuntimeException("Переполнение");
        }
        array[index++] = item;
    }

    public void set(int position, T item) {
        nseCheck(position);
        array[position] = item;
    }

    public void remove(int position) {
        nseCheck(position);
        System.arraycopy(array, position + 1, array, position, index - (position + 1));
        array[--index] = (T) new Object();
    }

    public T get(int position) {
        nseCheck(position);
        return array[position];
    }

    public int size() {
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            public int iterIndex = 0;

            @Override
            public boolean hasNext() {
                return (iterIndex < index);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[iterIndex++];
            }
        };
    }

    private void nseCheck(int position) {
        if (position >= index) {
            throw new NoSuchElementException();
        }
    }
}

//    private void ensureCapacity() {
//        int oldLength = array.length;
//        if (index == oldLength) {
//            System.arraycopy(array, 0, ((T[]) new Object[(oldLength * 3) / 2 + 1]), 0, oldLength);
//        }
//    }

