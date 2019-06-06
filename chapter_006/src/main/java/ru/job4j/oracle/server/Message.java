package ru.job4j.oracle.server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Message {
    private final Random random;
    private Map<Integer, String> map;
    private Integer count = 0;

    public Message(String file) {
        random = new Random();
        String out;
        map = new HashMap<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            out = read.readLine();
            while (out != null) {
                String[] phrases = out.split(" ");
                for (String s : phrases) {
                    map.put(count++, s);
                }
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
