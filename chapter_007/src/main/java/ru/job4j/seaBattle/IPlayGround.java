package ru.job4j.seaBattle;

import java.util.List;
import java.util.function.Consumer;

public interface IPlayGround {
    void show(boolean hide);

    void initPlayGround();

    boolean place(SimpleWarship ship);

    boolean shoot(String coordinate);

    boolean isLose();

    List<SimpleWarship> getMyWarSips();

    SimpleCell getCell(String coordinates);

    public Consumer<String> getOut();

    String toHiddenString();
}
