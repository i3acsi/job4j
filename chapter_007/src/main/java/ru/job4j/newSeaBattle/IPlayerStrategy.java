package ru.job4j.newSeaBattle;

public interface IPlayerStrategy {
    void prepare();

    int[] shoot();

    boolean acceptDamage(int x, int y);

    boolean isLose();

    void congratulations();
}
