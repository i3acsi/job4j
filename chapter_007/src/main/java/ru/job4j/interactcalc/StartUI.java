package ru.job4j.interactcalc;

import java.util.function.Consumer;

public class StartUI {

    /**
     * Содержит методы для математических действий
     */
    private final Calculator calculator;

    /**
     * Для получения чисел от пользователя
     */
    private final NumericInput numericInput;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    private final Consumer<String> output;

    /**
     * Конструктор с инициалицацией полей.
     *
     * @param input  ввод данных.
     * @param //todo tracker хранилище заявок.
     */
    public StartUI(Input input, NumericInput numericInput, Calculator calculator, Consumer<String> output) {
        this.input = input;
        this.calculator = calculator;
        this.numericInput = numericInput;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuCalculator menu = new MenuCalculator(this.numericInput, this.calculator, this.output);
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
            numericInput.setCurrent(result);
            System.out.println(result);

        } while (!"y".equals(this.input.ask("Exit?(y) ")));
    }


    /**
     * Запуск программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(
                input,
                new NumericInput(input),
                new Calculator(),
                System.out::println)
                .init();
    }
}


