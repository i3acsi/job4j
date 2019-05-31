package ru.job4j.oracle.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ServerTest {
    private final static String LN = System.lineSeparator();

    @Test
    public void whenAsksThenGetMessage() throws IOException {
        testInit("exit", new String[]{"goodbye"}, 1);

    }

    @Test
    public void whenHelloThenGreetings() throws IOException {
        testInit(Joiner.on(LN).join("hello", "exit"), new String[]{"Hello, dear friend, I'm a oracle.", "goodbye"}, 2);
    }


    @Test
    public void whenAsksThenSeveralWords() throws IOException {
        testInit(Joiner.on(LN).join("name", "exit"), new String[]{"goodbye"}, 1);
    }

    private void testInit(String input, String[] expected, int i) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(bais);
        when(socket.getOutputStream()).thenReturn(baos);
        Server server = new Server(socket, "../text.txt");
        server.init();
        String[] result = baos.toString().split(LN + LN);
        int length = result.length, k = 0;
        for (int j = 0; j < length - i; j++) {
            assert (result[j].length() > 0);
        }
        for (int j = length - i; j < length; j++) {
            assertThat(result[j], is(expected[k++]));
        }

    }

}