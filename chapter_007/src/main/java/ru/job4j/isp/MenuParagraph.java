package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс MenuParagraph - Представляет собой меню - иерархическую структуру с пунктами и подпунктами.
 * Каждому пункту соответсвует определенное описание и действие (определенное в методе 'run')
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class MenuParagraph implements IMenu, IParagraph {
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
    private List<IMenu> children;

    /**
     * Конструктор, инициализирующий поля
     * @param name название пункта
     * @param action описание действия
     * @param children список подпунктов
     */
    public MenuParagraph(String name, String action, List<IMenu> children) {
        this.children = new ArrayList<>();
        this.name = name;
        this.action = action;
        this.children = children;
    }

    /**
     * Возвращает подпункты.
     * @return List<MenuParagraph>
     */
    @Override
    public List<IMenu> getChildren() {
        return this.children;
    }

    /**
     * Добавляет в спсиок подпунктов ноый.
     * @param paragraph
     */
    @Override
    public void addParagraph(IMenu paragraph) {
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

    @Override
    public String getName() {
        return name;
    }
}
