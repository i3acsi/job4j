package ru.job4j.templates;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {
    private Template template;
    private String text, result, expected;
    private String[] data;

    @Before
    public void init() {
        this.template = new SimpleGenerator();
    }

    @Test
    public void whenMethodTakesRightParametersThenItReturnsExpectedText() {
        text = "Hello, ${name}.\r\nWho are ${subject}?";
        data = new String[]{"name=Vasya", "subject=you"};
        result = template.generate(text, data);
        expected = "Hello, Vasya.\r\nWho are you?";
        assertThat(result, is(expected));
    }

    @Test
    public void whenMethodTakesTooMachParametersThenItThrowsException() {
        text = "Hello, ${name}.";
        data = new String[]{"name=Vasya", "subject=you"};
        boolean flag = false;
        try {
            result = template.generate(text, data);
        } catch (TooMuchKeysException e) {
            flag = true;
        }
        assertThat(flag, is(true));
    }

    @Test
    public void whenMethodTakesNotEnoughKeysThenItThrowsException() {
        text = "Hello, ${name}.\r\nWho are ${subject}?";
        data = new String[]{"name=Vasya"};
        boolean flag = false;
        try {
            result = template.generate(text, data);
        } catch (NotEnoughKeysException e) {
            flag = true;
        }
        assertThat(flag, is(true));
    }

}