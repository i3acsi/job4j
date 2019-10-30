package ru.job4j.newSeaBattle;

import java.util.function.Predicate;

public interface IInput {
    String ask(String question, Predicate<String> predicate);
}
