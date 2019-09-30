package ru.job4j.templates;

public interface Template {
    String generate(String template, String[] data);
}
