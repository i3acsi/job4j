package ru.job4j.seaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void whenShootAndMissThanShowPlayground() {
        String text = "А1\r\nА2\nА3";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);

    }

    @Test
    public void whenPrepareWasSuccessfulThenPlaygroundHaveTenShips() {
        String text = "А1-А4\r\nВ1-В3\r\nД1-Д3\r\nЖ1-Ж2\r\nИ1-И2\r\nА7-А8\r\nА10-А10\r\nВ10-В10\r\nД10-Д10\r\nЖ10-Ж10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IPlayGround myPlayGround = new PlayGround(output);
        IPlayGround enemyPlayGround = new PlayGround(output);
        UserInput input = new UserInput(inputStream, output);
        Player player = new Player(myPlayGround, enemyPlayGround, input, output);
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

}