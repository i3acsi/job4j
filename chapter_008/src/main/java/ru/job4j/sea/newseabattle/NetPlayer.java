package ru.job4j.sea.newseabattle;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NetPlayer extends SimplePlayer {
    private Consumer<String> consumer;
    private Supplier<String> supplier;

    public NetPlayer(int size, String name, IDisplayStrategy display) {
        super(size, name, display);
    }

    @Override
    public void prepare() {

    }

    @Override
    public int[] shoot() {
        String[] data = supplier.get().split(";");
        return new int[]{Integer.valueOf(data[0]), Integer.valueOf(data[1])};
    }

    @Override
    public int acceptDamage(int x, int y) {
        String msg = String.format("%d;%d", x, y);
        consumer.accept(msg);
        return Integer.valueOf(supplier.get());
    }

    @Override
    public boolean isLose() {
        boolean rslt = false;
        String msg = supplier.get();
        if ("loose".equals(msg)) rslt = true;
        return rslt;
    }

    @Override
    public void shootResultAction(int result) {
        //todo ??
        String msg = "result =" + result;
        consumer.accept(msg);
    }

    public void setConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    public void setSupplier(Supplier<String> supplier) {
        this.supplier = supplier;
    }

}
