package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class SQLStorageTest {

    @Test
    public void test() {
        SQLStorage sqlStorage = new SQLStorage();
        sqlStorage.init();
    }

}