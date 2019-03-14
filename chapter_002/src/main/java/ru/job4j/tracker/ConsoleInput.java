package ru.job4j.tracker;

import java.util.Scanner;

class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question + ":");
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value: range) {
            if(value == key) {
                exist = true;
                break;
            }
        }
        return exist ? key : -1;
    }
}
