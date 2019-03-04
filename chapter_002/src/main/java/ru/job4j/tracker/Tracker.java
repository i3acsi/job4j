package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Класс Tracker - является оберткой над массивом. Представляет хранилище заявок.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
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
    void add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
    Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая id с аргументом String id и
     * возвращает найденный Item. Если Item не найден - возвращает null.
     *
     * @return Item.
     */
    Item findById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
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
    boolean replace(String id, Item item) {
        if (findById(id) == null) {
            return false;
        }
        findById(id).setTask(item.getTask());
        findById(id).setDescription(item.getDesk());
        findById(id).setDateCreation(item.getDateCreation());
        findById(id).setComments(item.getComments());
        return (findById(id).equals(item));
    }

    /**
     * Метод должен удалить ячейку в массиве. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    boolean delete(String id) {
        if (findById(id) == null) {
            return false;
        }
        int length = this.position;
        Item temp = new Item(items[length - 1]);
        Item[] newItems = new Item[length - 1];
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (items[i].getId().equals(id)) {
                continue;
            }
            newItems[j++] = items[i];
        }
        items = newItems;
        this.position--;
        return (items[items.length - 1].equals(temp));
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая name с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его;
     *
     * @return Item[].
     */
    Item[] findByName(String key) {
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

/**
 * Класс  Item описывают бизнес модель заявки.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
class Item {
    /**
     * Название задачи
     */
    private String task;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * Время создания задачи
     */
    private long dateCreation;

    /**
     * Комментарии к  задаче
     */
    private String comments;

    /**
     * Id задачи
     */
    private String id;

    /**
     * Переопределим метод hashcode.
     *
     * @return hashcode элемента.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) this.getDateCreation();
        result = prime * result + this.getTask().hashCode();
        return result;
    }

    /**
     * Переопределим метод equals.
     *
     * @return boolean - результат сравнения двух элементов.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return (this.getTask().equals(other.getTask())
                && this.getDesk().equals(other.getDesk())
                && this.getDateCreation() == other.getDateCreation()
                && this.getId().equals(other.getId())
        );
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями. dateCreation - текущее время.
     * Comments - комментарри.
     *
     * @param task - название задачи.
     * @param desc - описание задачи.
     */
    Item(String task, String desc) {
        this.task = task;
        this.description = desc;
        this.dateCreation = (new Date()).getTime();
        this.comments = " ";
    }

    /**
     * Конструктор - создание нового объекта на основе уже существующего.
     */
    Item(Item item) {
        this.task = item.getTask();
        this.description = item.getDesk();
        this.dateCreation = item.dateCreation;
        this.id = item.getId();
        this.comments = item.getComments();
    }

    /**
     * Процедура определения названия задачи.
     *
     * @param task - название задачи.
     */
    void setTask(String task) {
        this.task = task;
    }

    /**
     * Процедура определения описания задачи.
     *
     * @param description - описание задачи.
     */
    void setDescription(String description) {
        this.description = description;
    }

    /**
     * Процедура определения времени создания задачи по уже имеющимя данным.
     *
     * @param dateCreation - время создания задачи.
     */
    void setDateCreation(long dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Процедура определения id задачи.
     *
     * @param id - id задачи.
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * Процедура определения комментариев к задаче.
     *
     * @param comment - название задачи.
     */
    void setComments(String comment) {
        this.comments = comment;
    }

    /**
     * Функция получения значения поля названия задачи.
     *
     * @return task - возвращает название задачи.
     */
    String getTask() {
        return this.task;
    }

    /**
     * Функция получения значения поля описания задачи.
     *
     * @return description - возвращает описание задачи.
     */
    String getDesk() {
        return this.description;
    }

    /**
     * Функция получения значения поля времени создания задачи.
     *
     * @return dateCreation - возвращает время создания задачи.
     */
    long getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Функция получения значения поля комментариев к задаче.
     *
     * @return comments - возвращает комментарии к задаче.
     */
    String getComments() {
        return this.comments;
    }

    /**
     * Функция получения значения поля id задачи.
     *
     * @return id - возвращает id задачи.
     */
    String getId() {
        return this.id;
    }
}