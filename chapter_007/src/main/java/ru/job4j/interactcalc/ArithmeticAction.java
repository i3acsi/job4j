package ru.job4j.interactcalc;

public abstract class ArithmeticAction implements Action {
    private final int key;
    private final String info;

    protected ArithmeticAction(final int key, final String info) {
        this.key = key;
        this.info = info;
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