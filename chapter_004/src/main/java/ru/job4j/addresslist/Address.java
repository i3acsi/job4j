package ru.job4j.addresslist;

public class Address {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public int getHome() {
        return this.home;
    }

    public int getApartment() {
        return this.apartment;
    }

    @Override
    public String toString() {
        return String.format("City: %s, Street: %s, Home: %s, Apartment: %s.",
                this.city,
                this.street,
                this.home,
                this.apartment
        );
    }
}
