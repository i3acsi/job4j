package ru.job4j.interactcalc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {
    private final String ln = System.lineSeparator();
    private final String menu = ("0. Сложить" + ln
            + "1. Вычесть" + ln
            + "2. Умножить" + ln
            + "3. Разделить");
    private final Calculator calculator = new Calculator();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
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

    /**
     * Тест сложения.
     */
    @Test
    public void when2Plus3Then5() {
        Input input = new StubInput(new String[]{"0", "2", "3", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Сложить")
                                .append(ln)
                                .append("5.0")
                                .append(ln))
                                .toString()

                )
        );

    }

    /**
     * Тест вычетания.
     */
    @Test
    public void when5Minus3Then2() {
        Input input = new StubInput(new String[]{"1", "5", "3", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Вычесть")
                                .append(ln)
                                .append("2.0")
                                .append(ln))
                                .toString()

                )
        );

    }

    /**
     * Тест умножения.
     */
    @Test
    public void when5Multiply3Then15() {
        Input input = new StubInput(new String[]{"2", "5", "3", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Умножить")
                                .append(ln)
                                .append("15.0")
                                .append(ln))
                                .toString()

                )
        );

    }

    /**
     * Тест деления.
     */
    @Test
    public void when5Divide2Then2point5() {
        Input input = new StubInput(new String[]{"3", "5", "2", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Разделить")
                                .append(ln)
                                .append("2.5")
                                .append(ln))
                                .toString()

                )
        );

    }

    /**
     * Тест использования предыдущего результата.
     */
    @Test
    public void usePreviousResult() {
        Input input = new StubInput(new String[]{"3", "5", "2", "n", "2", "", "2", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Разделить")
                                .append(ln)
                                .append("2.5")
                                .append(ln)
                                .append(menu)
                                .append(ln)
                                .append("Умножить")
                                .append(ln)
                                .append("5.0")
                                .append(ln)
                        )
                                .toString()

                )
        );

    }

    /**
     * Тест некорректного ввода.
     */
    @Test
    public void incorrectInput() {
        Input input = new StubInput(new String[]{"0", "test", "4", "5", "y"});
        NumericInput numericInput = new NumericInput(input);
        new StartUI(input, numericInput, calculator, output).init();
        assertThat(this.out.toString(),
                is(
                        (new StringBuilder()
                                .append(menu)
                                .append(ln)
                                .append("Сложить")
                                .append(ln)
                                .append("9.0")
                                .append(ln))
                                .toString()

                )
        );

    }

    @Test
    public void incorrectInput2() {
        boolean flag = false;
        Input input = new StubInput(new String[]{"90"});
        NumericInput numericInput = new NumericInput(input);
        try {
            new StartUI(input, numericInput, calculator, output).init();
        } catch (MenuOutException e) {
            flag = true;
        }
        assertThat(flag, is(true));
    }
}
