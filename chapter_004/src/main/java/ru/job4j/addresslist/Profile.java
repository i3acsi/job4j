package ru.job4j.addresslist;

import java.util.List;
import java.util.stream.Collectors;

public class Profile {
    private Address address;
    private String name;
    private int passport;



    public Profile(Address address, String name, int passport) {
        this.address = address;
        this.name = name;
        this.passport = passport;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public int getPassport() {
        return passport;
    }
}
