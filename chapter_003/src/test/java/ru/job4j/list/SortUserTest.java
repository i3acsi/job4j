package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void when2on2ArrayThenList4() {
        SortUser sortUser = new SortUser();
        User[] users = new User[]{
                new User(0, "Vasiliy", "Gasevskiy", "Nsk", 32),
                new User(2, "Ivan", "Ivanov", "Bryansk", 31),
                new User(2, "Ivan", "Ivanov", "Bryansk", 34),
                new User(3, "Child", "Kid", "Kinder Garden", 4)
        };
        Set<User> result = sortUser.sort(Arrays.asList(users));
        Set<User> expected = new LinkedHashSet<>();
        expected.add(users[3]);
        expected.add(users[1]);
        expected.add(users[0]);
        expected.add(users[2]);
        assertThat(result, is(expected));
    }
}
