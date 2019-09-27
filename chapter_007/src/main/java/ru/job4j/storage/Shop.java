package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.ArrayList;

/**
 * Класс Shop - Представляет хранилище продуктов - магазин.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version 0.1
 */
public class Shop extends SimpleStorage {

    public Shop() {
        super.foods = new ArrayList<>();
    }

    /**
     * Метод реализаущий добавление продукта в магазин;
     *
     * @param food продукт
     */
    @Override
    public boolean add(Food food) {
        double expDate = food.getExpDate();
        double lifetime = food.getLifeTime();
        boolean result = false;
        double dateRelation = lifetime / expDate;
        if (dateRelation >= 0.25 && dateRelation < 1) {
            if (dateRelation > 0.75) {
                food.setDiscount(Math.round(food.getPrice() * 10.00) / 100.00);
            }
            result = super.foods.add(food);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Shop{}" + super.toString();
    }
}
