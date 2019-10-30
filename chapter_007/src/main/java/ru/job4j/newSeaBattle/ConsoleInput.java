package ru.job4j.newSeaBattle;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConsoleInput implements IInput {
    private BufferedReader reader;
    private Consumer<String> out;

    public ConsoleInput(InputStream inputStream, Consumer<String> out) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.out = out;
    }

    @Override
    public String ask(String question, Predicate<String> predicate) {
        String result = "";
        try {
            while (!predicate.test(result)) {
                out.accept(question);
                result = reader.readLine();
            }
        } catch (IOException e) {
            out.accept(e.getMessage());
        }
        return result;
    }
}
