package ru.job4j.seaBattle;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserInputTest {

    @Test
    public void whenFirstInputIsWrongAndSecondInputIsRightThanGetAnswer() {
        String text = "wrong test" + System.lineSeparator() + "test";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(buffer);
        UserInput userInput = new UserInput(input, System.out::println);

        assertThat(userInput.ask("question", ("test")::equals), is("test"));
    }

}