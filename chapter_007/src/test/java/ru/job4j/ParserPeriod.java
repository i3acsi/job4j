package ru.job4j;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

public class ParserPeriod {
    private static Random rn = new Random();

    @Test
    public void test() throws IOException {
        Files.newBufferedReader(Paths.get("C:/Users/Gasevskiy/Desktop/Работа/Фсс/dateOf.csv")).lines().forEach(this::parseThis);
    }

    private void parseThis(String str) {
        String[] strings = str.split(";");
        System.out.printf("%s;", strings[0]);
        System.out.println(dateAnalyze(strings[1]));
    }

    private String dateAnalyze(String str) {
        String[] date = str.split("\\.");
        LocalDate today = LocalDate.now();
        int curY = today.getYear();
        int accY = Integer.valueOf(date[2]);
        int dif = curY - accY;
        if (dif > 9) {
            dif = rn.nextInt((9 - 8) + 1) + 8;
        }
        return String.valueOf(dif);
    }
}
