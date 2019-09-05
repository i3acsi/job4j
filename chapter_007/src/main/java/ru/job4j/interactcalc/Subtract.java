package ru.job4j.interactcalc;

/**
 * Класс для вычетания.
 */
public class Subtract extends ArithmeticAction {

    public Subtract(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Calculator calculator, NumericInput numericInput) {
        double first = numericInput.getDouble("первое");
        double second = numericInput.getDouble("второе");
        calculator.subtract(first, second);
    }
}