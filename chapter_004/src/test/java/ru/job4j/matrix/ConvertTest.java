package ru.job4j.matrix;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertTest {
    private static Integer[][] matrix = new Integer[][]{
            {2, 3}, {3, 6}, {5, 7}, {1, 2}, {5, 1}
    };

    @Test
    public void convertTest() {
        Convert convert = new Convert();
        int[] result = convert.convertMatrix(matrix);
        int[] expected = new int[]{2, 3, 3, 6, 5, 7, 1, 2, 5, 1};
        assertThat(result, is(expected));
    }
}
