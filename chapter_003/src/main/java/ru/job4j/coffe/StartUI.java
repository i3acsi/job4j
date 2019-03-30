package ru.job4j.coffe;

public class StartUI {
    private Machine machine;

    private StartUI(Machine machine) {
        this.machine = machine;
    }

    private void start() {
        machine.init();
        machine.make();
    }

    public static void main(String[] args) {
        new StartUI(
                new Machine(
                        new Input()
                )
        ).start();
    }
}
