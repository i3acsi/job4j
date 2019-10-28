package ru.job4j.seaBattle;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class SimpleWarship implements IWarship {
    private boolean isKilled;
    private int size;
    private List<SimpleCell> cells;
    private int damagedCells;

    public SimpleWarship(String coordinates) {
        this.isKilled = false;
        this.damagedCells = 0;
        String[] c = coordinates.split("-");
        if (c.length != 2) throw new InitException("Ship initialization fail: wrong coordinates were input.");
        int[] a = SimpleCell.coordinatesConvert(c[0]);
        int[] b = SimpleCell.coordinatesConvert(c[1]);
        this.cells = getCells(a, b);
    }

    public boolean isKilled() {
        return isKilled;
    }

    public List<SimpleCell> getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    /**
     * // TODO: 11.10.2019 description
     *
     * @param nose - ship nose coordinates.
     * @param tail - ship stern coordinates.
     * @return - list of cells.
     */
    private List<SimpleCell> getCells(int[] nose, int[] tail) {
        if (nose[0] == tail[0]) {
            return CellsXorY(nose, tail, true);
        } else if (nose[1] == tail[1]) {
            return CellsXorY(nose, tail, false);
        } else {
            throw new InitException("Ship initialization fail: ship cells should be on one line.");
        }
    }

    private List<SimpleCell> CellsXorY(int[] nose, int[] tail, boolean invert) {
        this.size = Math.abs(nose[0] - tail[0] + nose[1] - tail[1]) + 1;
        int x = invert ? 0 : 1;
        int y = invert ? 1 : 0;
        List<SimpleCell> result = null;

        if (size > 4 || size < 1) {
            throw new InitException("Ship initialization fail: wrong size.");
        } else {
            result = new ArrayList<>(size);
            int a = Math.min(nose[y], tail[y]);
            int b = Math.max(nose[y], tail[y]);
            for (; a <= b; a++) {
                SimpleCell temp = invert ? new SimpleCell(nose[x], a, 2) : new SimpleCell(a, nose[x], 2);
                temp.setMyShip(this);//todo разобраться с инициализацией ячеек
                result.add(temp);
            }
        }
        return result;
    }

    public boolean acceptDamage(SimpleCell cell) {
        boolean result = false;
        for (SimpleCell c : this.cells) {
            if (c.equals(cell)) {
                if (c.getState() == 2) {
                    result = true;
                    c.setState(3);
                    damagedCells++;
                }
                break;
            }
        }
        if (damagedCells == size) {
            this.cells.forEach(c -> c.setState(4));
            this.isKilled = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleWarship warship = (SimpleWarship) o;
        return Objects.equal(cells, warship.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cells);
    }
}
