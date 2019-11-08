package ru.job4j.sea.newseabattle;

import java.util.function.Predicate;

public interface IInput {
    String ask(String question, Predicate<String> predicate);
}
