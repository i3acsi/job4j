
package ru.job4j.seaBattle;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

public class SimpleCell {
    private static Map<Character, Integer> charsToInt = new HashMap<>(14);
    private static Map<Integer, String> states = new HashMap<>(7);
    private SimpleWarship myShip = null;

    static {
        charsToInt.put('А', 0);
        charsToInt.put('Б', 1);
        charsToInt.put('В', 2);
        charsToInt.put('Г', 3);
        charsToInt.put('Д', 4);
        charsToInt.put('Е', 5);
        charsToInt.put('Ж', 6);
        charsToInt.put('З', 7);
        charsToInt.put('И', 8);
        charsToInt.put('К', 9);

        states.put(1, "_");
        states.put(2, "█");
        states.put(3, "▓");
        states.put(4, "░");
        states.put(5, "●");
    }

    private int x;
    private int y;
    private int state;

    public SimpleCell(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setMyShip(SimpleWarship myShip) {
        this.myShip = myShip;
    }

    public SimpleWarship getMyShip() {
        return myShip;
    }

    public boolean setState(int state) {
        if (state > 0 && state < 6) {
            if (state == 2 && this.state != 1) {
                return false;
            }
            this.state = state;
        } else throw new InitException("Cell init fail: Wrong State Parameter");
        return true;
    }

    /**
     * Depending on the state, it returns different variants of displaying playground cell.
     * For empty cell (case 1): _ .
     * For not damaged warship (case 2): █ .
     * For damaged warship (case 3): ▓ .
     * For killed warship (case 4): ░ .
     * For cells, that were shot (case 5): ● .
     *
     * @return String view of the cell
     */
    @Override
    public String toString() {
        return SimpleCell.states.get(this.state);
    }

    /**
     * Take coordinates in form, convenient for player and convert them to X and Y.
     *
     * @param coordinates in String form.
     * @return X Y coordinates.
     */
    public static int[] coordinatesConvert(String coordinates) {
        int[] result = new int[]{-1, -1};
        coordinates = coordinates.toUpperCase();
        if (coordinates.matches("[А-К]([1-9]|10)")) {
            result[0] = charsToInt.get(coordinates.charAt(0));
            result[1] = Integer.parseInt(coordinates.substring(1)) - 1;
        } else throw new InitException("Cell init fail: wrong coordinates were input.");
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCell cell = (SimpleCell) o;
        return x == cell.x &&
                y == cell.y; //&&
        //state == cell.state;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y); //,hashcode
    }
}
