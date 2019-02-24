package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] array = new String[]{"one", "two", "three", "four", "five", "one", "six", "six"};
        String[] expected = new String[]{"one", "two", "three", "four", "five", "six"};
        assertThat(test.remove(array), is(expected));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate2() {
        ArrayDuplicate test = new ArrayDuplicate();
        String[] array2 = new String[]{"one", "one", "three", "four", "five", "one", "six", "one"};
        String[] expected2 = new String[]{"one", "six", "three", "four", "five"};
        assertThat(test.remove(array2), is(expected2));
    }
}