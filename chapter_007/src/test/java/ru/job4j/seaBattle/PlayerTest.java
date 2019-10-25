package ru.job4j.seaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {
    private String ln = System.lineSeparator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };


    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenShootAndHitThanShowPlayground() {
        shootTest("А1\r\nА2\r\nА3\r\nА3", "▓", "_", "_", false);
    }

    @Test
    public void whenShootAndMissThanShowPlayground() {
        shootTest("Б1\r\nБ2\r\nБ3\r\nБ3", "_", "●", "_", false);
    }

    @Test
    public void whenShootAndKillThanShowPlayground() {
        shootTest("А1\r\nА2\r\nА3\r\nА4", "░", "_", "░", true);
    }

    private void shootTest(String coordinates, String symbol0, String symbol, String symbol2, boolean isLoose) {
        String text = coordinates;
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IPlayGround myPlayGround = new PlayGround(output);
        IPlayGround enemyPlayGround = new PlayGround(output);
        SimpleWarship ship = new SimpleWarship("А1-А4");
        enemyPlayGround.place(ship);
        enemyPlayGround.initPlayGround();
        UserInput input = new UserInput(inputStream, output);
        Player player = new Player(myPlayGround, enemyPlayGround, input, output, "player1");
        for (int i = 0; i < coordinates.split("\r\n").length; i++) {
            player.shoot();
        }
        enemyPlayGround.show(true);
        assertEquals(enemyPlayGround.isLose(), isLoose);
        String expected = String.format((
                "Введите координаты выстрела (Например А4)" + ln +
                        "Введите координаты выстрела (Например А4)" + ln +
                        "Введите координаты выстрела (Например А4)" + ln +
                        "Введите координаты выстрела (Например А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t%s\t%s\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t2\t%s\t%s\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t3\t%s\t%s\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t4\t%s\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln), symbol0, symbol, symbol0, symbol, symbol0, symbol, symbol2);
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenPrepareWasSuccessfulThenPlaygroundHaveTenShips() {
        String text = "А1-А4\r\nВ1-В3\r\nД1-Д3\r\nЖ1-Ж2\r\nИ1-И2\r\nА7-А8\r\nА10-А10\r\nВ10-В10\r\nД10-Д10\r\nЖ10-Ж10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IPlayGround myPlayGround = new PlayGround(output);
        IPlayGround enemyPlayGround = new PlayGround(output);
        UserInput input = new UserInput(inputStream, output);
        Player player = new Player(myPlayGround, enemyPlayGround, input, output, "player1");
        player.prepare();
        String expected =
                "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t2\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t2\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t3\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t2\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t3\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t_\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t_\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "Введите координаты носа и кормы корабля (Например А1-А4)" + ln +
                        "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                        "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_" + ln +
                        "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_" + ln +
                        "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                        "\t10\t█\t_\t█\t_\t█\t_\t█\t_\t_\t_" + ln;
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenDisplayThanBothOfPlaygroundsAreShown() {
        String text = "А1-А4\r\nВ1-В3\r\nД1-Д3\r\nЖ1-Ж2\r\nИ1-И2\r\nА7-А8\r\nА10-А10\r\nВ10-В10\r\nД10-Д10\r\nЖ10-Ж10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        List<SimpleWarship> list = List.of(
                new SimpleWarship("Б2-Д2"),
                new SimpleWarship("Б4-Г4"),
                new SimpleWarship("Б6-Г6"),
                new SimpleWarship("Б8-Б9"),
                new SimpleWarship("Г8-Г9"),
                new SimpleWarship("Е8-Е9"),
                new SimpleWarship("К1-К1"),
                new SimpleWarship("К3-К3"),
                new SimpleWarship("К5-К5"),
                new SimpleWarship("К7-К7"));
        IPlayGround myPlayGround = new PlayGround(output);
        IPlayGround enemyPlayGround = new PlayGround(output);
        list.forEach(enemyPlayGround::place);
        enemyPlayGround.initPlayGround();
        UserInput input = new UserInput(inputStream, output);
        Player player = new Player(myPlayGround, enemyPlayGround, input, output, "player1");
        player.prepare();
        out.reset();
        player.display();
        String expected = "#################################################################" + ln +
                "\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК\t\t\t\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК" + ln +
                "\t1\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_\t\t\t\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t2\t█\t_\t█\t_\t█\t_\t█\t_\t█\t_\t\t\t\t2\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t3\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_\t\t\t\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_\t\t\t\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln +
                "\t10\t█\t_\t█\t_\t█\t_\t█\t_\t_\t_\t\t\t\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_" + ln + ln;
        assertThat(this.out.toString(), is(expected));
    }

}