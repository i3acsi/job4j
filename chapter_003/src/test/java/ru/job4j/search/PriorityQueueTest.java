package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("middle3", 3));
        queue.put(new Task("middle2", 2));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle4", 4));
        var result = new Task[queue.length()];
        var expected = new String[]{"urgent", "middle2", "middle3", "middle4", "low"};
        for (var i = 0; i < queue.length(); i++) {
            result[i] = queue.take();
            assertThat(result[i].getDesc(), is(expected[i]));
        }


    }
}