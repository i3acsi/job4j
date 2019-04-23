package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    private SimpleStack<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
    }

    @Test
    public void whenPushThenStoreHasItem() {
        list.push(10);
        assertThat(list.poll(), is(10));
    }

    @Test
    public void whenPollThenException() {
        assertThat(list.poll(), is(3));
        assertThat(list.poll(), is(2));
        assertThat(list.poll(), is(1));
        boolean flag = false;
        try {
            list.poll();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
    }
}