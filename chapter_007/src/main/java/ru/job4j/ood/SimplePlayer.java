package ru.job4j.ood;

public abstract class SimplePlayer implements IPlayer {
    private String congratulationsOF;
    protected int mark;
    protected ITable table;

    protected SimplePlayer(int mark, ITable table, String congratulationsOF) {
        this.mark = mark;
        this.table = table;
        this.congratulationsOF = congratulationsOF;
    }

    public int getMark() {
        return mark;
    }

    public String getCongratulationsOF() {
        return this.congratulationsOF;
    }
}
