package ru.job4j.isp;

import java.util.List;

public interface IMenu extends Runnable {
    List<IMenu> getChildren();

    String getName();
}
