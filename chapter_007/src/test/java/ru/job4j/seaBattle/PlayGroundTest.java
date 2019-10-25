package ru.job4j.seaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayGroundTest {
    private IPlayGround playGround;
    private IPlayGround enemyPlayground;
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
        playGround = new PlayGround(output);
        enemyPlayground = new PlayGround(output);
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Empty PlayGround Display Test.
     */
    @Test
    public void whenCallMethodShowThanPlayGroundDisplayedInConsole() {
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
        playGround.initPlayGround();
        playGround.show(false);
        assertThat(this.out.toString(), is(emptyPlayGround));
    }

    /**
     * One Warship Display Test.
     */
    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere2() {
        SimpleWarship warship = new SimpleWarship("А6-А8");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere3() {
        SimpleWarship warship = new SimpleWarship("В2-Г2");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere4() {
        SimpleWarship warship = new SimpleWarship("К10-К10");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * All Warships Display Test.
     */
    @Test
    public void whenPutAListOfShipsOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        SimpleWarship warship4_1 = new SimpleWarship("А1-А4");
        SimpleWarship warship3_1 = new SimpleWarship("А6-А8");
        SimpleWarship warship3_2 = new SimpleWarship("В1-В3");
        SimpleWarship warship2_1 = new SimpleWarship("В5-В6");
        SimpleWarship warship2_2 = new SimpleWarship("Д1-Д2");
        SimpleWarship warship2_3 = new SimpleWarship("Д4-Д5");
        SimpleWarship warship1_1 = new SimpleWarship("А10-А10");
        SimpleWarship warship1_2 = new SimpleWarship("В8-В8");
        SimpleWarship warship1_3 = new SimpleWarship("В10-В10");
        SimpleWarship warship1_4 = new SimpleWarship("Д7-Д7");
        List<SimpleWarship> ships = List.of(warship1_1, warship1_2, warship1_3, warship1_4, warship2_1, warship2_2, warship2_3,
                warship3_1, warship3_2, warship4_1);

        ships.forEach(playGround::place);
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
        playGround.initPlayGround();
        playGround.show(false);
        assertThat(this.out.toString(), is(allShipsPlayGround));
    }

    /**
     * There must be at least one cell between ships.
     */
    @Test
    public void whenPutShipsCloseToEachOtherThenGetFalse() {
        SimpleWarship warship1 = new SimpleWarship("А1-А4");
        SimpleWarship warship2 = new SimpleWarship("Б1-Б3");
        SimpleWarship warship3 = new SimpleWarship("А4-А4");
        assertTrue(playGround.place(warship1));
        assertFalse(playGround.place(warship2));
        assertFalse(playGround.place(warship3));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * There must be certain number of ships with certain length.
     */
    @Test
    public void whenTryToPutTwoFourDeckShipsThenGetFalse() {
        SimpleWarship warship1 = new SimpleWarship("А1-А4");
        SimpleWarship warship2 = new SimpleWarship("В1-В4");
        assertTrue(playGround.place(warship1));
        assertFalse(playGround.place(warship2));
        playGround.initPlayGround();
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
        playGround.show(false);
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * When one player shoot and miss then method should return false.
     */
    @Test
    public void whenShotAndMissThenGetFalse() {
        SimpleWarship warship = new SimpleWarship("Б2-Б2");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        assertFalse(playGround.shoot("А1"));
        assertFalse(playGround.shoot("В2"));
        assertFalse(playGround.shoot("В3"));
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
        playGround.show(false);
        assertThat(this.out.toString(), is(missedShotPlayGround));
    }

    /**
     * When one player shoot and hits the ship then method return true.
     */
    @Test
    public void whenShotAndHitThenGetTrue() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        boolean rslt = playGround.shoot("А1");
        assertTrue(rslt);
        String damagedShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t▓\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
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
        playGround.show(false);
        assertThat(this.out.toString(), is(damagedShipPlayGround));
    }

    /**
     * When ship is destroyed then its visualization changes.
     * When all ships are killed then playground informs us about it.
     */
    @Test
    public void whenShipsAreKilledThenGetTrue() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        SimpleWarship warship2 = new SimpleWarship("И9-Ж9");
        assertTrue(playGround.place(warship));
        assertTrue(playGround.place(warship2));
        playGround.initPlayGround();
        assertTrue(playGround.shoot("А1"));
        assertTrue(playGround.shoot("А2"));
        assertTrue(playGround.shoot("А3"));
        assertTrue(playGround.shoot("А4"));
        assertTrue(playGround.shoot("И9"));
        assertTrue(playGround.shoot("Ж9"));
        assertFalse(playGround.isLose());
        assertTrue(playGround.shoot("З9"));
        assertTrue(playGround.isLose());
        String destroyedShipPlayGround = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t░\t░\t░\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        playGround.show(false);
        assertThat(this.out.toString(), is(destroyedShipPlayGround));
    }

    /**
     * OtherPlayground test. When player shot at enemy`s playground, it show only cells that was fired.
     * Cells, that was`nt fired are shown like empty cell;
     */

    @Test
    public void whenShotAtEnemyPlaygroundAndMissThanOnlyFiredCellChangeView() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        assertTrue(enemyPlayground.place(warship));
        enemyPlayground.initPlayGround();
        enemyPlayground.shoot("Б2");
        enemyPlayground.show(true);
        String expected = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t_\t●\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenShotAtEnemyPlaygroundAndHitThanOnlyFiredCellChangeView() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        assertTrue(enemyPlayground.place(warship));
        enemyPlayground.initPlayGround();
        enemyPlayground.shoot("А1");
        enemyPlayground.shoot("А2");
        enemyPlayground.shoot("А3");
        enemyPlayground.show(true);
        String expected = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t▓\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t▓\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t▓\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenShotAtEnemyPlaygroundAndKillShipThanShipChangeView() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        assertTrue(enemyPlayground.place(warship));
        enemyPlayground.initPlayGround();
        enemyPlayground.shoot("А1");
        enemyPlayground.shoot("А2");
        enemyPlayground.shoot("А3");
        enemyPlayground.shoot("А4");
        enemyPlayground.show(true);
        String expected = new StringBuilder("\t\tА\tБ\tВ\tГ\tД\tЕ\tЖ\tЗ\tИ\tК").append(ln)
                .append("\t1\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t2\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t3\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t4\t░\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t5\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t6\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t7\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t8\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t9\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .append("\t10\t_\t_\t_\t_\t_\t_\t_\t_\t_\t_").append(ln)
                .toString();
        assertThat(this.out.toString(), is(expected));
    }
}
