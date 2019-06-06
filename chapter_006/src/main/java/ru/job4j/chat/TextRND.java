package ru.job4j.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TextRND {
    private final Random random = new Random();
    private Map<Integer, String> map;
    private Integer count = 0;

    public TextRND(String file) {
        String out;
        map = new HashMap<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            out = read.readLine();
            while (out != null) {
                map.put(count++, read.readLine());
                out = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnswer() {
        int i = random.nextInt(count);
        return map.get(i);
    }
}
