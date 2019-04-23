package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteThenHeadIsChanged() {
        assertThat(list.delete(), is(3));
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenIteratorHasNext() {
        Iterator iterator = list.iterator();
        assert (iterator.hasNext());
    }

    @Test
    public void whenIteratorHasNoNext() {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assert (!iterator.hasNext());
        boolean flag = false;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
    }


    @Test
    public void whenNextThenGetLastItem() {
        Iterator iterator = list.iterator();
        assertThat(iterator.next(), is(list.get(0)));
    }

    @Test
    public void whenListModifiedThenIteratorsNextTrowsException() {
        Iterator iterator = list.iterator();
        list.add(4);
        boolean flag = false;
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            flag = true;
        }
        assert (flag);
    }
}