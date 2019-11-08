package ru.job4j.sea.newseabattle;

import javafx.util.Pair;

import java.util.*;

public class PCPlayer extends SimplePlayer {
    private int x0 = -1;
    private int y0 = -1;
    private int direction = 0;
    private int step = 0;
    private Cell[][] otherTable;
    private Random random;
    private List<Pair<Integer, Integer>> ship = new ArrayList<>();

    public PCPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
        this.random = new Random();
        initEmpty();
    }

    @Override
    public void prepare() {
        List<Integer> shipSizes = new ArrayList<>(tableSize * tableSize / 10);
        for (int i = 0; i < tableSize * tableSize / 100; i++) {
            shipSizes.add(4);
            for (int j = 0; j < 2; j++) shipSizes.add(3);
            for (int j = 0; j < 3; j++) shipSizes.add(2);
            for (int j = 0; j < 4; j++) shipSizes.add(1);
        }
        int x = 0, y = 0;
        for (int size : shipSizes) {
            if (x + size >= tableSize) {
                y = y + 2;
                x = 0;
            }
            if (!myTable.placeShip(x, y, x + size - 1, y)) x++;
            else x = x + size + 1;
        }
    }

    @Override
    public int[] shoot() {
        int[] result;
        if (x0 == -1) {
            result = getNewCoordinates();
        } else {
            result = guessCoordinates();
        }
        x0 = result[0];
        y0 = result[1];
        return result;
    }

    @Override
    public void shootResultAction(int result) {
        otherTable[x0][y0].setState(result);
        if (result >= 3) {
            if (result == 3) {
                step = 0;
                direction = 0;
                ship.add(new Pair<>(x0, y0));
                x0 = -1;
                y0 = -1;
                ship.forEach(pair -> {
                    int x = pair.getKey();
                    int y = pair.getValue();
                    otherTable[x][y].setState(3);
                });
                ship = new ArrayList<>();
            } else if (step == 0) {
                x0 = -1;
                y0 = -1;
            } else {
                direction = direction + 2;
                step = 1;
            }

        } else {
            ship.add(new Pair<>(x0, y0));
            ++step;
            --direction;
        }

    }

    private int[] guessCoordinates() {
        int[] result = null;
        int x, y;
        while (result == null) {
            switch (direction) {
                case 0:
                    x = x0;
                    y = y0 - step;
                    if (y >= 0 && checkSqre(x, y)) {
                        result = new int[]{x, y};
                        direction = 1;
                    } else {
                        if (step == 1) {
                            direction = 1;
                        } else {
                            direction = 2;
                            step = 1;
                        }
                    }
                    break;
                case 1:
                    x = x0 + step;
                    y = y0;
                    if (x < tableSize && checkSqre(x, y)) {
                        result = new int[]{x, y};
                        direction = 2;
                    } else {
                        if (step == 1) {
                            direction = 2;
                        } else {
                            direction = 3;
                            step = 1;
                        }
                    }
                    break;
                case 2:
                    x = x0;
                    y = y0 + step;
                    if (y < tableSize && checkSqre(x, y)) {
                        result = new int[]{x, y};
                        direction = 3;
                    } else {
                        direction = 3;
                    }
                    break;
                case 3:
                    x = x0 - step;
                    y = y0;
                    if (x >= 0 && checkSqre(x, y)) {
                        result = new int[]{x, y};
                        direction = 0;
                    } else {
                        direction = 0;
                    }
                    break;
                default:
                    direction = 3;
                    break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает массив интов - координаты точки на карте x и y.
     * Координаты получаются рандомно, при этом есть проверка на наличие обнаруженных клеток корабля
     * рядом с точкой, и в самой точке - это означает что все координаты, которые возвращает функция
     * потенциально содержат необнаруженный корабль.
     *
     * @return coordinates
     */
    private int[] getNewCoordinates() {
        int[] result = null;
        while (result == null) {
            int x = random.nextInt(tableSize);
            int y = random.nextInt(tableSize);
            int state = otherTable[x][y].getState();
            if (state == 0) {
                if (checkSqre(x, y)) {
                    result = new int[]{x, y};
                }
            }
        }
        return result;
    }

    /**
     * Метод проверяет наличие обнаруженных клеток корабля в точке с коодинатами x и y.
     *
     * @param x
     * @param y
     * @return true - если проверка пройдена.
     */
    private boolean checkSqre(int x, int y) {
        boolean result = true;
        int xMin = x == 0 ? x : x - 1;
        int yMin = y == 0 ? y : y - 1;
        int xMax = x == tableSize - 1 ? x : x + 1;
        int yMax = y == tableSize - 1 ? y : y + 1;

        for (int oy = yMin; oy <= yMax; oy++) {
            for (int ox = xMin; ox <= xMax; ox++) {
                int state = otherTable[x][y].getState();
                if (state == 3) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private void initEmpty() {
        otherTable = new Cell[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                otherTable[i][j] = new Cell();
            }
        }
    }
}
