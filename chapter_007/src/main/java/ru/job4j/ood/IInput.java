package ru.job4j.ood;

import java.util.function.Predicate;

interface IInput {
    String ask(String question, Predicate<String> predicate);
}
