package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.ArrayList;

/**
 * Класс Warehouse - Представляет хранилище продуктов - склад.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class Warehouse extends SimpleStorage {

    public Warehouse() {
        super.foods = new ArrayList<>();
    }

    /**
     * Метод реализаущий добавление продукта на склад;
     *
     * @param food продукт
     */

    @Override
    public boolean add(Food food) {
        double expDate = food.getExpDate();
        double lifetime = food.getLifeTime();
        boolean result = false;
        if ((lifetime / expDate) < 0.25) {
            result = super.foods.add(food);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Warehouse{}" + super.toString();
    }
}