package ru.job4j.seaBattle;

import java.io.InputStream;
import java.util.function.Consumer;

public class SingleGame {
    private IPlayer player1;
    private IPlayer player2;


    public SingleGame(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        player1.prepare();
        player2.prepare();
        while (!player2.win() && !player1.win()) {
            player1.display();
            player1.shoot();
            player1.display();
            sleep();
            player2.display();
            player2.shoot();
            player2.display();
            sleep();
        }
        if (player1.win()) player1.congratulations();
        else player2.congratulations();

    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        Consumer<String> out = System.out::println;
        UserInput input = new UserInput(inputStream, out);
        IPlayGround first = new PlayGround(out);
        IPlayGround second = new PlayGround(out);
        IPlayer player1 = new Player(first, second, input, out, "player1");
        IPlayer player2 = new Player(second, first, input, out, "player2");
        SingleGame game = new SingleGame(player1, player2);
        game.start();
    }
}
