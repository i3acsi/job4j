package ru.job4j.seaBattle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SeaBattleGameTest {
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
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void singleGameTest() {
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
        assertThat(this.out.toString(), is(new StringBuilder("Разместите ваши корабли на игровом поле")
                .append(emptyPlayGround)
                .append("У вас :").append(ln)
                .append("\t\tодин четырехпалубный █ █ █ █").append(ln)
                .append("\t\tдва трехпалубных █ █ █\t█ █ █").append(ln)
                .append("\t\tтри двупалубных █ █\t█ █\t█ █").append(ln)
                .append("\t\tчетыре однопалубных █\t█\t█\t█").append(ln)
                .toString()
        ));
    }
}
