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
                new User(0, "Vasiliy", "Gasevskiy", "Nsk"),
                new User(2, "Ivan", "Ivanov", "Bryansk"),
                new User(2, "Ivan", "Ivanov", "Bryansk")
        };
        List<User> list = new ArrayList<>(Arrays.asList(users));
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        for (User u : users) {
            expected.put(u.getId(), u);
        }
        assertThat(result, is(expected));
    }
}
