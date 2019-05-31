package ru.job4j.list;

import org.junit.Test;

public class ListTest {
    @Test
    public void test() {
        String[] strings = new String[]{"a", "b", "c", "d"};
        String[] strings1 = new String[]{"c", "a", "b", "d"};
        String[] strings2 = new String[]{"1", "a", "b", "c"};

        String[] mama = new String[]{"m", "a", "m", "a"};
        String[] maaa = new String[]{"m", "a", "a", "a"};

        String[] faa2 = new String[]{"f", "a", "a"};
        String[] faa1 = new String[]{"f", "a", "a"};

        assert (ArrayCompare.compareArrays(strings, strings1));
        assert (!ArrayCompare.compareArrays(strings, strings2));

        assert (!ArrayCompare.compareArrays(mama, maaa));
        System.out.println(ArrayCompare.compareArrays(mama, maaa));

        assert (ArrayCompare.compareArrays(faa2, faa1));
        System.out.println(ArrayCompare.compareArrays(faa2, faa1));
    }
}
