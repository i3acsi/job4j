package ru.job4j.interactcalc;

import ru.job4j.interactcalc.actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuCalculator {

    /**
     * @param input ввод данных пользователем
     * @param calculator математические действия
     * @param output вывод данных
     */
    protected final Input input;
    protected final Calculator calculator;
    protected final Consumer<String> output;

    /**
     * хранит ссылку на массив типа Action.
     */
    protected List<Action> actions = new ArrayList<>();

    /**
     * Конструктор. - Инициализация плей.
     */
    public MenuCalculator(Input input, Calculator calculator, Consumer<String> output) {
        this.input = input;
        this.calculator = calculator;
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
        this.actions.add(new Sum(0, "Сложить", calculator, input));
        this.actions.add(new Subtract(1, "Вычесть", calculator, input));
        this.actions.add(new Multiply(2, "Умножить", calculator, input));
        this.actions.add(new Divide(3, "Разделить", calculator, input));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        System.out.println(this.actions.get(key).info());
        this.actions.get(key).execute();
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