package ru.job4j.chat;

public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask() {
        String result = "";
        if (position < value.length) {
            result = this.value[this.position++];
        }
        return result.toLowerCase().replaceAll("[^A-Za-zА-Яа-я0-9]", "");
    }
}
