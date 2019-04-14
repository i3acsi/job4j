package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    private final SortUser sortUser = new SortUser();
    private final User[] users = new User[]{
            new User(0, "Vasiliy", "Gasevskiy", "Nsk", 32),
            new User(1, "Petr", "Arsentev", "Bryansk", 31),
            new User(2, "Ivan", "Ivanov", "Bryansk", 34),
            new User(3, "Child", "Kid", "Kinder Garden", 4),
            new User(4, "Child", "Kid", "Kinder Garden", 344),
            new User(5, "Child", "Kid", "Kinder Garden", 37)

    };

    @Test
    public void whenSortedByAge() {
        Set<User> result = sortUser.sort(Arrays.asList(users));
        Set<User> expected = Set.of(
                users[3],
                users[1],
                users[0],
                users[2],
                users[5],
                users[4]);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortedByName() {
        List<User> result = sortUser.sortNameLength(Arrays.asList(users));
        List<User> expected = List.of(
                users[3],
                users[4],
                users[5],
                users[2],
                users[1],
                users[0]);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortedTwice() {
        List<User> result = sortUser.sortByAllFields(Arrays.asList(users));
        List<User> expected = List.of(
                users[3],
                users[5],
                users[4],
                users[2],
                users[1],
                users[0]);
        assertThat(result, is(expected));
    }
}
