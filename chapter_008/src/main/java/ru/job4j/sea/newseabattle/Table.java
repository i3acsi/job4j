package ru.job4j.sea.newseabattle;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Cell[][] cells;
    private int size;
    private List<Integer> shipSizes;
    private List<Ship> ships;

    public Table(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        initEmpty();
        initShipSizes();
    }

    /**
     * Проверяет укомплектованность кораблями.
     *
     * @return
     */
    public boolean equipped() {
        return shipSizes.isEmpty();
    }

    public int getSize() {
        return size;
    }

    /**
     * Возращает игровое поле в текущем состоянии.
     *
     * @return
     */
    public Cell[][] getCells() {
        return cells;
    }


    private void initEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }


    /**
     * Метод инициализирует Список длин кораблей в зависимости от размеров игрового поля
     * - чем больше размер, тем больше кораблей поле содержит.
     */
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


    /**
     * Метод размещает корабль на карте, принимает координаты кормы и носа корабля.
     *
     * @param x1 корма - координата по горизонтали.
     * @param y1 корма - координата по вертикали.
     * @param x2 нос - координата по горизонтали.
     * @param y2 нос - координата по вертикали.
     * @return true - если получилось.
     */
    public boolean placeShip(int x1, int y1, int x2, int y2) {
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

    /**
     * Метод инициализирует отображение корабля на карте, а так же, опционально,
     * присваивает ссылку на корабль в соответствующее поле ячейки.
     * Таким образом ячейки "знают" ссылку на свой корабль и содержат текущее состояние.
     *
     * @param ship
     * @param state
     * @param firstInit
     */
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

    /**
     * Метод возвращает целое число - результат выстрела.
     *
     * @param x координата по горизонтали.
     * @param y координата по вертикали
     * @return 4 - если мимо
     * 5 - если высрелил по разбитому кораблю
     * 2 - если попал и ранил
     * 3 - если попал и убил
     */
    public int shoot(int x, int y) {
        int result = 5;
        int state = cells[x][y].getState();
        switch (state) {
            case 0:
                cells[x][y].setState(4);
                result = 4;
                break;
            case 1:
                Ship ship = cells[x][y].getShip();
                ship.acceptDamage();
                cells[x][y].setState(2);
                if (ship.getHealth() == 0) updateShipDisplay(ship, 3, false);
                result = cells[x][y].getState();
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Метод кидает исключение, если переданные коодинаты двух точек за пределами поля или не на одной линии.
     *
     * @param x1 корма - координата по горизонтали
     * @param y1 корма - координата по вертикали
     * @param x2 нос - координата по горизонтали
     * @param y2 нос - координата по вертикали
     */
    public void checkCoordinates(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0 || x1 > size || x2 > size || y1 > size || y2 > size) {
            throw new InitException("wrong coordinates");
        }
        if (x1 != x2 & y1 != y2) {
            throw new InitException("wrong coordinates");
        }
    }

    /**
     * Возвращает true, если все корабли, находящиеся на карте уничтожены.
     *
     * @return
     */
    public boolean isLose() {
        boolean result = true;
        for (Ship ship : this.ships) {
            if (ship.getHealth() != 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
