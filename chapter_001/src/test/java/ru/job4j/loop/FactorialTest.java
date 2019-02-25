package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 19.02.2019
 */


public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial f5 = new Factorial();
        assertThat(f5.calc(5), is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial f0 = new Factorial();
        assertThat(f0.calc(0), is(1));
    }
}
