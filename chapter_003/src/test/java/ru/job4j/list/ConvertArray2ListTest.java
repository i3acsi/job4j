package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertArray2ListTest {
    @Test
    public void when2ElementsAnd4Elements() {
        ConvertArray2List array = new ConvertArray2List();
        List<int[]> list = List.of(new int[]{1, 2}, new int[]{3, 4, 5, 6});
        List<Integer> result = array.convert(list);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }
}
