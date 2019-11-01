package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public interface IDisplayStrategy {
    void show(Cell[][] cells, boolean hide);

    String getString(Cell[][] cells, boolean hide);

    void display(Table myTable, Table otherTable, String name);

    Consumer<String> getOut();

    Map<String, Integer> getMap();

    String askCoordinates();

    String askCoordinate();

    void congratulations(String name);

    int[] getCoordinate(String c);
}
