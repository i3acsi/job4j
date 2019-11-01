package ru.job4j.newSeaBattle;

public class HumanPlayer extends SimplePlayer {


    public HumanPlayer(int size, String name, IDisplayStrategy display, Table otherTable) {
        super(size, name, display, otherTable);
    }

    public HumanPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
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
                int[] tail = displayStrategy.getCoordinate(data[0]);
                int[] nose = displayStrategy.getCoordinate(data[1]);
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

    @Override
    public boolean shoot() {
        String coordinates = displayStrategy.askCoordinate();
        boolean result = false;
        try {
            int[] c = displayStrategy.getCoordinate(coordinates);
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
    public void congratulations() {
        this.displayStrategy.congratulations(name);
    }

}
