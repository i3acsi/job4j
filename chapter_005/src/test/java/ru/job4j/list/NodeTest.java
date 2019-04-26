package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

public class NodeTest<T> {
    Node<Integer> first;
    Node<Integer> second;
    Node<Integer> third;
    Node<Integer> fourth;

    @Before
    public void init() {
        first = new Node<>();
        second = new Node<>();
        third = new Node<>();
        fourth = new Node<>();
    }

    @Test
    public void whenCycledThenTrue() {
        first.next = second;
        second.next = third;
        third.next = first;
        assert (Node.hasCycle(first));
    }

    @Test
    public void whenCycledInMiddleThenTrue() {
        first.next = second;
        second.next = third;
        third.next = second;
        assert (Node.hasCycle(first));
    }

    @Test
    public void whenNotCycledThenFalse() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = null;
        assert (!Node.hasCycle(first));
    }

}