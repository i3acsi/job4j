package ru.job4j.isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import ru.job4j.tracker.StubInput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuInitTest {
    private MenuParagraph menu;
    private MenuInit menuInit;
    private StubInput input;
    private final String ln = System.lineSeparator();
    private final StringBuilder finalMenu = new StringBuilder("Задача 1| action= action_1" + ln)
            .append("----Задача 1.1.| action= action_1_1" + ln)
            .append("--------Задача 1.1.1.| action= action_1_1_1" + ln)
            .append("--------Задача 1.1.2.| action= action_1_1_2" + ln)
            .append("--------Задача 1.1.3.| action= action_1_1_3" + ln)
            .append("----Задача 1.2.| action= action_1_2" + ln);
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    /**
     * Определяю пункты меню. Метод 'run' можно переопрелить, или остваить реализацию по-умолчанию
     */
    @Before
    public void init() {
        System.setOut(new PrintStream(this.out));
        this.menu = new MenuParagraph("Задача 1", "action_1", List.of(
                new MenuParagraph("Задача 1.1.", "action_1_1", List.of(
                        new MenuParagraph("Задача 1.1.1.", "action_1_1_1", new ArrayList<>()),
                        new MenuParagraph("Задача 1.1.2.", "action_1_1_2", new ArrayList<>()) {
                            @Override
                            public void run() {
                                output.accept("Задача 1.1.2. Метод run");
                            }
                        },
                        new MenuParagraph("Задача 1.1.3.", "action_1_1_3", new ArrayList<>())
                )),
                new MenuParagraph("Задача 1.2.", "action_1_2", Collections.emptyList())
        )) {
            @Override
            public void run() {
                output.accept("Задача 1 Метод run");
            }
        };
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenRunMethodIsOverride() {
        this.input = new StubInput(new String[]{"Задача 1.1.2."});
        this.menuInit = new MenuInit(menu, input, output);
        menuInit.show();
        menuInit.chose();
        assertThat(this.out.toString(),
                is(finalMenu
                        .append("Задача 1.1.2. Метод run" + ln)
                        .toString()));

    }
}
