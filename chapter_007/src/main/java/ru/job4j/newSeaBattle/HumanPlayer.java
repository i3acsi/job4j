package ru.job4j.newSeaBattle;

public class HumanPlayer extends SimplePlayer {


    public HumanPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
    }

    @Override
    public void prepare() {
        displayStrategy.show(this, false);
        while (!myTable.equipped()) {
            try {
                int[][] answr = displayStrategy.askCoordinates();
                int[] tail = answr[0];
                int[] nose = answr[1];
                if (tail == null || nose == null) throw new InitException("wrong coordinates");
                int x1 = Math.min(tail[0], nose[0]);
                int y1 = Math.min(tail[1], nose[1]);
                int x2 = Math.max(tail[0], nose[0]);
                int y2 = Math.max(tail[1], nose[1]);
                myTable.checkCoordinates(x1, y1, x2, y2);
                myTable.placeShip(x1, y1, x2, y2);
                displayStrategy.show(this, false);
            } catch (InitException e) {
                displayStrategy.accept(e.getMessage());
            }

        }
    }

    @Override
    public int[] shoot() {
        int[] result;
        do {
            result = displayStrategy.askCoordinate();
        } while (result == null);
        return result;
    }

}
