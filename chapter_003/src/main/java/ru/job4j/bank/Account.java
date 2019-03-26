package ru.job4j.bank;

public class Account {

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public boolean transfer(Account other, double amount) {
        boolean result = false;
        if (value >= amount) {
            value -= amount;
            other.value += amount;
            result = true;
        }
        return result;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Счет № " + requisites + ". Сумма на счету: " + value + " рублей.";
    }

    public Account(double value) {
        this.value = value;
        String requisites = "no requisites yet";
    }

    /**
     * Value - количество денег.
     */
    private double value;

    /**
     * Реквизиты счета
     */
    private String requisites;
}
