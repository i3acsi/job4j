package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public abstract class SimplePlayer implements IPlayerStrategy {
    protected String name;
    protected Table myTable;
    protected Table otherTable;
    protected int tableSize;
    protected Consumer<String> out;
    protected IDisplayStrategy displayStrategy;

    public SimplePlayer(int size, String name, IDisplayStrategy display, Table otherTable) {
        this(size, name, display);
        this.otherTable = otherTable;
    }

    public SimplePlayer(int size, String name, IDisplayStrategy display) {
        this.myTable = new Table(size, display);
        this.name = name;
        this.otherTable = null;
        this.tableSize = myTable.getSize();
        this.out = display.getOut();
        this.displayStrategy = display;
    }

    @Override
    public boolean win() {
        return otherTable.isLose();
    }
}
