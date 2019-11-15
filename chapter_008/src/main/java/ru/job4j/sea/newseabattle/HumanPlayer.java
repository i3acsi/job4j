package ru.job4j.sea.newseabattle;

public class HumanPlayer extends SimplePlayer {


    public HumanPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
    }

    @Override
    public void prepare() {
//        myTable.placeShip(0, 0, 0, 3);
//        myTable.placeShip(0, 5, 0, 7);
//        myTable.placeShip(0, 9, 2, 9);
//        myTable.placeShip(2, 0, 2, 1);
//        myTable.placeShip(2, 3, 2, 4);
//        myTable.placeShip(2, 6, 2, 7);
//        myTable.placeShip(4, 0, 4, 0);
//        myTable.placeShip(5, 2, 5, 2);
//        myTable.placeShip(6, 4, 6, 4);
//        myTable.placeShip(7, 6, 7, 6);
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
