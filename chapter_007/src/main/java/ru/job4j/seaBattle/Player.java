package ru.job4j.seaBattle;

import java.util.function.Consumer;

public class Player implements IPlayer{
    private String ln = System.lineSeparator();
    private String name;
    private IPlayGround myPlayGround;
    private IPlayGround otherPlayGround;
    private UserInput input;
    private Consumer<String> output;

    public Player(IPlayGround myPlayGround, IPlayGround otherPlayGround, UserInput input, Consumer<String> output, String name) {
        this.myPlayGround = myPlayGround;
        this.otherPlayGround = otherPlayGround;
        this.input = input;
        this.output = output;
        this.name = name;
    }

    @Override
    public void prepare() {
        myPlayGround.show(false);
        while (myPlayGround.getMyWarSips().size() < 10) {
            String coordinates = input.ask("Введите координаты носа и кормы корабля (Например А1-А4)", str -> str.matches("([А-Е]|[Ж-К])([1-9]|10)-([А-Е]|[Ж-К])([1-9]|10)"));
            try {
                SimpleWarship ship = new SimpleWarship(coordinates);
                myPlayGround.place(ship);
            } catch (InitException e) {
                output.accept(e.getMessage());
            }
            myPlayGround.show(false);
        }
        myPlayGround.initPlayGround();
    }

    @Override
    public void shoot() {
        String coordinates = input.ask("Введите координаты выстрела (Например А4)", str -> str.matches("([А-Е]|[Ж-К])([1-9]|10)"));
        otherPlayGround.shoot(coordinates);
    }

    @Override
    public void display() {
        StringBuilder result = new StringBuilder("#################################################################").append(this.name).append("####################") .append(ln);
        String[] my = this.myPlayGround.toString().split(ln);
        String[] other = this.otherPlayGround.toHiddenString().split(ln);
        for (int i = 0; i < my.length; i++) {
            result.append(my[i]).append("\t\t\t").append(other[i]).append(ln);
        }
        this.output.accept(result.toString());
    }

    @Override
    public boolean win() {
        return this.otherPlayGround.isLose();
    }

    @Override
    public void congratulations() {
        output.accept(String.format("Player '%s', won"));
    }
}