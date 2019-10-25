package ru.job4j.seaBattle;

import org.junit.Test;

import java.io.InputStream;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class SingleGameTest {
    @Test
    public void singleGameTest() {
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