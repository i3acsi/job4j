package ru.job4j.storage.product;

import java.time.LocalDate;

public class Cracker extends Food {
    /**
     * Конструктор с инициализацией всех параметров
     *
     * @param name
     * @param expireDate
     * @param createDate
     * @param price
     */
    public Cracker(String name, LocalDate expireDate, LocalDate createDate, double price) {
        super(name, expireDate, createDate, price);
    }
}
