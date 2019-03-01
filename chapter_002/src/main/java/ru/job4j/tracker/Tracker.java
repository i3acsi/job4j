package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
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
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
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
        return Arrays.copyOf(this.items, position);
    }

    /**
     * Метод проверяет в цикле все элементы массива, сравнивая id с аргументом String id и
     * возвращает найденный Item. Если Item не найден - возвращает null.
     *
     * @return Item.
     */
    public Item findById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Метод должен заменить ячейку в массиве this.items. Для этого необходимо найти
     * ячейку в массиве по id. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    public boolean replace(String id, Item item) {

        findById(id).setNull();
        findById(id).setTask(item.getTask());
        findById(id).setDescription(item.getDesk());
        findById(id).setDateCreation(item.getDateCreation());
        findById(id).setComments(item.getComments());
        return (findById(id).equals(item));
    }

    public boolean delete(String id) {
        Item[] newItems = new Item[items.length - 1];
        int i = 0
        for (; i < items.length; i++) {
            if (items[j].getId().equals(id)) {
                break;
            }
        }
        items = System.arraycopy();
        return true;
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.add(new Item("task1", "desc1"));
        tracker.add(new Item("task2", "desc2"));
        tracker.add(new Item("task3", "desc3"));
        Item item1 = new Item("Kucha", "mala");
        for (Item item : tracker.findAll()) {
            System.out.println(item.getTask() + " " + item.getId());
        }
    }
}

class Item {
    private String task;
    private String description;
    private long dateCreation;
    private String comments;
    private String id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) this.getDateCreation();
        result = prime * result + this.getTask().hashCode();
        return result;
    }

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
                & this.getDesk().equals(other.getDesk())
                & this.getDateCreation() == other.getDateCreation()
                & this.getComments().equals(other.getComments())
                & this.getId().equals(other.getId())
        );
    }

    public Item(String task, String desc) {
        this.task = task;
        this.description = desc;
        this.dateCreation = (new Date()).getTime();
    }

    public Item(Item item) {
        this.task = item.task;
        this.description = item.description;
        this.dateCreation = (new Date().getTime());
    }

    public Item setNull() {
        return null;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreation(long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComments(String comment) {
        this.comments = comments + "$$" + comment;
    }

    public String getTask() {
        return this.task;
    }

    public String getDesk() {
        return this.description;
    }

    public long getDateCreation() {
        return this.dateCreation;
    }

    public String getComments() {
        return this.comments;
    }

    public String getId() {
        return this.id;
    }
}