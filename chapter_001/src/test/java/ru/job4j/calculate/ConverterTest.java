package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    @Test
    public void when66RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(66);
        assertThat(result, is(1));
    }

    @Test
    public void when75RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(75);
        assertThat(result, is(1));
    }
}