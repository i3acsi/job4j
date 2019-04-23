package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    public SimpleQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> result = this.last;
        this.last = result.next;
        this.size--;
        return result.date;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void push(T value) {
        Node<T> oldItem = first;
        first = new Node<>(value);
        if (isEmpty()) {
            last = first;
            last.next = first;
        } else {
            oldItem.next = first;
        }
        this.size++;
    }

    public int size() {
        return this.size;
    }

    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
