package ru.job4j.ood;

public class PCPlayer extends SimplePlayer {
    private ILogic logic;

    protected PCPlayer(int mark, ITable table, ILogic logic, String congratulations) {
        super(mark, table, congratulations);
        this.logic = logic;
    }

    @Override
    public void turn() {
        logic.move(table, this.mark);
    }
}
