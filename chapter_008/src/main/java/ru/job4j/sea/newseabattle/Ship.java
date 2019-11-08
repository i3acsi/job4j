package ru.job4j.sea.newseabattle;

public class Ship {
    private int size;
    private int health;
    private int x1, x2, y1, y2;

    public Ship(int x1, int y1, int x2, int y2) {
        this.size = getShipLength(x1, y1, x2, y2);
        this.health = size;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getHealth() {
        return health;
    }

    public int getSize() {
        return size;
    }

    public boolean acceptDamage() {
        if (health == 0) return false;
        else health--;
        return true;
    }

    private int getShipLength(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        return Math.max(dx, dy) + 1;
    }
}
