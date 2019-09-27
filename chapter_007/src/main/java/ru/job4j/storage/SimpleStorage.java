package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.List;

public abstract class SimpleStorage implements IStorageStrategy {

    /**
     * Массив для хранение продуктов;
     */
    protected List<Food> foods;


    /**
     * Метод возвращает копию массива this.foods без null элементов;
     *
     * @return List<Food>.
     */
    @Override
    public List<Food> findAll() {
        return foods;
    }

    /**
     * Метод должен удалить продукт в списке. Для этого необходимо найти
     * продукт в списке. Метод должен вернуть boolean результат - удалось ли провести операцию.
     *
     * @return boolean.
     */
    @Override
    public boolean delete(Food food) {
        return foods.remove(food);
    }

    @Override
    public void clean(List<Food> f) {
        f.clear();
    }
}
