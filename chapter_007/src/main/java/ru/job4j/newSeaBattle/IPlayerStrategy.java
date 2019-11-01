package ru.job4j.newSeaBattle;

public interface IPlayerStrategy {
    void prepare();

    boolean shoot();

    void display();

    boolean win();

    void congratulations();

    void setOtherTable(Table otherTable);

    Table getMyTable();
}
