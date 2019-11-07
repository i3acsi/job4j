package ru.job4j.newSeaBattle;

public interface IPlayerStrategy {

    void prepare();

    int[] shoot();

    void shootResultAction(int result);

    int acceptDamage(int x, int y);

    boolean isLose();

    void congratulations();

    String getName();
}
