package ru.job4j.seaBattle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Playground for sea battle game. Contains 2-d array of Cells and list of Warships.
 * Contains public methods for:
 * locating ships on playground;
 * shot at ships;
 * display playground in console.
 */

public class PlayGround implements IPlayGround {

    private Consumer<String> out;
    private final String ln = System.lineSeparator();
    private SimpleCell[][] cells;
    private int size;
    private List<SimpleWarship> myWarSips;

    PlayGround(Consumer<String> out) {
        this.out = out;
        this.cells = new SimpleCell[10][10];
        this.size = 10;
        this.myWarSips = new ArrayList<>(10);
    }

    /**
     * Place single warship on playground.
     * You should place all ships before init playground;
     *
     * @param ship - single warship.
     * @return result.
     */
    @Override
    public boolean place(SimpleWarship ship) {
        boolean result = setState(ship);
        if (result) {
            this.myWarSips.add(ship);
            ship.getCells().forEach(cell -> cell.setMyShip(ship));
        }
        return result;
    }

    private boolean setState(SimpleWarship ship) {
        List<SimpleCell> cells = ship.getCells();
        SimpleCell cell = cells.get(0);
        SimpleCell cell2 = cells.get(cells.size() - 1);
        //Оределяю координаты квадрата, который будет занимать корабль(с учетом минимального расстояния между кораблями)
        int xMin = Math.min(cell.getX(), cell2.getX());
        xMin = xMin == 0 ? xMin : xMin - 1;
        int xMax = Math.max(cell.getX(), cell2.getX());
        xMax = xMax == 9 ? xMax : xMax + 1;
        int yMin = Math.min(cell.getY(), cell2.getY());
        yMin = yMin == 0 ? yMin : yMin - 1;
        int yMax = Math.max(cell.getY(), cell2.getY());
        yMax = yMax == 9 ? yMax : yMax + 1;
        //Проверяю что необходимый квадрат пуст
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                if (this.cells[y][x] != null && this.cells[y][x].getState() != 1) return false;
            }
        }
        //Проинициализирую данный квадрат пустыми ячейками.
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                this.cells[y][x] = new SimpleCell(x, y, 1);
            }
        }
        updateShip(ship, true);
        return true;
    }

    /**
     * Updates the display of the ship on playground.
     * Optionally initialize reference on ship
     *
     * @param ship - warship, whose display needs to be updated.
     */
    private void updateShip(SimpleWarship ship, boolean firstInit) {
        ship.getCells().forEach(cell -> this.cells[cell.getY()][cell.getX()].setState(cell.getState()));
        if (firstInit) ship.getCells().forEach(cell -> this.cells[cell.getY()][cell.getX()].setMyShip(ship));
    }

    /**
     * Use this method after all ships initialization.
     * Init all empty cells.
     */
    @Override
    public void initPlayGround() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.cells[i][j] == null) this.cells[i][j] = new SimpleCell(i, j, 1);
            }
        }
    }

    /**
     * Display playground with warships on console;
     */
    @Override
    public void show() {
        this.out.accept(this.toString());
    }

    /**
     * Use this method to shot.
     *
     * @param coordinate of cell being fired.
     * @return result of shot: false if missed, true - if hit the ship.
     */
    @Override
    public boolean shoot(String coordinate) {
        boolean result = false;
        int[] xy = SimpleCell.coordinatesConvert(coordinate);
        SimpleWarship temp = this.cells[xy[0]][xy[1]].getMyShip();
        if (temp != null) {
            temp.acceptDamage(new SimpleCell(xy[0], xy[1], 1));
            updateShip(temp, false);
            result = true;
        } else {
            this.cells[xy[0]][xy[1]].setState(5);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("   А Б В Г Д Е Ж З И К");
        int size = 10;
        for (int i = 0; i < size - 1; i++) {
            result.append(ln).append(" ").append(i + 1);
            for (int j = 0; j < size; j++) {
                result.append(" ").append(this.cells[i][j]);
            }
        }
        result.append(ln).append(size);
        for (int j = 0; j < size; j++) {
            result.append(" ").append(this.cells[size - 1][j]);
        }

        return result.toString();
    }

    @Override
    public boolean isLose() {
        for (SimpleWarship ship : myWarSips) {
            if (!ship.isKilled()) return false;
        }
        return true;
    }
}
