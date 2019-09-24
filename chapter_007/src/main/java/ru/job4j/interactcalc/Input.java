package ru.job4j.interactcalc;

public interface Input {
    Double[] CURRENT = new Double[1];

    String ask(String question);

    int ask(String question, int[] range);

    default double getDouble(String param) {
        String question = String.format("введите %s число или ввод для использования предыдущего результата", param);
        String str = this.ask(question);
        if ("".equals(str) & CURRENT[0] != null) {
            return CURRENT[0];
        }
        while (!this.isDouble(str)) {
            str = this.ask(question);
        }

        return Double.parseDouble(str);
    }

    default void setCurrent(double current) {
        Input.CURRENT[0] = current;
    }

    default boolean isDouble(String str) {
        return (str.matches("-?\\d+\\.?\\d*"));
    }
}
