package ru.job4j.newSeaBattle;

import java.util.function.Consumer;

public abstract class SimplePlayer implements IPlayerStrategy {
    protected String name;
    protected Table myTable;
    protected int tableSize;
    protected Consumer<String> out;
    protected IDisplayStrategy displayStrategy;


    public SimplePlayer(int size, String name, IDisplayStrategy display) {
        this.myTable = new Table(size, display);
        this.name = name;
        this.tableSize = myTable.getSize();
        this.out = display.getOut();
        this.displayStrategy = display;
    }

    @Override
    public boolean isLose() {
        return myTable.isLose();
    }
}
