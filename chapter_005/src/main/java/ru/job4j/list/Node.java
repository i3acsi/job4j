package ru.job4j.list;

public class Node<T> {
    T value;
    Node<T> next;

    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node tortoise  = first;
        Node hare = first;
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                result = true;
                break;
            }
        }
        return result;
    }
}
