package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 */

public class MergeTest {

    /**
     * Test merge of two different sorted arrays.
     */
    @Test
    public void whenMergeThenTrue() {
        Merge test = new Merge();
        int[] a = new int[]{1, 3, 5, 67, 122, 889};
        int[] b = new int[]{-10, -8, -2, 0, 2, 23, 44, 221};
        int[] c = test.add(a, b);
        int[] expected = new int[]{-10, -8, -2, 0, 1, 2, 3, 5, 23, 44, 67, 122, 221, 889};
        assertThat(c, is(expected));
    }

    /**
     * Test merge of two arrays with same last element.
     */
    @Test
    public void whenMergeThenTrue2() {
        Merge test = new Merge();
        int[] a = new int[]{1, 3, 5, 67, 122, 889};
        int[] b = new int[]{-10, -8, -2, 0, 2, 889};
        int[] c = test.add(a, b);
        int[] expected = new int[]{-10, -8, -2, 0, 1, 2, 3, 5, 67, 122, 889, 889};
        assertThat(c, is(expected));
    }

    /**
     * Test merge of two arrays, one of which initialized with null
     */
    @Test
    public void whenMergeWithNull() {
        Merge test = new Merge();
        int[] a = new int[]{1, 3, 5, 67, 122, 889};
        int[] b = null;
        int[] c = test.add(a, b);
        assertThat(c, is(a));
    }

    /**
     * Test merge of two arrays, one of which has zero length
     */
    @Test
    public void whenMergeWithEmptyArray() {
        Merge test = new Merge();
        int[] a = new int[]{-9, 2, 0, 17, 22, 29};
        int[] b = new int[0];
        int[] c = test.add(a, b);
        assertThat(c, is(a));
    }

    /**
     * Test merge of two arrays, both of which are null
     */
    @Test
    public void whenMergeTwoNullArray() {
        Merge test = new Merge();
        int[] a = null;
        int[] b = null;
        int[] c = test.add(a, b);
        assertThat((c == null), is(true));
    }
}


