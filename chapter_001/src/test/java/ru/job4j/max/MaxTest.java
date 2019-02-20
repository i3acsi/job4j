package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 20.02.2019
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
		Max maxim = new Max();
		assertThat(maxim.max(1, 2), is(2));
	}
	
	@Test
    public void whenFirstLessSecondAndThird() {
		Max maxim = new Max();
		assertThat(maxim.max(1, 2, 3), is(3));
	}
}
