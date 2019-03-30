package ru.job4j.coffe;

import java.util.Scanner;
import java.util.function.Predicate;

public class Input {
    public int accept(String question, Predicate<Integer> p) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        while (value <= 0) {
            System.out.println(question);
            String line = scanner.nextLine();
            if (isNomber(line)) {
                value = Integer.valueOf(line);
                if (!p.test(value)) {
                    value = 0;
                }
            }
        }
        return value;

    }

    private boolean isNomber(String line) {
        return line.matches("-?\\d+");
    }
}

