package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 19.02.2019
 */


public class CounterTest {
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
		Counter c = new Counter();
        assertThat(c.add(1, 10), is(30));
		//напишите здесь тест, проверяющий, что сумма чётных чисел от 1 до 10 при вызове метода counter.add будет равна 30.
    }
}
