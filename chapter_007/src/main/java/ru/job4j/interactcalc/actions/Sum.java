package ru.job4j.interactcalc.actions;

import ru.job4j.interactcalc.Calculator;
import ru.job4j.interactcalc.Input;

/**
 * Класс для сложения.
 */
public class Sum extends ArithmeticAction {


    public Sum(int key, String info, Calculator calculator, Input input) {
        super(key, info, calculator, input);
    }

    @Override
    public void execute() {
        double first = input.getDouble("первое");
        double second = input.getDouble("второе");
        calculator.add(first, second);
    }
}