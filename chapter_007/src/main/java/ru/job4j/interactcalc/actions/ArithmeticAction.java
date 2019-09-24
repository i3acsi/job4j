package ru.job4j.interactcalc.actions;

import ru.job4j.interactcalc.Calculator;
import ru.job4j.interactcalc.Input;

public abstract class ArithmeticAction implements Action {
    private final int key;
    private final String info;
    protected final Calculator calculator;
    protected final Input input;

    protected ArithmeticAction(final int key, final String info, final Calculator calculator, final Input input) {
        this.key = key;
        this.info = info;
        this.calculator = calculator;
        this.input = input;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s", this.info);
    }
}