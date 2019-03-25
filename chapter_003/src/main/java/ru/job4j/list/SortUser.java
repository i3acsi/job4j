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
//        for (int i = 1; i < users.size(); i++) {
//            User temp = null;
//            int compare = users.get(i-1).getName().compareTo(users.get(i).getName());
//            if (compare!=0){
//                temp = users.get(i-1);
//                users.set(i-1, users.get(i));
//                users.set(i, temp);
//            }
//        }


        return users.stream().sorted(User::compareThis).collect(Collectors.toList());
        //return users.stream().sorted().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }
}
