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
        List<Person> result = new ArrayList<>();

        return persons.stream()
//                .filter((x -> x.getAddress().equals(key)))
                .filter(x->x.getName().equals(key))
//                .filter(x->x.getPhone().equals(key))
//                .filter(x->x.getSurname().equals(key))
                        .collect(Collectors.toList());
    }
}