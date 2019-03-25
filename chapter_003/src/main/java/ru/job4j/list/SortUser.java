package ru.job4j.list;

import java.util.*;
import java.util.stream.Collectors;

public class SortUser {
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    public List<User> sortNameLength(List<User> users) {
        return users.stream().sorted(Comparator.comparingInt(x -> x.getName().length())).collect(Collectors.toList());
    }

    public List<User> sortByAllFields(List<User> users) {
        return users.stream().sorted().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }
}
