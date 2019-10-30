package ru.job4j.newSeaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TableTest {
    private Table table;
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

    @After
    public void backOutput() {
        System.setOut(this.stdout);

    }

    @Before
    public void initTable() {
        System.setOut(new PrintStream(this.out));
        Map<Character, Integer> mapCharToInt = new HashMap<>(17);
        mapCharToInt.put('А', 0);
        mapCharToInt.put('Б', 1);
        mapCharToInt.put('В', 2);
        mapCharToInt.put('Г', 3);
        mapCharToInt.put('Д', 4);
        mapCharToInt.put('Е', 5);
        mapCharToInt.put('Ж', 6);
        mapCharToInt.put('З', 7);
        mapCharToInt.put('И', 8);
        mapCharToInt.put('К', 9);
        Map<Integer, Character> states = new HashMap<>(17);
        states.put(0, '_');
        states.put(1, '█');
        states.put(2, '▓');
        states.put(3, '░');
        states.put(4, '●');
        ICellDisplayStrategy cellDisplayStrategy = new CellDisplayStrategyConsole(states);
        IDisplayStrategy displayStrategy = new ConsoleDisplay(mapCharToInt, cellDisplayStrategy, System.out::println);
        this.table = new Table(10, displayStrategy);
    }

    /**
     * Empty PlayGround Display Test.
     */
    @Test
    public void whenCreateTableAndDisplayItInConsole() {
        String emptyPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(emptyPlayGround));
    }

    /**
     * One Warship Display Test.
     */
    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        assertTrue(table.placeShip(0, 0, 0, 3));
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere2() {
        assertTrue(table.placeShip(0, 5, 0, 7));
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere3() {
        assertTrue(table.placeShip(2, 1, 3, 1));
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t_\t█\t█\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere4() {
        assertTrue(table.placeShip(9, 9, 9, 9));
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t█").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }


    @Test
    public void whenPutAListOfShipsOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        assertTrue(table.placeShip(0, 0, 0, 3));//4
        assertTrue(table.placeShip(2, 0, 2, 2));//3
        assertTrue(table.placeShip(4, 0, 4, 1));//2
        assertTrue(table.placeShip(0, 5, 0, 7));//3
        assertTrue(table.placeShip(2, 4, 2, 5));//2
        assertTrue(table.placeShip(4, 3, 4, 4));//2
        assertTrue(table.placeShip(0, 9, 0, 9));//1
        assertTrue(table.placeShip(2, 7, 2, 7));//1
        assertTrue(table.placeShip(2, 9, 2, 9));//1
        assertTrue(table.placeShip(4, 6, 4, 6));//1
        String allShipsPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t█\t_\t█\t_\t█\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t█\t_\t_\t_\t█\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t█\t_\t█\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t█\t_\t_\t_\t█\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t█\t_\t█\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(allShipsPlayGround));
    }

    /**
     * There must be at least one cell between ships.
     */
    @Test
    public void whenPutShipsCloseToEachOtherThenGetFalse() {
        assertTrue(table.placeShip(0, 0, 0, 3));
        assertFalse(table.placeShip(1, 0, 1, 2));
        assertFalse(table.placeShip(0, 3, 0, 3));
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * There must be certain number of ships with certain length.
     */
    @Test
    public void whenTryToPutTwoFourDeckShipsThenGetFalse() {
        assertTrue(table.placeShip(0, 0, 0, 3));//4
        assertFalse(table.placeShip(2, 0, 2, 3));//4
        String oneShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t█\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * When one player shoot and miss then method should return false.
     */
    @Test
    public void whenShotAndMissThenGetFalse() {
        assertTrue(table.placeShip(1, 1, 1, 1));//4
        assertFalse(table.shoot(2, 1));
        assertFalse(table.shoot(2, 2));
        assertFalse(table.shoot(0, 0));
        String missedShotPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t●\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t█\t●\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t●\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        table.show(false);
        assertThat(this.out.toString(), is(missedShotPlayGround));
    }
}