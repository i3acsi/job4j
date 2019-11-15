package ru.job4j.sea.newseabattle;

public class EmptyDisplay implements IDisplayStrategy {
    @Override
    public void show(SimplePlayer player, boolean hide) {

    }

    @Override
    public void display(SimplePlayer me, SimplePlayer other) {

    }

    @Override
    public int[][] askCoordinates() {
        return new int[0][];
    }

    @Override
    public int[] askCoordinate() {
        return new int[0];
    }

    @Override
    public int askMode() {
        return 0;
    }

    @Override
    public String askIp() {
        return null;
    }

    @Override
    public void congratulations(SimplePlayer me) {

    }

    @Override
    public void accept(int[] coordinate) {

    }

    @Override
    public void accept(String message) {

    }
}
