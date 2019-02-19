package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void whenX1Y1andX2Y2ThenDistanceSqrt2() {
        Point a = new Point(1, 1);
		Point b = new Point(2, 2);
		double distance = a.distanceTo(b);
		assertThat(distance, closeTo(Math.sqrt(2), 0.01));
    }
}