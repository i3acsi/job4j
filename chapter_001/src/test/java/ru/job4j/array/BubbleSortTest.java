package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void inArrayOutArray() {
        BubbleSort test = new BubbleSort();
        int[] data = new int[] {10, -5, 0, 17, 9, -8, 1};
        int[] expected = new int[] {-8, -5, 0, 1, 9, 10, 17};
        assertThat(test.sortedArr(data), is(expected));
    }
}
