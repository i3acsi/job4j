package ru.job4j.interactcalc.actions;

import ru.job4j.interactcalc.Calculator;
import ru.job4j.interactcalc.Input;

/**
 * Класс для умножения.
 */
public class Multiply extends ArithmeticAction {

    public Multiply(int key, String info, Calculator calculator, Input input) {
        super(key, info, calculator, input);
    }

    @Override
    public void execute() {
        double first = input.getDouble("первое");
        double second = input.getDouble("второе");
        calculator.multiply(first, second);
    }
}