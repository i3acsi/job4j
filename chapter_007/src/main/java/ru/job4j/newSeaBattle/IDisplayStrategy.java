package ru.job4j.newSeaBattle;

public interface IDisplayStrategy {

    void show(SimplePlayer player, boolean hide);

    void display(SimplePlayer me, SimplePlayer other);

    int[][] askCoordinates();

    int[] askCoordinate();

    int askMode();

    void congratulations(SimplePlayer me);

    void accept(int[] coordinate);

    void accept(String message);
}
