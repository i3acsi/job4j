package ru.job4j.trackersingle3;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Test adding a new element.
     * If this element appears in the array, the test is passed.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = Tracker.getInstance();
        Tracker tracker2 = Tracker.getInstance();
        assertThat(tracker.equals(tracker2), is(true));
    }
}