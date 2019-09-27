package ru.job4j.storage.product;

import java.time.LocalDate;

public class IceCream extends Food {
    public IceCream(String name, LocalDate expireDate, LocalDate createDate, double price) {
        super(name, expireDate, createDate, price);
    }
}
