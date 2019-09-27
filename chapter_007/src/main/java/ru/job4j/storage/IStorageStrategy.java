package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.List;

public interface IStorageStrategy {
    boolean add(Food food);

    boolean delete(Food food);

    List<Food> findAll();

    void clean(List<Food> foods);
}
