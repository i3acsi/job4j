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
        String emptyPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.initPlayGround();
        playGround.show();
        assertThat(this.out.toString(), is(emptyPlayGround));
    }

    /**
     * One Warship Display Test.
     */
    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        SimpleWarship warship = new SimpleWarship("А1-А4", 4);
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        String oneShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere2() {
        SimpleWarship warship = new SimpleWarship("А6-А8", 3);
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        String oneShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere3() {
        SimpleWarship warship = new SimpleWarship("В2-Г2", 2);
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        String oneShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 _ _ █ █ _ _ _ _ _ _").append(ln)
                .append(" 3 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    @Test
    public void whenPutAShipOnThePlayGroundAndCallMethodShowThanItDisplayedThere4() {
        SimpleWarship warship = new SimpleWarship("К10-К10", 1);
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        String oneShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ █").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * All Warships Display Test.
     */
    @Test
    public void whenPutAListOfShipsOnThePlayGroundAndCallMethodShowThanItDisplayedThere() {
        SimpleWarship warship4_1 = new SimpleWarship("А1-А4", 4);
        SimpleWarship warship3_1 = new SimpleWarship("А6-А8", 3);
        SimpleWarship warship3_2 = new SimpleWarship("В1-В3", 3);
        SimpleWarship warship2_1 = new SimpleWarship("В5-В6", 2);
        SimpleWarship warship2_2 = new SimpleWarship("Д1-Д2", 2);
        SimpleWarship warship2_3 = new SimpleWarship("Д4-Д5", 2);
        SimpleWarship warship1_1 = new SimpleWarship("А10-А10", 1);
        SimpleWarship warship1_2 = new SimpleWarship("В8-В8", 1);
        SimpleWarship warship1_3 = new SimpleWarship("В10-В10", 1);
        SimpleWarship warship1_4 = new SimpleWarship("Д7-Д7", 1);
        List<SimpleWarship> ships = List.of(warship1_1, warship1_2, warship1_3, warship1_4, warship2_1, warship2_2, warship2_3,
                warship3_1, warship3_2, warship4_1);

        ships.forEach(playGround::place);
        String allShipsPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 █ _ █ _ █ _ _ _ _ _").append(ln)
                .append(" 2 █ _ █ _ █ _ _ _ _ _").append(ln)
                .append(" 3 █ _ █ _ _ _ _ _ _ _").append(ln)
                .append(" 4 █ _ _ _ █ _ _ _ _ _").append(ln)
                .append(" 5 _ _ █ _ █ _ _ _ _ _").append(ln)
                .append(" 6 █ _ █ _ _ _ _ _ _ _").append(ln)
                .append(" 7 █ _ _ _ █ _ _ _ _ _").append(ln)
                .append(" 8 █ _ █ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 █ _ █ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.initPlayGround();
        playGround.show();
        assertThat(this.out.toString(), is(allShipsPlayGround));
    }

    /**
     * There must be at least one cell between ships.
     */
    @Test
    public void whenPutShipsCloseToEachOtherThenGetFalse() {
        SimpleWarship warship1 = new SimpleWarship("А1-А4", 4);
        SimpleWarship warship2 = new SimpleWarship("Б1-Б3", 3);
        SimpleWarship warship3 = new SimpleWarship("А4-А4", 1);
        assertTrue(playGround.place(warship1));
        assertFalse(playGround.place(warship2));
        assertFalse(playGround.place(warship3));
        String oneShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(oneShipPlayGround));
    }

    /**
     * When one player shoot and miss then method should return false.
     */
    @Test
    public void whenShotAndMissThenGetFalse() {
        assertFalse(playGround.shoot("А1"));
        String missedShotPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 ● _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(missedShotPlayGround));
    }

    /**
     * When one player shoot and hits the ship then method return true.
     */
    @Test
    public void whenShotAndHitThenGetTrue() {
        SimpleWarship warship = new SimpleWarship("А1-А4", 4);
        assertTrue(playGround.place(warship));
        playGround.initPlayGround();
        boolean rslt = playGround.shoot("А1");
        assertTrue(rslt);
        String damagedShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 ▓ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 █ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(damagedShipPlayGround));
    }

    /**
     * When ship is destroyed then its visualization changes.
     * When all ships are killed then playground informs us about it.
     */
    @Test
    public void whenShipsAreKilledThenGetTrue() {
        SimpleWarship warship = new SimpleWarship("А1-А4", 4);
        assertTrue(playGround.place(warship));
        assertTrue(playGround.shoot("А1"));
        assertTrue(playGround.shoot("А2"));
        assertTrue(playGround.shoot("А3"));
        assertFalse(playGround.isLose());
        assertTrue(playGround.shoot("А4"));
        assertTrue(playGround.isLose());
        String destroyedShipPlayGround = new StringBuilder("   А Б В Г Д Е Ж З И К").append(ln)
                .append(" 1 ░ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 2 ░ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 3 ░ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 4 ░ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 5 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 6 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 7 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 8 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append(" 9 _ _ _ _ _ _ _ _ _ _").append(ln)
                .append("10 _ _ _ _ _ _ _ _ _ _").append(ln)
                .toString();
        playGround.show();
        assertThat(this.out.toString(), is(destroyedShipPlayGround));
    }
}
