package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    private final SortUser sortUser = new SortUser();
    private final User[] users = new User[]{
            new User(0, "Vasiliy", "Gasevskiy", "Nsk", 32),
            new User(2, "Petr", "Arsentev", "Bryansk", 31),
            new User(2, "Ivan", "Ivanov", "Bryansk", 34),
            new User(3, "Child1", "Kid", "Kinder Garden", 4)
            new User(3, "Child2", "Kid", "Kinder Garden", 5)
    };

    @Test
    public void whenSortedByAge() {
        Set<User> result = sortUser.sort(Arrays.asList(users));
        Set<User> expected = new LinkedHashSet<>();
        expected.add(users[3]);
        expected.add(users[1]);
        expected.add(users[0]);
        expected.add(users[2]);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortedByName() {
        List<User> result  = sortUser.sortNameLength(Arrays.asList(users));
        List<User> expected = new ArrayList<>();
        expected.add(users[1]);
        expected.add(users[2]);
        expected.add(users[3]);
        expected.add(users[0]);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortedTwice() {
        List<User> result  = sortUser.sortByAllFields(Arrays.asList(users));
        List<User> expected = new ArrayList<>();
        expected.add(users[3]);
        expected.add(users[2]);
        expected.add(users[1]);
        expected.add(users[0]);
        for (User u: result) {
            System.out.println(u.getName());
        }
        assertThat(result, is(expected));

    }
}
