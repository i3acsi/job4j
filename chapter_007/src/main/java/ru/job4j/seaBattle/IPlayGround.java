package ru.job4j.seaBattle;

import java.util.List;

public interface IPlayGround {
    void show();

    void initPlayGround();

    boolean place(SimpleWarship ship);

    boolean shoot(String coordinate);

    boolean isLose();
}
