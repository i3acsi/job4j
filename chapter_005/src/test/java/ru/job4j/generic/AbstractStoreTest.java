package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore userStore;
    private RoleStore roleStore;
    private Iterator<User> userIterator;
    private Iterator<Role> roleIterator;
    private static final User[] USERS = new User[]{new User("0"), new User("1"), new User("2")};
    private static final Role[] ROLES = new Role[]{new Role("0"), new Role("1"), new Role("2")};

    @Before
    public void init() {
        userStore = new UserStore();
        roleStore = new RoleStore();
        userIterator = userStore.iterator();
        roleIterator = roleStore.iterator();
    }

    @Test
    public void whenItemAddThenStoreHaveItem() {
        userStore.add(USERS[0]);
        roleStore.add(ROLES[0]);
        assertThat(userStore.findById("0"), is(USERS[0]));
        assertThat(roleStore.findById("0"), is(ROLES[0]));
    }

    @Test
    public void whenReplaceItemThenStoreHaveOtherItem() {
        userStore.add(USERS[0]);
        roleStore.add(ROLES[0]);
        userStore.replace("0", USERS[1]);
        roleStore.replace("0", ROLES[1]);
        assert (userStore.findById("0") == null);
        assertThat(userStore.findById("1"), is(USERS[1]));
        assert (roleStore.findById("0") == null);
        assertThat(roleStore.findById("1"), is(ROLES[1]));
    }

    @Test
    public void whenDeleteThenSoreHaveNoItem() {
        userStore.add(USERS[0]);
        userStore.add(USERS[1]);
        userStore.add(USERS[2]);
        roleStore.add(ROLES[0]);
        roleStore.add(ROLES[1]);
        roleStore.add(ROLES[2]);
        int userSizeBefore = userStore.size();
        int roleSizeBefore = roleStore.size();
        userStore.delete("1");
        roleStore.delete("2");
        assertThat(userSizeBefore, is(userStore.size() + 1));
        assertThat(roleSizeBefore, is(roleStore.size() + 1));
    }

    @Test
    public void iteratorTest() {
        userStore.add(USERS[0]);
        userStore.add(USERS[1]);
        userStore.add(USERS[2]);
        roleStore.add(ROLES[0]);
        roleStore.add(ROLES[1]);
        roleStore.add(ROLES[2]);
        userIterator = userStore.iterator();
        roleIterator = roleStore.iterator();
        int i = 0;
        while (userIterator.hasNext()) {
            assertThat(userIterator.next(), is(USERS[i++]));
        }
        i = 0;
        while (roleIterator.hasNext()) {
            assertThat(roleIterator.next(), is(ROLES[i++]));
        }
    }
}