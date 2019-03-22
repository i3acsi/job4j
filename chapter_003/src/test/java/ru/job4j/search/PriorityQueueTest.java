package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task[] result = new Task[queue.length()];
        String[] expected = new String[]{"urgent", "middle", "low"};
        for (int i = 0; i < queue.length(); i++) {
            result[i] = queue.take();
            assertThat(result[i].getDesc(), is(expected[i]));
        }


    }
}