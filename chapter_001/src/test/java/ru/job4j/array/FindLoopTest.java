package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FindLoopTest {

    @Test
    public void whenArrayHasLengh5Then0() {
        FindLoop testLoop = new FindLoop();
        assertThat(testLoop.indexOf(new int[]{4, 5, 8, 12, 0, -1}, 5), is(1));
    }

    @Test
    public void whenArrayHasNoElement() {
        FindLoop testLoop = new FindLoop();
        assertThat(testLoop.indexOf(new int[]{4, 5, 8, 12, 0, -1}, 77), is(-1));
    }
}
