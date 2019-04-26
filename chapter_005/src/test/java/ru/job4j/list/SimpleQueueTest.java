package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {
    private SimpleQueue<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleQueue<>();
        list.push(1);
        list.push(2);
        list.push(3);
    }

    @Test
    public void whenPushThenStoreHasItem() {
        assertThat(list.poll(), is(1));
    }

    @Test
    public void whenPollThenException() {
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(3));
        boolean flag = false;
        try {
            list.poll();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
    }

    @Test
    public void orderTest() {
        assertThat(list.poll(), is(1));
        assertThat(list.poll(), is(2));
        list.push(4);
        list.push(5);
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(4));
        assertThat(list.poll(), is(5));
    }

}