package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ScriptProcessTest {

    private Map<Integer, List<Integer>> ds;

    @Before
    public void init() {
        ds = new HashMap<>();
    }

    @Test
    public void testMethodWith1() {
        ds.put(1, Arrays.asList(2, 3));
        ds.put(2, Arrays.asList(4));
        ds.put(4, Arrays.asList());
        ds.put(3, Arrays.asList(4, 5));
        ds.put(5, Arrays.asList());

        ScriptProcess scriptProcess = new ScriptProcess();
        List<Integer> result = scriptProcess.listLoad(ds, 1);
        assertThat(result, is(List.of(5, 4, 3, 2)));
    }

    @Test
    public void testMethodWith3() {
        ds.put(1, Arrays.asList(2, 3));
        ds.put(2, Arrays.asList(4));
        ds.put(4, Arrays.asList());
        ds.put(3, Arrays.asList(4, 5));
        ds.put(5, Arrays.asList());

        ScriptProcess scriptProcess = new ScriptProcess();
        List<Integer> result = scriptProcess.listLoad(ds, 3);
        assertThat(result, is(List.of(5, 4)));
    }

    @Test
    public void v2testMethodWith1() {
        ds.put(1, Arrays.asList(2, 3));
        ds.put(2, Arrays.asList(4));
        ds.put(4, Arrays.asList());
        ds.put(3, Arrays.asList(5));
        ds.put(5, Arrays.asList(4, 6));
        ds.put(6, Arrays.asList());

        ScriptProcess scriptProcess = new ScriptProcess();
        List<Integer> result = scriptProcess.listLoad(ds, 1);
        assertThat(result, is(List.of(6, 4, 5, 3, 2)));
    }


    @Test
    public void v3testMethodWith1() {
        ds.put(1, Arrays.asList(2, 3));
        ds.put(2, Arrays.asList(4));
        ds.put(4, Arrays.asList(5, 6));
        ds.put(3, Arrays.asList(5));
        ds.put(5, Arrays.asList());
        ds.put(6, Arrays.asList());

        ScriptProcess scriptProcess = new ScriptProcess();
        List<Integer> result = scriptProcess.listLoad(ds, 1);
        assertThat(result, is(List.of(5, 3, 6, 4, 2)));
    }

}