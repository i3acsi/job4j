package ru.job4j.newSeaBattle;

public class Cell {
    private Ship ship;
    private int state;

    public Cell() {
        this.ship = null;
        this.state = 0;
    }

    public int getState() {
        return state;
    }
/*
0 - empty
1 - ship
2 - damaged ship
3 - killed ship
4 - shoot and miss
 */
    public void setState(int state) {
        this.state = state;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }
}
