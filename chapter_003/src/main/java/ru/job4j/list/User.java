package ru.job4j.list;

import java.util.Comparator;

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String surname;
    private String city;
    private int age;

    public User(int id, String name, String surname, String city, int age) {
        this.city = city;
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }

    public int compareThis(User o) {
        int result = Integer.compare(this.age, o.age);
        if (result == 0) {
            result = o.getName().compareTo(this.getName());
        } else result = 0;
        System.out.println(result+" "+o.getName()+""+this.getName());
        return result;
    }

}
