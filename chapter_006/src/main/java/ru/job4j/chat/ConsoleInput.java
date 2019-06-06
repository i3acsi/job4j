package ru.job4j.chat;

import java.util.Scanner;

public class ConsoleInput implements Input {

    Scanner sc = new Scanner(System.in, System.getProperty("console.encoding", "utf-8"));

    @Override
    public String ask() {
        return sc.nextLine().toLowerCase().replaceAll("[^A-Za-zА-Яа-я0-9]", "");
    }
}
