package ru.job4j.interactcalc;

/**
 * Класс для деления.
 */
public class Divide extends ArithmeticAction {

    protected Divide(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Calculator calculator, NumericInput numericInput) {
        double first = numericInput.getDouble("первое");
        double second = numericInput.getDouble("второе");
        calculator.divide(first, second);
    }
}