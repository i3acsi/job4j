package ru.job4j.newSeaBattle;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SingleGame {
    private SimplePlayer player1;
    private SimplePlayer player2;
    private IDisplayStrategy displayStrategy;
    private IInput iInput;
    private Map<String, Integer> map;
    private Map<Integer, Character> states;
    private Consumer<String> out = System.out::println;
    private InputStream in = System.in;

    public SingleGame(int size) {
        initMaps();
        initPlayers();
    }

    public void startGame() {
        while (!player2.isLose() && !player1.isLose()) {
            playersTurn(player1, player2);
            if (player2.isLose()) break;
            playersTurn(player2, player1);
        }
        if (player1.isLose()) player2.congratulations();
        else player1.congratulations();
    }

    private void playersTurn(SimplePlayer me, SimplePlayer other) {
        boolean result = true;
        while (result || other.isLose()) {
            displayStrategy.display(me, other);
            int[] coordinates = me.shoot();
            result = other.acceptDamage(coordinates[0], coordinates[1]);
            displayStrategy.display(me, other);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initPlayers() {
        iInput = new ConsoleInput(in, out);
        displayStrategy = new ConsoleDisplay(map, states, out, iInput);
        player1 = new HumanPlayer(10, "player1", displayStrategy);
        player1.prepare();
        player2 = new HumanPlayer(10, "player2", displayStrategy);
        player2.prepare();
    }

    private void initMaps() {
        map = new HashMap<>(17);
        map.put("А", 0);
        map.put("Б", 1);
        map.put("В", 2);
        map.put("Г", 3);
        map.put("Д", 4);
        map.put("Е", 5);
        map.put("Ж", 6);
        map.put("З", 7);
        map.put("И", 8);
        map.put("К", 9);
        states = new HashMap<>(17);
        states.put(0, '_');
        states.put(1, '█');
        states.put(2, '▓');
        states.put(3, '░');
        states.put(4, '●');
    }

    public static void main(String[] args) {
        SingleGame game = new SingleGame(10);
        game.startGame();
    }
}
