package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private int size;
    private Node<T> first;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node<T> result = this.first;
        this.first = result.next;
        this.size--;
        return result.date;
    }

    public void push(T value) {
        Node<T> newLink = new Node<>(value);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
