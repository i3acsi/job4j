package ru.job4j.newSeaBattle;

import java.util.function.Consumer;

public interface IDisplayStrategy {
    void show(Cell[][] cells, boolean hide);

    String getString(Cell[][] cells, boolean hide);

    void display(SimplePlayer me, SimplePlayer other);

    Consumer<String> getOut();


    String askCoordinates();

    String askCoordinate();

    void congratulations(String name);

    int[] getCoordinate(String c);

}
