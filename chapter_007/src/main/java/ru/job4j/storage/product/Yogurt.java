package ru.job4j.storage.product;

import java.time.LocalDate;

public class Yogurt extends Food {
    public Yogurt(String name, LocalDate expireDate, LocalDate createDate, double price) {
        super(name, expireDate, createDate, price);
    }
}
