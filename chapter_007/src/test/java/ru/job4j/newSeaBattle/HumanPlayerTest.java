package ru.job4j.newSeaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HumanPlayerTest {
    private IDisplayStrategy displayStrategy;
    private IPlayerStrategy player;
    private Map<String, Integer> mapCharToInt;
    private Map<Integer, Character> states;
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
        initTable();
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    private void initTable() {
        mapCharToInt = new HashMap<>(17);
        mapCharToInt.put("А", 0);
        mapCharToInt.put("Б", 1);
        mapCharToInt.put("В", 2);
        mapCharToInt.put("Г", 3);
        mapCharToInt.put("Д", 4);
        mapCharToInt.put("Е", 5);
        mapCharToInt.put("Ж", 6);
        mapCharToInt.put("З", 7);
        mapCharToInt.put("И", 8);
        mapCharToInt.put("К", 9);
        states = new HashMap<>(17);
        states.put(0, '_');
        states.put(1, '█');
        states.put(2, '▓');
        states.put(3, '░');
        states.put(4, '●');
    }

    @Test
    public void whenShootAndHitThanShowPlayground() {
        shootTest("А.1\r\nА.2\r\nА.3\r\nА.3", "▓", "_", "_", new Boolean[]{true, true, true, false, false});
    }

    @Test
    public void whenShootAndMissThanShowPlayground() {
        shootTest("Б.1\r\nБ.2\r\nБ.3\r\nБ.3", "_", "●", "_", new Boolean[]{false, false, false, false, false});
    }

    @Test
    public void whenShootAndKillThanShowPlayground() {
        shootTest("А.1\r\nА.2\r\nА.3\r\nА.4", "░", "_", "░", new Boolean[]{true, true, true, true, true});
    }

    private void shootTest(String coordinates, String symbol0, String symbol, String symbol2, Boolean[] results) {
        Iterator<Boolean> shootResult = Arrays.asList(results).iterator();

        byte[] buffer = coordinates.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IInput input = new ConsoleInput(inputStream, output);

        this.displayStrategy = new ConsoleDisplay(mapCharToInt, states, output, input);

        Table otherTable = new Table(10, displayStrategy);
        otherTable.placeShip(0, 0, 0, 3);

        this.player = new HumanPlayer(10, "player1", displayStrategy, otherTable);
        for (int i = 0; i < coordinates.split("\r\n").length; i++) {
            assertThat(player.shoot(), is(shootResult.next()));
        }
        otherTable.show(true);
        assertEquals(player.win(), shootResult.next());
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
    public void whenThereWereErrorsDuringPreparing() {
        String text = "\r\nА.1-А.4"
                + "\r\nБ.1-Б.2" // менне одной клетки до соседнего корабля
                + "\r\nВ.1-В.4" //лишний корабль
                + "\r\nВ.1-Г.3" //не на одной линии
                + "\r\nВ.1-В.6" //не правильная длинна
                + "\r\nН.1-Н.3" //нет такой клетки
                + "\r\nК.13-К.13" //нет такой клетки
                + "\r\nА.1-А.1" //занята
                + "\r\n в.1-в.3\r\nД.1-Д.3\r\nЖ.1-Ж.2\r\nИ.1-И.2\r\nА.7-А.8\r\nА.10-А.10\r\nВ.10-В.10\r\nД.10-Д.10" +
                "\r\nЗ.3-К.2\r\nЖ.10-Ж.10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IInput input = new ConsoleInput(inputStream, output);
        this.displayStrategy = new ConsoleDisplay(mapCharToInt, states, output, input);
        this.player = new HumanPlayer(10, "player1", displayStrategy, new Table(10, displayStrategy));
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
                        "wrong coordinates" + ln +
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
                        "wrong coordinates" + ln +
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
                        "wrong coordinates" + ln +
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
                        "wrong coordinates" + ln +
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
    public void whenPrepareWasSuccessfulThenPlaygroundHaveTenShips() {
        String text = "А.1-А.4\r\nБ.1-Б.2\r\nВ.1-В.4\r\nВ.1-В.3\r\nД.1-Д.3\r\nЖ.1-Ж.2\r\nИ.1-И.2\r\nА.7-А.8\r\nА.10-А.10\r\nВ.10-В.10\r\nД.10-Д.10\r\nЖ.10-Ж.10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IInput input = new ConsoleInput(inputStream, output);
        this.displayStrategy = new ConsoleDisplay(mapCharToInt, states, output, input);
        this.player = new HumanPlayer(10, "player1", displayStrategy, new Table(10, displayStrategy));
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
                        "Введите координаты носа и кормы корабля (Например А.1-А.4)" + ln +
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
        String text = "А.1-А.4\r\nВ.1-В.3\r\nД.1-Д.3\r\nЖ.1-Ж.2\r\nИ.1-И.2\r\nА.7-А.8\r\nА.10-А.10\r\nВ.10-В.10\r\nД.10-Д.10\r\nЖ.10-Ж.10";
        byte[] buffer = text.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        IInput input = new ConsoleInput(inputStream, output);
        Table otherTable = new Table(10, displayStrategy);
        otherTable.placeShip(1, 1, 4, 1);//4
        otherTable.placeShip(1, 3, 3, 3);//3
        otherTable.placeShip(1, 5, 3, 5);//3
        otherTable.placeShip(1, 7, 1, 8);//2
        otherTable.placeShip(3, 7, 3, 8);//2
        otherTable.placeShip(5, 7, 5, 8);//2
        otherTable.placeShip(9, 0, 9, 0);//1
        otherTable.placeShip(9, 2, 9, 2);//1
        otherTable.placeShip(9, 4, 9, 4);//1
        otherTable.placeShip(9, 6, 9, 6);//1
        this.displayStrategy = new ConsoleDisplay(mapCharToInt, states, output, input);
        this.player = new HumanPlayer(10, "player1", displayStrategy, otherTable);
        player.prepare();
        out.reset();
        player.display();
        String expected = "#################################################################player1####################" + ln +
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