package ru.job4j.sea.newseabattle;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConsoleInput implements IInput {
    private BufferedReader reader;
    private Consumer<String> out;

    public ConsoleInput(InputStream inputStream, Consumer<String> out) {
        try {
            this.reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.out = out;
    }

    @Override
    public String ask(String question, Predicate<String> predicate) {
        String result = null;
        try {
            out.accept(question);
            result = reader.readLine();
            //result = new String(str.getBytes(), StandardCharsets.UTF_8);
            out.accept(result);
            result.toUpperCase().replaceAll("\\s", "").replaceAll("\r\n", "");
            while (!predicate.test(result)) {
                out.accept(question);
                result = reader.readLine(); //.toUpperCase().replaceAll("\\s", "").replaceAll("\r\n", "");
            }
        } catch (IOException e) {
            out.accept(e.getMessage());
        }
        return result;
    }
}
