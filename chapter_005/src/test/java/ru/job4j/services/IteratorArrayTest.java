package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorArrayTest {
    private static int[] array = new int[]{2, 4, 7, 12, 3, 78, 223, 12, 11, 13};
    private static IteratorArray iteratorArray;

    @Before
    public void init() {
        iteratorArray = new IteratorArray(array);
    }

    @Test
    public void whenHasNoNext() {
        for (int i: array) {
            iteratorArray.next();
        }
        assertThat(iteratorArray.hasNext(), is(false));
    }

    @Test
    public void whenHasNextTHenTrue() {
        assertThat(iteratorArray.hasNext(), is(true));
    }

    @Test
    public void whenGetNextPointerStepsForward() {
        int result = (Integer) iteratorArray.next();
        assertThat(result, is(array[0]));
    }

    @Test
    public void whenGetNextPointerBreak() {
        boolean flag = false;
        while (iteratorArray.hasNext()) {
            iteratorArray.next();
        }
        try {
            iteratorArray.next();
        } catch (ArrayIndexOutOfBoundsException e){
            flag = true;
        }
        assertThat(flag, is(true));
    }
}