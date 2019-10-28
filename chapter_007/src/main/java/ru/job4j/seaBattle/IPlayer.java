package ru.job4j.seaBattle;

public interface IPlayer {
    void prepare();

    boolean shoot();

    void display();

    boolean win();

    void congratulations();
}
