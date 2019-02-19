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
	
	@Test
    public void when1EuroToRublThen75() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);
        assertThat(result, is(75));
    }
	
	@Test
    public void when1DollarToRublThen66() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(66));
    }
}