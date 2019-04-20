package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */

public class EvenIterator implements Iterator {
    private final int[] values;
    private int currentPos = 0, index = 0;

    public EvenIterator(final int[] numbs) {
        this.values = numbs;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        //Проверка на -1 для того, чтобы вызов hasNext повторно - не вызывал бы ошибок
        if (currentPos == -1) {
            return true;
        }
        // Проверка на -2 для того, чтобы вызов hasNext при уже
        // полученном результате false - не проходить цикл заново, т.к. результат уже известен.
        if (currentPos == -2) {
            return false;
        }
        if (currentPos == index) {
            for (int i = index; i < values.length; i++) {
                if (values[i] % 2 == 0) {
                    index = i;
                    result = true;
                    currentPos = -1;
                    break;
                }
                result = false;
                currentPos = -2;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = values[index++];
        currentPos = index;
        return result;
    }
}
