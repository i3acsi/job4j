package ru.job4j.storage.product;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс  Food описывают бизнес модель продукта.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Food {
    /**
     * Название продукта.
     */
    private String name;

    /**
     * Дата окончания срока годности и дата производста продукта.
     */
    private LocalDate expireDate, createDate;

    /**
     * Цена продукта без скидки.
     */
    private double price;

    /**
     * Скидка на проукт.
     */
    private double discount;

    /**
     * Конструктор с инициализацией всех параметров
     *
     * @param name название
     * @param expireDate дата окончания срока годности
     * @param createDate дата производства
     * @param price итоговая цена
     */
    public Food(String name, LocalDate expireDate, LocalDate createDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0.0;
    }

    public double getPrice() {
        return price;
    }

    private LocalDate getExpireDate() { return expireDate; }

    private LocalDate getCreateDate() {
        return createDate;
    }

    public double getDiscount() {
        return discount;
    }

    /**
     * @return expDate - срок годности в днях.
     */
    public double getExpDate() {
        return this.getExpireDate().toEpochDay() - this.getCreateDate().toEpochDay();
    }

    /**
     * @return lifeTime - время, которое прошо с даты изготовления (в днях).
     */
    public double getLifeTime() {
        return LocalDate.now().toEpochDay() - this.getCreateDate().toEpochDay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        if (Double.compare(food.price, price) != 0) {
            return false;
        }
        if (Double.compare(food.discount, discount) != 0) {
            return false;
        }
        if (!Objects.equals(name, food.name)) {
            return false;
        }
        if (!Objects.equals(expireDate, food.expireDate)) {
            return false;
        }
        return Objects.equals(createDate, food.createDate);
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Food.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("expireDate=" + expireDate)
                .add("createDate=" + createDate)
                .add("price=" + price)
                .add("discount=" + discount)
                .toString();
    }
}
