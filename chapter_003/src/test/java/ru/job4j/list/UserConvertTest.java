package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void when2on2ArrayThenList4() {
        UserConvert userConvert = new UserConvert();
        User[] users = new User[]{
                new User(0, "Vasiliy", "Gasevskiy", "Nsk", 32),
                new User(2, "Ivan", "Ivanov", "Bryansk", 31),
                new User(2, "Ivan", "Ivanov", "Bryansk", 34)
        };
        List<User> list = List.of(users);
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        Arrays.stream(users).forEach(x->expected.put(x.getId(), x));
        assertThat(result, is(expected));
    }
}
