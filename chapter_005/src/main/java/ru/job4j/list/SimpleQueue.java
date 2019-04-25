package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    public SimpleStack<T> list1 = new SimpleStack<>();
    public SimpleStack<T> list2 = new SimpleStack<>();

    public T poll() {
        if (list1.size() <= 0) {
            throw new NoSuchElementException();
        }
        return list1.poll();
    }

    public void push(T value) {
        int temp = list1.size();
        for (int i = 0; i < temp; i++) {
            list2.push(list1.poll());
        }
        list1.push(value);
        for (int i = 0; i < temp; i++) {
            list1.push(list2.poll());
        }
    }
}
