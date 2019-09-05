package ru.job4j.interactcalc;

public interface Input {
    String ask(String question);

    int ask(String question, int[] range);
}
