package ru.job4j.interactcalc.actions;

public interface Action {
    /**
     * Метод возвращает ключ опции.
     * @return key - ключ.
     */
    int key();

    /**
     * Основной метод.
     * //todo @param tracker - объект типа Tracker
     */
    void execute();

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return info - Строка меню
     */
    String info();
}