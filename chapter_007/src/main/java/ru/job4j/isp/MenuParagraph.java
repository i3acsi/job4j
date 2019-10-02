package ru.job4j.isp;

import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс MenuParagraph - Представляет собой меню - иерархическую структуру с пунктами и подпунктами.
 * Каждому пункту соответсвует определенное описание и действие (определенное в методе 'run')
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class MenuParagraph implements Runnable, IParagraph {
    /**
     * Название пункта меню.
     */
    private String name;
    /**
     * Описание действия, привязанного к пунту.
     */
    private String action;
    /**
     * Список подпунктов.
     */
    private List<MenuParagraph> children;

    /**
     * Конструктор, инициализирующий поля
     *
     * @param name     название пункта
     * @param action   описание действия
     * @param children список подпунктов
     */
    public MenuParagraph(String name, String action, List<MenuParagraph> children) {
        this.children = new ArrayList<>();
        this.name = name;
        this.action = action;
        this.children = children;
    }

    /**
     * Возвращает подпункты.
     *
     * @return List<MenuParagraph>
     */

    public List<MenuParagraph> getChildren() {
        return this.children;
    }

    public List<MenuParagraph> getChildren(boolean reverse) {
        if (!reverse) {
            return this.children;
        }
        List<MenuParagraph> result = new ArrayList<>(this.children);
        Collections.reverse(result);
        return result;
    }

    /**
     * Добавляет в спсиок подпунктов ноый.
     *
     * @param paragraph
     */
    @Override
    public void addParagraph(MenuParagraph paragraph) {
        this.children.add(paragraph);
    }

    /**
     * Метод 'run' - выполняется, если пользователь выбрал этот пункт меню.
     * При создании меню его можно переопределить, а можно оставить реализацию по умолчанию.
     */
    @Override
    public void run() {
        System.out.println(this + "run");
    }

    @Override
    public String toString() {
        return name + "| action= " + action;
    }

    public String getName() {
        return name;
    }
}
