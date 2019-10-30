package ru.job4j.newSeaBattle;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Cell[][] cells;
    private int size;
    private IDisplayStrategy displayStrategy;
    private List<Integer> shipSizes;
    private List<Ship> ships;

    public Table(int size, IDisplayStrategy displayStrategy) {
        this.size = size;
        this.cells = new Cell[size][size];
        this.displayStrategy = displayStrategy;
        initEmpty();
        initShipSizes();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public int getSize() {
        return size;
    }

    private void initEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void initShipSizes() {
        this.shipSizes = new ArrayList<>(size * size / 10);
        this.ships = new ArrayList<>(size * size / 10);
        for (int i = 0; i < size * size / 100; i++) {
            shipSizes.add(4);
            for (int j = 0; j < 2; j++) shipSizes.add(3);
            for (int j = 0; j < 3; j++) shipSizes.add(2);
            for (int j = 0; j < 4; j++) shipSizes.add(1);
        }
    }

    public void show(boolean hide) {
        displayStrategy.show(this.cells, hide);
    }

    public boolean placeShip(int x1, int y1, int x2, int y2) {
        int[] xoy = getNoseAndTail(x1, y1, x2, y2);
        x1 = xoy[0];
        y1 = xoy[1];
        x2 = xoy[2];
        y2 = xoy[3];
        Ship ship = new Ship(x1, y1, x2, y2);
        int length = ship.getSize();
        if (!shipSizes.remove(Integer.valueOf(length))) return false;
        else {
            if (checkSquare(x1, y1, x2, y2)) {
                updateShipDisplay(ship, 1, true);
                this.ships.add(ship);
            } else {
                shipSizes.add(length);
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int x1, int y1, int x2, int y2) {

        int xMin = x1 == 0 ? x1 : x1 - 1;
        int yMin = y1 == 0 ? y1 : y1 - 1;
        int xMax = x2 == size - 1 ? x2 : x2 + 1;
        int yMax = y2 == size - 1 ? y2 : y2 + 1;

        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                if (cells[x][y].getState() != 0) return false;
            }
        }
        return true;
    }

    private int[] getNoseAndTail(int x1, int y1, int x2, int y2) {
        int[] result = new int[4];
        result[0] = Math.min(x1, x2);
        result[1] = Math.min(y1, y2);
        result[2] = Math.max(x1, x2);
        result[3] = Math.max(y1, y2);
        return result;
    }


    private void updateShipDisplay(Ship ship, int state, boolean firstInit) {
        int x1 = ship.getX1();
        int x2 = ship.getX2();
        int y1 = ship.getY1();
        boolean oXDirection = x1 - x2 != 0;
        for (int i = 0; i < ship.getSize(); i++) {
            cells[x1][y1].setState(state);
            if (firstInit) cells[x1][y1].setShip(ship);
            if (oXDirection) x1++;
            else y1++;
        }
    }

    public boolean shoot(int x, int y) {
        Ship ship = cells[x][y].getShip();
        boolean result = false;
        if (ship == null) {
            cells[x][y].setState(4);
        } else {
            result = ship.acceptDamage();
            if (result) cells[x][y].setState(2);
            if (ship.getHealth() == 0) updateShipDisplay(ship, 3, false);
        }
        return result;
    }

    public boolean isLose() {
        boolean result = true;
        for (Ship ship:this.ships) {
            if (ship.getHealth()!=0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
