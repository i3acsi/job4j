package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharTest {
    @Test
    public void ifWordStarsWithPrefixThenTrue() {
        ArrayChar testArray = new ArrayChar("Hello");
        assertThat(testArray.startWith("Hell"), is(true));
    }

    @Test
    public void ifWordNotStarsWithPrefixThenFalse() {
        ArrayChar testArray = new ArrayChar("Hello");
        assertThat(testArray.startWith("Hi"), is(false));
    }
}
