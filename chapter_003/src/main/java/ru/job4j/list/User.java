package ru.job4j.list;

public class User {
    private int id;
    private String name;
    private String surname;
    private String city;

    public User(int id, String name, String surname, String city) {
        this.city = city;
        this.surname = surname;
        this.name = name;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }
}
