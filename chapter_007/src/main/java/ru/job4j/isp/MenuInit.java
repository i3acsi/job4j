package ru.job4j.isp;

import java.util.*;
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
    private MenuParagraph menu;
    private Input input;
    private final Consumer<String> output;
    private String lvlOut = "----";

    public MenuInit(MenuParagraph menu, Input input, Consumer<String> output) {
        this.menu = menu;
        this.input = input;
        this.output = output;
    }

    public void show() {
        List<MenuParagraph> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int level = 0;
        LinkedList<MenuLevel> nodeStack = new LinkedList<>();
        nodeStack.push(new MenuLevel(menu, 0));
        while (!nodeStack.isEmpty()) {
            MenuLevel current = nodeStack.removeLast();
            System.out.println(current);
            current.getMenuParagraph().getChildren(true).forEach(x ->
                    nodeStack.addLast(new MenuLevel(x, current.getLevel() + 1))
            );//nodeStack::addLast);
        }
    }

    private void showMenu(MenuParagraph menuP, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(lvlOut);
        }
        output.accept(sb.toString() + menuP.toString());
        level++;
        for (MenuParagraph paragraph : menuP.getChildren()) {
            this.showMenu(paragraph, level);
        }
    }

    public void chose() {
        String chose = this.input.ask("Выберете пункт меню");
        this.choseMenu(menu, chose);

    }

    private void choseMenu(MenuParagraph menuP, String chose) {
        if (chose.equals(menuP.getName())) {
            menuP.run();
        } else {
            for (MenuParagraph paragraph : menuP.getChildren()) {
                this.choseMenu(paragraph, chose);
            }
        }

    }

    public static void main(String[] args) {
        MenuParagraph menu = new MenuParagraph("Задача 1", "action_1", List.of(
                new MenuParagraph("Задача 1.1.", "action_1_1", List.of(
                        new MenuParagraph("Задача 1.1.1.", "action_1_1_1", new ArrayList<>()),
                        new MenuParagraph("Задача 1.1.2.", "action_1_1_2", new ArrayList<>())
                )),
                new MenuParagraph("Задача 1.2.", "action_1_2", List.of(
                        new MenuParagraph("Задача 1.2.1", "action_1_2_1", List.of(
                                new MenuParagraph("Задача 1.2.1.1", "action_1_2_1_1", new ArrayList<>())
                        ))
                )),
                new MenuParagraph("Задача 1.3", "action_1_3", List.of(
                        new MenuParagraph("Задача 1.3.1", "action_1_3_1", List.of(
                                new MenuParagraph("Задача 1.3.1.1", "action_1_3_1_1", new ArrayList<>()),
                                new MenuParagraph("Задача 1.3.1.2", "action_1_3_1_2", new ArrayList<>())
                        ))
                ))
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

class MenuLevel {
    private MenuParagraph menuParagraph;
    private int level;

    public MenuLevel(MenuParagraph menuParagraph, int level) {
        this.menuParagraph = menuParagraph;
        this.level = level;
    }

    public MenuParagraph getMenuParagraph() {
        return menuParagraph;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level; i++) {
            result.append("----");
        }
        result.append(menuParagraph);
        return result.toString();
    }
}
