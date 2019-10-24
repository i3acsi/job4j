package ru.job4j.seaBattle;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class UserInput {
    private Scanner scanner;
    private Consumer<String> output;

    public UserInput(InputStream input, Consumer<String> output) {
        this.scanner = new Scanner(input);
        this.output = output;
    }

    public String ask(String question, Predicate<String> predicate) {
        String result = "";
        while (!predicate.test(result)) {
            output.accept(question);
            result = scanner.nextLine();
        }
        return result;
    }
}
