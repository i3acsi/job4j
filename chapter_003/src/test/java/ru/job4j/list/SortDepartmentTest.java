package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortDepartmentTest {
    private static final String[] DEPARTS = new String[]{
            "K1\\SK1",
            "K1\\SK2",
            "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2",
            "K2",
            "K2\\SK1\\SSK1",
            "K2\\SK1\\SSK2"
    };
    private static final SortDepartment SORT_DEPARTMENT = new SortDepartment(DEPARTS);

    @Test
    public void whenAscendingSort() {
        String[] result = SORT_DEPARTMENT.ascendingSort();
        String[] expected = new String[]{
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        assertThat(result, is(expected));
    }

    @Test
    public void whenDescendingSort() {
        String[] result = SORT_DEPARTMENT.descendingSort();
        String[] expected = new String[]{
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        };
        assertThat(result, is(expected));
    }
}