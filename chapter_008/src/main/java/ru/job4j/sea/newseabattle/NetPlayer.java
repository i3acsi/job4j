package ru.job4j.sea.newseabattle;

import javafx.scene.control.Tab;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NetPlayer extends SimplePlayer {
    private InputStream inputStream;
    private OutputStream outputStream;

    private Consumer<int[]> consumer;
    private Supplier<int[]> supplier;

    public NetPlayer(int size, String name, IDisplayStrategy display, InputStream is, OutputStream os) {
        super(size, name, display);
        this.inputStream = is;
        this.outputStream = os;
        consumer = ints -> {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                oos.writeObject(ints);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        supplier = () -> {
            int[] result = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                result = (int[]) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return result;
        };
    }

    @Override
    public void prepare() {
        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            while (myTable == null) myTable = (Table) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int acceptDamage(int x, int y) {
        consumer.accept(new int[]{x, y});
        return myTable.shoot(x, y);
    }

    @Override
    public int[] shoot() {
        return supplier.get();
    }
}
