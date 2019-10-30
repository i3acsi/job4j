package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public class HumanPlayer implements IPlayerStrategy {
    private Table myTable;
    private Table otherTable;
    private Consumer<String> out;
    private IInput input;

    public HumanPlayer(int size, IDisplayStrategy display, Consumer<String> out, IInput input, Map<Character, Integer> map) {
        this.myTable = new Table(size, display);
        this.otherTable = new Table(size, display);
        this.out = out;
        this.input = input;
    }

    @Override
    public void prepare() {
        myTable.show(false);
        int size = myTable.getSize() * myTable.getSize() / 10;
        while (myTable.getShips().size() < size) {
            String answr = input.ask("Введите координаты носа и кормы корабля (Например А1-А4)", str -> {
                String[] data = str.split("-");
                if (data.length!=2 && data[0].length()!=2 && data[1].length()!=2) return false;

                return false;
            });
        }
    }

    @Override
    public boolean shoot() {
        return false;
    }

    @Override
    public void display() {

    }

    @Override
    public boolean win() {
        return false;
    }

    @Override
    public void congratulations() {

    }
}
