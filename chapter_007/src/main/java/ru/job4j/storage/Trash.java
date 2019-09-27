package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.ArrayList;

/**
 * Класс Trash - Представляет хранилище продуктов - мусор.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class Trash extends SimpleStorage {

    public Trash() {
        super.foods = new ArrayList<>();
    }
    /**
     * Метод реализаущий добавление продукта в мусор;
     *
     * @param food продукт
     */
    @Override
    public boolean add(Food food) {
        double expDate = food.getExpDate();
        double lifetime = food.getLifeTime();
        boolean result = false;
        if ((lifetime / expDate) >= 1) {
            result = super.foods.add(food);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Trash{}" + super.toString();
    }
}
