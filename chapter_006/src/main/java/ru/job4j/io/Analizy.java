package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        String[][] strings = {new String[0]};
        StringBuilder builder = new StringBuilder();
        boolean[] flag = new boolean[]{false};
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines()
                    .filter(x -> (x.length() != 0))
                    .peek(x -> strings[0] = x.split(" "))
                    .forEach(x -> {
                        if (!flag[0] & (strings[0][0].equals("400") || strings[0][0].equals("500"))) {
                            builder.append(strings[0][1]);
                            flag[0] = true;
                        }
                        if (flag[0] & !(strings[0][0].equals("400") || strings[0][0].equals("500"))) {
                            builder.append(" ").append(strings[0][1]).append(System.lineSeparator());
                            flag[0] = false;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

        save(builder.toString(), source);

    }


    private void save(String data, String source) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
