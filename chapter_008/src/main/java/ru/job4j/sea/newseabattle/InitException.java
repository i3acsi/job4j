package ru.job4j.sea.newseabattle;

public class InitException extends RuntimeException {
    private String msg;

    InitException(String load) {
        super("", null, false, false);
        this.msg = load;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
