package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        return persons.stream()
                .filter(x -> x.getName().equals(key)
                        | x.getAddress().equals(key)
                        | x.getPhone().equals(key)
                        | x.getSurname().equals(key))
                .collect(Collectors.toList());
    }
}