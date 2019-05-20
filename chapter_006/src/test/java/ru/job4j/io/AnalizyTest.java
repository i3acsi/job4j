package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private static final String SEPARATOR = File.separator;
    private static final String TEMPDIR = System.getProperty("java.io.tmpdir");

    @Test
    public void test() {
        Analizy analizy = new Analizy();


        analizy.unavailable(TEMPDIR + "/unavailable.csv", "server.log1");

        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(TEMPDIR + "/unavailable.csv"))) {
            read.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result.get(0), is("10:57:01 10:59:01"));
        assertThat(result.get(1), is("11:01:02 11:02:02"));
    }

}