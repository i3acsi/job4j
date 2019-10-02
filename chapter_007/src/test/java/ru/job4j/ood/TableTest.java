package ru.job4j.ood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TableTest {
    private ITable table;
    private final String ln = System.lineSeparator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    ILogic logic = new DummyLogic();

    @Before
    public void loadOutput() {
        this.table = new Table(output);
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        table.clear();
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenPutXThanTableHaveIt() {
        whenPutMarkThanTableHaveIt(2, "X");
    }

    @Test
    public void whenPutOThanTableHaveIt() {
        whenPutMarkThanTableHaveIt(1, "O");
    }

    private void whenPutMarkThanTableHaveIt(int player, String mark) {
        assertThat(table.add(0, 0, player), is(true));
        table.show();
        assertThat(this.out.toString(), is(
                new StringBuilder("######")
                        .append(ln)
                        .append(mark)
                        .append(" _ _ ")
                        .append(ln)
                        .append("_ _ _ ")
                        .append(ln)
                        .append("_ _ _ ")
                        .append(ln)
                        .append(ln)
                        .toString()
        ));
    }

    @Test
    public void whenXisWinnerThanGet2() {
        whenThereIsWinnerThanGetDigit(2);
    }

    @Test
    public void whenOisWinnerThanGet1() {
        whenThereIsWinnerThanGetDigit(1);
    }

    @Test
    public void whenNoWinnerThanGet0() {
        whenThereIsWinnerThanGetDigit(0);
    }

    private void whenThereIsWinnerThanGetDigit(int player) {
        table.fill(new int[][]{{player, player, player}, {0, 0, 0}, {0, 0, 0}});
        assertThat(table.win(), is(player));
    }

    @Test
    public void whenGameOverAndNoWinnerThanGetMinus1() {
        table.fill(new int[][]{{2, 1, 2}, {2, 1, 1}, {1, 2, 2}});
        assertThat(table.win(), is(-1));
    }

    @Test
    public void whenComputerMakesMoveThanTableHaveMark() {
        assertThat(logic.move(table, 1), is(true));
        assertThat(logic.move(table, 2), is(true));
        table.show();
        assertThat(this.out.toString(), is(
                new StringBuilder("######")
                        .append(ln)
                        .append("O X _ ")
                        .append(ln)
                        .append("_ _ _ ")
                        .append(ln)
                        .append("_ _ _ ")
                        .append(ln)
                        .append(ln)
                        .toString()
        ));
    }
}
