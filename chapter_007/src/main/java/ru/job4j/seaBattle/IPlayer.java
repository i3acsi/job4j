package ru.job4j.seaBattle;

public interface IPlayer {
    void prepare();

    void shoot();

    void display();

    boolean win();

    void congratulations();
}
