package ru.job4j.interactcalc;

/**
 * Класс для умножения.
 */
public class Multiply extends ArithmeticAction {

    protected Multiply(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Calculator calculator, NumericInput numericInput) {
        double first = numericInput.getDouble("первое");
        double second = numericInput.getDouble("второе");
        calculator.multiply(first, second);
    }
}