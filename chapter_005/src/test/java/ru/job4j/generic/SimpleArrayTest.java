package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<Integer> list;
    private Iterator iterator;

    @Before
    public void init() {
        list = new SimpleArray<>(12);
        iterator = list.iterator();
    }

    @Test
    public void whenAddItemThenListHaveIt() {
        int sizeBefore = list.size();
        list.add(12);
        int sizeAfter = list.size();
        assertThat(sizeBefore + 1, is(sizeAfter));
        assertThat(list.get(0), is(12));
    }

    @Test
    public void whenRemoveThenListHaveNoItem() {
        boolean flag = false;
        list.add(1);
        list.remove(0);
        assertThat(list.size(), is(0));
        try {
            list.get(0);
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
    }

    @Test
    public void whenOverflowThenException() {
        String res = "";
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        try {
            list.add(0);
        } catch (RuntimeException e) {
            res = e.getMessage();
        }
        assertThat(res, is("Переполнение"));
    }

    @Test
    public void whenSetThenListHaveOtherItem() {
        list.add(1);
        list.set(0, 2);
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenHasNoNext() {
        boolean flag = false;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenHasNextTHenTrue() {
        list.add(1);
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenChekTHenReturnSameValue() {
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.hasNext();
        boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenGetNextPointerStepsForward() {
        list.add(0);
        int result = (Integer) iterator.next();
        assertThat(result, is(0));
    }

    @Test
    public void whenGetNextPointerBreak() {
        boolean flag = false;
        while (iterator.hasNext()) {
            iterator.next();
        }
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assertThat(flag, is(true));
    }
}