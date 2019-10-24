package ru.job4j.seaBattle;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Integer> listOfShipSizes;

    PlayGround(Consumer<String> out) {
        this.out = out;
        this.cells = new SimpleCell[10][10];
        this.size = 10;
        this.myWarSips = new ArrayList<>(10);
        this.listOfShipSizes = new ArrayList<>(10);
        this.listOfShipSizes.addAll(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
    }

    public SimpleCell getCell(String coordinates) {
        int[] coordinate = SimpleCell.coordinatesConvert(coordinates);
        return cells[coordinate[1]][coordinate[0]]; //todo
    }

    public List<SimpleWarship> getMyWarSips() {
        return myWarSips;
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
        if (!listOfShipSizes.remove(Integer.valueOf(ship.getSize()))) return false;
        boolean result = setState(ship);
        if (result) {
            this.myWarSips.add(ship);
            //ship.getCells().forEach(cell -> cell.setMyShip(ship));// не полнял - это же одно и тоже . пожоже что это ошибка. пока уберу
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
        //Расположу корабль на поле.
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
        if (firstInit)
            ship.getCells().forEach(cell -> this.cells[cell.getY()][cell.getX()].setMyShip(ship));// не полнял - это же одно и тоже
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
    public void show(boolean hide) {
        if(!hide) this.out.accept(this.toString());
        else this.out.accept(this.toHiddenString());
    }

    //public void show(boolean )

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
        SimpleWarship temp = this.cells[xy[1]][xy[0]].getMyShip();
        if (temp != null) {
            temp.acceptDamage(new SimpleCell(xy[0], xy[1], 1)); // параметр state в данном случае не важен. можно было бы пердавать координаты
            updateShip(temp, false);
            result = true;
        } else {
            this.cells[xy[0]][xy[1]].setState(5);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК");
        int size = 10;
        for (int i = 0; i < size ; i++) {
            result.append(ln).append("\t").append(i + 1);
            for (int j = 0; j < size; j++) {
                result.append("\t");
                SimpleCell c = this.cells[i][j];
                if (c == null) result.append("_");
                else result.append(c);
            }
        }
//        result.append(ln).append(size);
//        for (int j = 0; j < size; j++) {
//            result.append("\t");
//            SimpleCell c = this.cells[size - 1][j];
//            if (c == null) result.append("_");
//            else result.append(c);
//        }

        return result.toString();
    }

    public String toHiddenString() {
        StringBuilder result = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК");
        int size = 10;
        for (int i = 0; i < size ; i++) {
            result.append(ln).append("\t").append(i + 1);
            for (int j = 0; j < size; j++) {
                result.append("\t");
                SimpleCell c = this.cells[i][j];
                if (c.getState() == 2) result.append("_");
                else result.append(c);
            }
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

    public Consumer<String> getOut() {
        return out;
    }
}
