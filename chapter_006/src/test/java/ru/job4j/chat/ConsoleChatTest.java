package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleChatTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();


    private final String[] words =  new String[]{
            "Привет",
            "стоп",
            "Привет",
            "продолжить",
            "пока",
            "закончить"
    };



    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void chatTest() {
        ConsoleChat chat = new ConsoleChat(new StubInput(words), String.format(".%spom.xml", File.separator));
        chat.start();

        assertThat((this.out.toString().split(System.lineSeparator()).length), is(3));
    }

}