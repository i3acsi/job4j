package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileSearchTest {
    @Test
    public void whenFindByNameThenFileHasResult() {
        FileSearch fileSearch = new FileSearch();
        fileSearch.find("../", "pom.xml", "-f", "./result.txt");
        File f = new File("./result.txt");
        assert (f.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            reader.lines().forEach(x -> assertThat(x.endsWith("pom.xml"), is(true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByMaskThenFileHasResult() {
        FileSearch fileSearch = new FileSearch();
        fileSearch.find("../", "*.txt", "-m", "./result.txt");
        File f = new File("./result.txt");
        assert (f.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            reader.lines().forEach(x -> assertThat(x.endsWith(".txt"), is(true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByExpThenFileHasResult() {
        FileSearch fileSearch = new FileSearch();
        fileSearch.find("../", "pom", "-r", "./result.txt");
        File f = new File("./result.txt");
        assert (f.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            reader.lines().forEach(x -> assertThat(x.substring(x.lastIndexOf(File.separator)).split("\\.")[0].contains("pom"), is(true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}