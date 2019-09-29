package ru.job4j.isp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;

/**
 * Класс MenuInit - Класс для работы с меню, предстваленным классом MenuParagraph.
 * Можно отобразить все меню, и затем предложить пользователю выбрать
 * конкретный пункт путем ввода его имени в консоль.
 * При выборе пункта, выполнится действие, определенное в методе 'run' выбранного пункта.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class MenuInit {
    private IMenu menu;
    private Input input;
    private final Consumer<String> output;
    private String lvlOut = "----";

    public MenuInit(IMenu menu, Input input, Consumer<String> output) {
        this.menu = menu;
        this.input = input;
        this.output = output;
    }

    public void show() {
        this.showMenu(this.menu, 0);
    }

    private void showMenu(IMenu menuP, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(lvlOut);
        }
        output.accept(sb.toString() + menuP.toString());
        level++;
        for (IMenu paragraph : menuP.getChildren()) {
            this.showMenu(paragraph, level);
        }
    }

    public void chose() {
        String chose = this.input.ask("Выберете пункт меню");
        this.choseMenu(menu, chose);

    }

    private void choseMenu(IMenu menuP, String chose) {
        if (chose.equals(menuP.getName())) {
            menuP.run();
        } else {
            for (IMenu paragraph : menuP.getChildren()) {
                this.choseMenu(paragraph, chose);
            }
        }

    }

    public static void main(String[] args) {
        MenuParagraph menu = new MenuParagraph("Задача 1", "action_1", List.of(
                new MenuParagraph("Задача 1.1.", "action_1_1", List.of(
                        new MenuParagraph("Задача 1.1.1.", "action_1_1_1", new ArrayList<>()),
                        new MenuParagraph("Задача 1.1.2.", "action_1_1_2", new ArrayList<>()) {
                            @Override
                            public void run() {
                                System.out.println("Задача 1.1.2. Метод run");
                            }
                        },
                        new MenuParagraph("Задача 1.1.3.", "action_1_1_3", new ArrayList<>())
                )),
                new MenuParagraph("Задача 1.2.", "action_1_2", Collections.emptyList())
        )) {
            @Override
            public void run() {
                System.out.println("Задача 1. Метод run");
            }
        };
        Input input = new ConsoleInput();
        MenuInit menuInit = new MenuInit(menu, input, System.out::println);
        menuInit.show();
        menuInit.chose();
    }
}
