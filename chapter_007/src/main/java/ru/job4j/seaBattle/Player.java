package ru.job4j.seaBattle;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Player {
    // private SimpleLogic logic;
    //private List<IWarship> ships;
    private IPlayGround myPlayGround;
    private IPlayGround otherPlayGround;
    private UserInput input;
    private Consumer<String> output;

    public Player(IPlayGround myPlayGround, IPlayGround otherPlayGround, UserInput input, Consumer<String> output) {
        this.myPlayGround = myPlayGround;
        this.otherPlayGround = otherPlayGround;
        this.input = input;
        this.output = output;
    }

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
    }

    public void shoot() {
        String coordinates = input.ask("Введите координаты выстрела (Например А4)", str -> str.matches("([А-Е]|[Ж-К])([1-9]|10)"));
        otherPlayGround.shoot(coordinates);
    }

}
