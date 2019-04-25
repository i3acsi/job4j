package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> {

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
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = result.next;
        this.size--;
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

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }

    public boolean hasCycle() {
        Node first = this.first;
        boolean result = false;
        link:
        for (int i = 0; i < size; i++) {
            Node temp = first;
            while (temp.next != null) {
                if (first.date == temp.next.date) {
                    result = true;
                    break link;
                }
                temp = temp.next;
            }
            first = first.next;
        }
        return result;
    }
}