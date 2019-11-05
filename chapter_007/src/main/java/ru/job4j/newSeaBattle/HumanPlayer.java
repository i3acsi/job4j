package ru.job4j.newSeaBattle;

public class HumanPlayer extends SimplePlayer {


    public HumanPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
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
    public boolean acceptDamage(int x, int y) {
        boolean result = myTable.shoot(x, y);
        return result;
    }

    @Override
    public int[] shoot() {
        int[] result;
        do {
            String coordinates = displayStrategy.askCoordinate();
            result = displayStrategy.getCoordinate(coordinates);
        } while (result==null); // нужен ли чек ?
        return result;
    }


    @Override
    public void congratulations() {
        this.displayStrategy.congratulations(name);
    }

}
