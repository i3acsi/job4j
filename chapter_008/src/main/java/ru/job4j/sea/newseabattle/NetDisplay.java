package ru.job4j.sea.newseabattle;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NetDisplay implements IDisplayStrategy {
    Consumer<String> consumer;
    Supplier<String> supplier;

    public NetDisplay(Consumer<String> consumer, Supplier<String> supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
    }

    @Override
    public void show(SimplePlayer player, boolean hide) {

    }

    @Override
    public void display(SimplePlayer me, SimplePlayer other) {

    }

    @Override
    public int[][] askCoordinates() {
        int[][] result = new int[2][2];
        String[] data = supplier.get().split(";");
        result[0][0] = Integer.valueOf(data[0]);
        result[0][1] = Integer.valueOf(data[1]);
        result[1][0] = Integer.valueOf(data[2]);
        result[1][1] = Integer.valueOf(data[3]);
        return result;
    }

    @Override
    public int[] askCoordinate() {
        int[] result = new int[2];
        String[] data = supplier.get().split(";");
        result[0] = Integer.valueOf(data[0]);
        result[0] = Integer.valueOf(data[1]);
        return result;
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

    public Consumer<String> getConsumer() {
        return consumer;
    }

    public Supplier<String> getSupplier() {
        return supplier;
    }
}
