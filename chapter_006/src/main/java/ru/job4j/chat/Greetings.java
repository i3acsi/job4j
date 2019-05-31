package ru.job4j.chat;

public class Greetings implements ChatAction {

    @Override
    public void execute(Input input) {
        System.out.println("Привет!");
    }
}
