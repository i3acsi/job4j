package ru.job4j.interactcalc;

/**
 * Класс для сложения.
 */
public class Sum extends ArithmeticAction {

    public Sum(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Calculator calculator, NumericInput numericInput) {
        double first = numericInput.getDouble("первое");
        double second = numericInput.getDouble("второе");
        calculator.add(first, second);
    }
}