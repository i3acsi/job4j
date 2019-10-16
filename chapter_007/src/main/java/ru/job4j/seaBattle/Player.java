package ru.job4j.seaBattle;

import java.util.List;

public class Player {
    private SimpleLogic logic;
    private List<IWarship> ships;
    private IPlayGround myPlayGround;

    public Player(SimpleLogic logic, IPlayGround myPlayGround) {
        this.logic = logic;
        this.myPlayGround = myPlayGround;
    }

    public void shot(){

    }
}
