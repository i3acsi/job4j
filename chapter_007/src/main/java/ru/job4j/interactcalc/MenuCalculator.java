package ru.job4j.interactcalc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuCalculator {
    private NumericInput numericInput;
    private Calculator calculator;
    private final Consumer<String> output;

    /**
     * хранит ссылку на массив типа Action.
     */
    private List<Action> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param numericInput объект типа numericInput для ввода чисел
     * @param calculator   - для математических действий
     */
    public MenuCalculator(NumericInput numericInput, Calculator calculator, Consumer<String> output) {
        this.calculator = calculator;
        this.numericInput = numericInput;
        this.output = output;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return возвращает длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new Sum(0, "Сложить"));
        this.actions.add(new Subtract(1, "Вычесть"));
        this.actions.add(new Multiply(2, "Умножить"));
        this.actions.add(new Divide(3, "Разделить"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        System.out.println(this.actions.get(key).info());
        this.actions.get(key).execute(this.calculator, this.numericInput);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (Action action : this.actions) {
            if (action != null) {
                output.accept(String.format("%d. %s", action.key(), action.info()));
            }
        }
    }
}