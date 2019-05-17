package ru.job4j.stat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    private List<Analyze.User> userList;

    private List<Analyze.User> newUserList;

    @Test
    public void test() {
        userList = new ArrayList<>();
        userList.add(new Analyze.User(3, "name3"));
        userList.add(new Analyze.User(5, "name5"));
        userList.add(new Analyze.User(1, "name1"));
        userList.add(new Analyze.User(8, "name8"));
        userList.add(new Analyze.User(4, "name4"));
        userList.add(new Analyze.User(7, "name7"));
        userList.add(new Analyze.User(9, "name9"));
        userList.add(new Analyze.User(0, "name0"));
        userList.add(new Analyze.User(2, "name2"));
        userList.add(new Analyze.User(6, "name6"));

        newUserList = new ArrayList<>();
        newUserList.add(new Analyze.User(11, "name11 new"));
        newUserList.add(new Analyze.User(3, "name3"));
        newUserList.add(new Analyze.User(5, "name5"));
        newUserList.add(new Analyze.User(10, "name10 new"));
        newUserList.add(new Analyze.User(4, "name4"));
        newUserList.add(new Analyze.User(7, "name7"));
        newUserList.add(new Analyze.User(9, "name9 new"));
        newUserList.add(new Analyze.User(0, "name0 new"));
        newUserList.add(new Analyze.User(2, "name2 new"));
        newUserList.add(new Analyze.User(6, "name6"));

        Analyze analyze = new Analyze();

        assertThat(analyze.diff(userList, newUserList), is(new Analyze.Info(2, 3, 2)));

    }

    @Test
    public void whenAllDeleted() {
        List<Analyze.User> userList = new ArrayList<>();
        userList.add(new Analyze.User(1, "first"));
        userList.add(new Analyze.User(2, "second"));
        userList.add(new Analyze.User(3, "third"));
        List<Analyze.User> newUserList = new ArrayList<>();
        Analyze analyze = new Analyze();
        assertThat(analyze.diff(userList, newUserList), is(new Analyze.Info(0, 0, 3)));
    }

    @Test
    public void whenAllNew() {
        List<Analyze.User> userList = new ArrayList<>();
        List<Analyze.User> newUserList = new ArrayList<>();
        newUserList.add(new Analyze.User(1, "first"));
        newUserList.add(new Analyze.User(2, "second"));
        newUserList.add(new Analyze.User(3, "third"));
        Analyze analyze = new Analyze();
        assertThat(analyze.diff(userList, newUserList), is(new Analyze.Info(3, 0, 0)));
    }

    @Test
    public void whenNoChanges() {
        List<Analyze.User> userList = new ArrayList<>();
        userList.add(new Analyze.User(1, "first"));
        List<Analyze.User> newUserList = new ArrayList<>();
        newUserList.add(new Analyze.User(1, "first"));
        Analyze analyze = new Analyze();
        assertThat(analyze.diff(userList, newUserList), is(new Analyze.Info(0, 0, 0)));
    }


}