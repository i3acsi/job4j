package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest<T> {
    private SimpleSet<Integer> set;

    @Before
    public void itit() {
        set = new SimpleSet<>();
    }

    @Test
    public void whenAddItemThenListHaveIt() {
        int sizeBefore = set.size();
        set.add(11);
        int sizeAfter = set.size();
        assertThat(sizeBefore + 1, is(sizeAfter));
        Iterator i = set.iterator();
        assertThat(i.next(), is(11));
    }

    @Test
    public void whenOverflowThenNpException() {
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        assert (set.add(11));
    }

    @Test
    public void whenHasNoNext() {
        Iterator iterator = set.iterator();
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
        set.add(1);
        Iterator iterator = set.iterator();
        assert (iterator.hasNext());
    }

    @Test
    public void whenChekTHenReturnSameValue() {
        for (int i = 0; i < 9; i++) {
            set.add(i);
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.hasNext();
        boolean result = iterator.hasNext();
        assert (!result);
    }

    @Test
    public void whenListChangedCallIteratorThowsException() {
        Iterator iterator = set.iterator();
        set.add(0);
        boolean flag = false;
        assert (iterator.hasNext());
        try {
            iterator.next();
        } catch (ConcurrentModificationException e) {
            flag = true;
        }
        assert (flag);
    }

    @Test
    public void whenAddSameItemThenFalse() {
        set.add(0);
        assert (!set.add(0));
    }

    @Test
    public void whenAddNull() {
        assert (set.add(5));
        assert (set.add(null));
        assert (set.add(7));
        assert (!set.add(null));
        assert (!set.add(null));
        assert (!set.add(7));
        Iterator i = set.iterator();
        assertThat(i.next(), is(5));
        assert (i.next() == null);
        assertThat(i.next(), is(7));
    }

}