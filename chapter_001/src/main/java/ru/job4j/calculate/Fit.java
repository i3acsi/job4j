package ru.job4j.calculate;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    /**
     * Идеальный вес для мужщины = (рост в сантиметрах – 100) · 1,15.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15D;
    }

    /**
     * Идеальный вес для женщины = (рост в сантиметрах – 110) · 1,15.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15D;
    }
}