package ru.job4j.seaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IPlayerTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };
    private final String ln = System.lineSeparator();

    @Before
    public void loadOutput() {
        //playGround = new PlayGround(output);
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    //    @Test
//    public void whenCreateHumanPlayer() {
//        IPlayGround playGround = new PlayGround(System.out::println);
//        IPlayer player = new Player();
//    }
//    @Test
//    public void whenJustCreatePlayerThanHisPlaygroundIsNotInitialized() {
//        IPlayer player = new HumanPlayer();
//        IPlayGround myPlayground = new PlayGround(output);
//        StringBuilder uninitializedPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln);
//        for (int i = 0; i < 9; i++) {
//            uninitializedPlayGround.append(" ").append(i);
//            for (int j = 0; j < 10; j++) {
//                uninitializedPlayGround.append(" ").append(String.valueOf(null));
//            }
//        }
//        uninitializedPlayGround.append("10");
//        for (int j = 0; j < 10; j++) {
//            uninitializedPlayGround.append(" ").append(String.valueOf(null));
//        }
//        assertThat(myPlayground, is(uninitializedPlayGround.toString()));
//    }
//
//    @Test
//    public void whenPlaceAllShipsThanPlaygroundHaveThem() {
//        IPlayer player = new HumanPlayer();
//        player.prepare();
//        IPlayGround myPlayground = new PlayGround(output);
//        String allShipsPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
//                .append(" 1 █ _ _ _ █ _ _ _ _ █").append(ln)
//                .append(" 2 █ _ _ _ █ _ _ _ _ █").append(ln)
//                .append(" 3 █ _ _ _ _ _ _ _ _ █").append(ln)
//                .append(" 4 █ _ _ _ _ _ _ _ _ _").append(ln)
//                .append(" 5 _ _ _ _ █ _ _ _ _ _").append(ln)
//                .append(" 6 █ _ _ _ _ _ _ _ █ _").append(ln)
//                .append(" 7 █ _ _ _ _ _ _ _ █ _").append(ln)
//                .append(" 8 █ _ _ _ _ _ _ _ _ _").append(ln)
//                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
//                .append("10 █ _ █ _ █ _ _ _ █ █").append(ln)
//                .toString();
//        assertThat(myPlayground, is(allShipsPlayGround.toString()));
//    }
}