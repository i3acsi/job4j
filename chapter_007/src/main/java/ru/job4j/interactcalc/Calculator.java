package ru.job4j.interactcalc;

/**
 * Calculator.
 *
 * @author Vasiliy Gasevskiy.
 * @version 1
 * @since 19.02.2019
 */

public class Calculator {
    private double result;

    /**
     * Method add.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method multiply.
     */
    public void multiply(double first, double second) {
        this.result = second * first;
    }

    /**
     * Method divide.
     */
    public void divide(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method getResult.
     *
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}