package ru.job4j.chat;

public class Phrase implements ChatAction {
    private TextRND textRND;

    public Phrase(TextRND textRND) {
        this.textRND = textRND;
    }

    @Override
    public void execute(Input input) {
        System.out.println(textRND.getAnswer());
    }
}
