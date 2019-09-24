package ru.job4j.interactcalc;

import java.util.function.Consumer;

public class StartUI {

    protected MenuCalculator menu;

    /**
     * Содержит методы для математических действий
     */
    protected Calculator calculator;

    /**
     * Получение данных от пользователя.
     */
    protected final Input input;

    protected final Consumer<String> output;

    /**
     * Конструктор с инициалицацией полей.
     *
     * @param input  ввод данных.
     * @param calculator математические вычисления
     * @param output вывод данных
     */
    public StartUI(Input input, Calculator calculator, Consumer<String> output) {
        this.input = input;
        this.calculator = calculator;
        this.output = output;
        this.menu = new MenuCalculator(this.input, this.calculator, this.output);
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        menu.fillActions();
        int length = menu.getActionsLength();
        int[] range = new int[length];
        for (int i = 0; i < length; i++) {
            range[i] = range[i] + i;
        }
        do {
            menu.show();
            menu.select(input.ask("select", range));
            double result = calculator.getResult();
            input.setCurrent(result);
            System.out.println(result);

        } while (!"y".equals(this.input.ask("Exit?(y) ")));
    }


    /**
     * Запуск программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(new ConsoleInput()),
                new Calculator(),
                System.out::println)
                .init();
    }
}


