package ru.job4j.interactcalc;

import ru.job4j.interactcalc.actions.ArithmeticAction;

import java.util.function.Consumer;

public class EngineerMenu extends MenuCalculator {
    /**
     * Конструктор.
     *
     * @param input объект типа numericInput для ввода чисел
     * @param calculator   - для математических действий
     * @param output
     */
    public EngineerMenu(Input input, EngineerCalculator calculator, Consumer<String> output) {
        super(input, calculator, output);
    }

    @Override
    public void fillActions() {
        super.fillActions();
        this.actions.add(new CosineAction(4, "Косинус", (EngineerCalculator) this.calculator, this.input));
    }
}

class CosineAction extends ArithmeticAction {
    private EngineerCalculator calculator;

    protected CosineAction(int key, String info, EngineerCalculator calculator, Input input) {
        super(key, info, calculator, input);
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        double x = input.getDouble("");
        this.calculator.cosine(x);
    }
}
