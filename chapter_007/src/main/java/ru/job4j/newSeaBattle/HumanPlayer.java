package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public class HumanPlayer implements IPlayerStrategy {
    private String name;
    private Table myTable;
    private Table otherTable;
    private int tableSize;
    private Consumer<String> out;
    private Map<String, Integer> map;
    private IDisplayStrategy displayStrategy;

    public HumanPlayer(int size, String name, IDisplayStrategy display, Table otherTable) {
        this(size, name, display);
        this.otherTable = otherTable;
    }

    public HumanPlayer(int size, String name, IDisplayStrategy display) {
        this.myTable = new Table(size, display);
        this.name = name;
        this.otherTable = null;
        this.tableSize = myTable.getSize();
        this.out = display.getOut();
        this.map = display.getMap();
        this.displayStrategy = display;
    }

    public void setOtherTable(Table otherTable) {
        this.otherTable = otherTable;
    }

    public Table getMyTable() {
        return myTable;
    }

    @Override
    public void prepare() {
        myTable.show(false);
        int size = tableSize * tableSize / 10;
        String answr = "";
        while (myTable.getShips().size() < size) {
            try {
                answr = displayStrategy.askCoordinates();
                String[] data = answr.split("-");
                int[] tail = getCoordinate(data[0]);
                int[] nose = getCoordinate(data[1]);
                if (tail == null || nose == null) throw new InitException("wrong coordinates");
                int x1 = Math.min(tail[0], nose[0]);
                int y1 = Math.min(tail[1], nose[1]);
                int x2 = Math.max(tail[0], nose[0]);
                int y2 = Math.max(tail[1], nose[1]);
                checkCoordinates(x1, y1, x2, y2);
                myTable.placeShip(x1, y1, x2, y2);
                myTable.show(false);
            } catch (InitException e) {
                out.accept(e.getMessage());
            }

        }
    }

    private void checkCoordinates(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > tableSize || x2 > tableSize || y1 > tableSize || y2 > tableSize)
            throw new InitException("wrong coordinates");
        if (x1 != x2 & y1 != y2)
            throw new InitException("wrong coordinates");
    }

    private int[] getCoordinate(String c) {
        int[] result = new int[2];
        String[] data = c.split("\\.");//?
        if (data.length != 2 || !data[0].matches("\\w+|([А-Я]+)") || !data[1].matches("\\d+")) return null;
        int y = Integer.valueOf(data[1]);
        if (0 >= y && y > tableSize) return null;
        result[1] = --y;
        result[0] = map.getOrDefault(data[0], -1);
        return result[0] == -1 ? null : result;
    }

    @Override
    public boolean shoot() {
        String coordinates = displayStrategy.askCoordinate();
        boolean result = false;
        try {
            int[] c = getCoordinate(coordinates);
            if (c == null) return false;
            else {
                checkCoordinates(c[0], c[1], c[0], c[1]);
                result = otherTable.shoot(c[0], c[1]);
            }
        } catch (InitException e) {
            out.accept(e.getMessage());
        }
        return result;
    }

    @Override
    public void display() {
        this.displayStrategy.display(myTable, otherTable, name);
    }

    @Override
    public boolean win() {
        return otherTable.isLose();
    }

    @Override
    public void congratulations() {
        this.displayStrategy.congratulations(name);
    }

}
