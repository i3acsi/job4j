package ru.job4j.trackersingle2;

import java.util.Arrays;
import java.util.Random;
import  ru.job4j.tracker.Item;
import static java.lang.Thread.sleep;

/**
 * Класс Tracker - является оберткой над массивом. Представляет хранилище заявок.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private static Tracker instance;

    private Tracker() {
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    /**
     * Массив для хранение заявок.
     */
    private Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Генератор случайного числа
     */
    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return  item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        try {
            sleep(100 + RN.nextInt(10));
        } catch (Exception e) {
            e.getMessage();
        }
        return String.valueOf(RN.nextInt(100)) + "_" + String.valueOf(System.currentTimeMillis());
    }

    /**
     * Метод возвращает копию массива this.items без null элементов;
     *
     * @return Item[].
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая id с аргументом String id и
     * возвращает найденный Item. Если Item не найден - возвращает null.
     *
     * @return Item.
     */
    public Item findById(String id) {
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Метод должен заменить ячейку в массиве. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = item;
                items[i].setId(id);
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод должен удалить ячейку в массиве. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    public boolean delete(String id) {
        boolean result = false;
        int length = items.length;
        for (int i = 0; i < length; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = null;
                System.arraycopy(items, i + 1, items, i, length - i - 1);
                items[length - 1] = null;
                position--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая name с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;
     *
     * @return Item[].
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int count = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getTask().equals(key)) {
                result[count++] = items[i];
            }
        }
        return Arrays.copyOf(result, count);
    }
}

