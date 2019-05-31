package ru.job4j.chat;

public class Exit implements ChatAction  {

    @Override
    public void execute(Input input) {
        System.out.println("bye bye");
    }
}
