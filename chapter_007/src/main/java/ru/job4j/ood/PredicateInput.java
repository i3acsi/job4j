package ru.job4j.ood;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Данный клас содержит метод ask - который принимает вопрос и предикат.
 * Вопрос будет задаваться пользователю до тех пор, пока ответ не будет соответствовать предикату.
 */
public class PredicateInput implements IInput {
    private Scanner scanner;
    private Consumer<String> output;

    public PredicateInput() {
        this.scanner = new Scanner(System.in);
        this.output = System.out::print;
    }

    public PredicateInput(Consumer<String> output) {
        this.scanner = new Scanner(System.in);
        this.output = output;
    }

    public PredicateInput(Readable readable, Consumer<String> output) {
        this.scanner = new Scanner(readable);
        this.output = output;
    }

    @Override
    public String ask(String question, Predicate<String> predicate) {
        String result;
        do {
            output.accept(question + ":");
            result = scanner.nextLine();
        } while (!predicate.test(result));
        return result;
    }

    public static void main(String[] args) {
        PredicateInput predicateInput = new PredicateInput(System.out::print);
        predicateInput.ask("y/n", p -> p.matches("[yn]"));
    }
}
