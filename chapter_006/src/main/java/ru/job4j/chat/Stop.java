package ru.job4j.chat;

public class Stop implements ChatAction {

    @Override
    public void execute(Input input) {
        String str = input.ask();
        while (!str.equals("продолжить")) {
            str = input.ask();
        }
    }
}
