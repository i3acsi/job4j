package ru.job4j.interactcalc;

public class NumericInput {
    private Input input;
    private Double current;

    public NumericInput(Input input) {
        this.input = input;
        this.current = null;
    }

    public double getDouble(String param) {
        String question = String.format("введите %s число или ввод для использования предыдущего результата", param);
        String str = input.ask(question);
        if ("".equals(str) & current != null) {
            return current;
        }
        while (!NumericInput.isDouble(str)) {
            str = input.ask(question);
        }

        return Double.parseDouble(str);
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    private static boolean isDouble(String str) {
        return (str.matches("-?\\d+\\.?\\d*"));
    }
}
