package ru.job4j.newSeaBattle;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.function.Consumer;

public class PCPlayer {//extends SimplePlayer {
    private int x0 = -1;
    private int y0 = -1;
    private Random random;

//    public PCPlayer(int size, String name, IDisplayStrategy display, Table otherTable) {
//        super(size, name, display, otherTable);
//        this.random = new Random();
//    }
//
//    public PCPlayer(int size, String name, IDisplayStrategy display) {
//        super(size, name, display);
//        this.random = new Random();
//    }

//    @Override
//    public void prepare() {
//        List<Integer> shipSizes = new ArrayList<>(tableSize * tableSize / 10);
//        for (int i = 0; i < tableSize * tableSize / 100; i++) {
//            shipSizes.add(4);
//            for (int j = 0; j < 2; j++) shipSizes.add(3);
//            for (int j = 0; j < 3; j++) shipSizes.add(2);
//            for (int j = 0; j < 4; j++) shipSizes.add(1);
//        }
//        int x = 0, y = 0;
//        for (int size : shipSizes) {
//            if (x + size >= tableSize) {
//                y = y + 2;
//                x = 0;
//            }
//            if (!myTable.placeShip(x, y, x + size - 1, y)) x++;
//            else x = x + size + 1;
//        }
//    }
//
//    @Override
//    public boolean shoot() {
//        if (x0 == -1) {
//            int[] xoy = getNewCoordinates();
//            boolean result = otherTable.shoot(xoy[0], xoy[1]);
//            if (result) {
//                x0 = xoy[0];
//                y0 = xoy[1];
//            } else {
//                x0 = -1;
//                y0 = -1;
//            }
//        } else {
//            guessCoordinates();
//        }
//        return false;
//    }
//
//    private int[] guessCoordinates() {
//        int[] result = new int[2];
//        for (int x = -1; x < 2; x = x + 2) {
//            for (int y = 1; y > -2; y = y - 2) {
//                result[0] = x0 + x;
//                result[1] = y0 + y;
//                if (0>result[0] || result[0]>=tableSize) break;
//                if (0>result[1] || result[1]>=tableSize) break;
//                int state = otherTable.getCells()[result[0]][result[1]].getState();
//                if ()
//
//            }
//        }
//        return null;
//    }
//
//    private int[] getNewCoordinates() {
//        int[] result = null;
//        while (result == null) {
//            int x = random.nextInt(tableSize) - 1;
//            int y = random.nextInt(tableSize) - 1;
//            int state = otherTable.getCells()[x][y].getState();
//            if (state == 0 || state == 1) {
//                if (checkSqre(x, y)) {
//                    result = new int[]{x, y};
//                }
//            }
//        }
//        return result;
//    }
//
//    private boolean checkSqre(int x, int y) {
//        boolean result = true;
//        int xMin = x == 0 ? x : x - 1;
//        int yMin = y == 0 ? y : y - 1;
//        int xMax = x == tableSize - 1 ? x : x + 1;
//        int yMax = y == tableSize - 1 ? y : y + 1;
//
//        for (int oy = yMin; oy <= yMax; oy++) {
//            for (int ox = xMin; ox <= xMax; ox++) {
//                int state = otherTable.getCells()[x][y].getState();
//                if (state == 3) {
//                    result = false;
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public void display() {
//
//    }
//
//    @Override
//    public boolean win() {
//        return false;
//    }
//
//    @Override
//    public void congratulations() {
//
//    }
//
//    @Override
//    public void setOtherTable(Table otherTable) {
//
//    }
//
//    @Override
//    public Table getMyTable() {
//        return this.myTable;
//    }

}
