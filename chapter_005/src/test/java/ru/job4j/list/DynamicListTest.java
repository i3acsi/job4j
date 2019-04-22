package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicListTest {
    private DynamicList<Integer> list;

    @Before
    public void init() {
        list = new DynamicList<>();
    }

    @Test
    public void whenAddItemThenListHaveIt() {
        int sizeBefore = list.size();
        list.add(11);
        int sizeAfter = list.size();
        assertThat(sizeBefore + 1, is(sizeAfter));
        assertThat(list.get(0), is(11));
    }

    @Test
    public void whenOverflowThenNpException() {
        for (int i = 0; i < 10; i++) {
            list.add(0);
        }
        assert (list.add(0));
    }

    @Test
    public void whenHasNoNext() {
        Iterator iterator = list.iterator();
        boolean flag = false;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            flag = true;
        }
        assert (flag);
        assert (!iterator.hasNext());
    }

    @Test
    public void whenHasNextTHenTrue() {
        list.add(1);
        Iterator iterator = list.iterator();
        assert (iterator.hasNext());
    }

    @Test
    public void whenChekTHenReturnSameValue() {
        for (int i = 0; i < 9; i++) {
            list.add(0);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.hasNext();
        boolean result = iterator.hasNext();
        assert (!result);
    }

    @Test
    public void whenGetNextPointerStepsForward() {
        list.add(0);
        Iterator iterator = list.iterator();
        int result = (Integer) iterator.next();
        assertThat(result, is(0));
    }

    @Test
    public void whenListChangedCallIteratorThowsException() {
        Iterator iterator = list.iterator();
        list.add(0);
        boolean flag = false;
        assert (iterator.hasNext());
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            flag = true;
        }
        assert (flag);
    }
}