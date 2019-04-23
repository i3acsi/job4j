package ru.job4j.list;

import java.util.*;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> implements Iterable<E> {
    private int modCount = 0;
    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = result.next;
        result.setNextNull();
        this.size--;
        this.modCount++;
        return result.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = this.modCount;
        return new Iterator<E>() {
            Node<E> current = first;
            int inerIndex = 0;

            @Override
            public boolean hasNext() {
                return inerIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                checkMod();
                Node<E> result = current;
                current = current.next;
                inerIndex++;
                return result.date;
            }

            private void checkMod() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }

        public void setNextNull() {
            this.next = null;
        }
    }
}