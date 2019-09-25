package ru.job4j.interactcalc;

import java.util.function.Consumer;

public class StartUIEngineer extends StartUI {

    /**
     * Конструктор с инициалицацией полей.
     *
     * @param input      ввод данных.
     * @param calculator математические вычисления
     * @param output     вывод данных
     */
    public StartUIEngineer(Input input, EngineerCalculator calculator, Consumer<String> output) {
        super(input, calculator, output);
        super.menu = new EngineerMenu(input, calculator, output);
    }

    public static void main(String[] args) {
        new StartUIEngineer(
                new ValidateInput(new ConsoleInput()),
                new EngineerCalculator(),
                System.out::println)
                .init();
    }
}


