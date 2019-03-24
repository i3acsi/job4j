package ru.job4j.tracker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.job4j.tracker.Item;

import static java.lang.Thread.sleep;

/**
 * Класс Tracker - является оберткой над массивом. Представляет хранилище заявок.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
enum Tracker {
    INSTANCE;

    public static Tracker getInstance() {
        return INSTANCE;
    }

    /**
     * Массив для хранение заявок.
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        String id = this.generateId();
        item.setId(id);
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Метод возвращает копию массива this.items без null элементов;
     *
     * @return Item[].
     */
    public ArrayList<Item> findAll() {
        return items;
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая id с аргументом String id и
     * возвращает найденный Item. Если Item не найден - возвращает null.
     *
     * @return Item.
     */
    public Item findById(String id) {
        return items.stream().filter(x -> id.equals(x.getId())).findAny().orElse(null);
    }

    /**
     * Метод должен заменить ячейку в массиве. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    public boolean replace(String id, Item item) {
        int index = items.indexOf(this.findById(id));
        if (index >= 0) {
            items.remove(index);
            item.setId(id);
            items.add(index, item);
        }
        return index >= 0;
        //        boolean result = false;
//        for (int i = 0; i < items.length; i++) {
//            if (items[i] != null && items[i].getId().equals(id)) {
//                items[i] = item;
//                items[i].setId(id);
//                result = true;
//            }
//        }
//        return result;
    }

    /**
     * Метод должен удалить ячейку в массиве. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    public boolean delete(String id) {
        int index = items.indexOf(this.findById(id));
        if (index >= 0) {
            items.remove(index);
        }
        return index >= 0;
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая name с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;
     *
     * @return Item[].
     */
    public ArrayList<Item> findByName(String key) {
        return items.stream().filter(x -> key.equals(x.getTask())).collect(Collectors.toCollection(ArrayList::new));
//        Item[] result = new Item[this.position];
//        int count = 0;
//        for (int i = 0; i < this.position; i++) {
//            if (items[i].getTask().equals(key)) {
//                result[count++] = items[i];
//            }
//        }
//        return Arrays.copyOf(result, count);
    }

    public void clean() {
        items.clear();
    }
}

