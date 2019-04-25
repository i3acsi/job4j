package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private SimpleArrayList<T> list = new SimpleArrayList<>();

    public T poll() {
        if (list.getSize()<=0) {
            throw new NoSuchElementException();
        }
        return list.delete();
    }

    public void push(T value) {
        list.add(value);
    }
}
