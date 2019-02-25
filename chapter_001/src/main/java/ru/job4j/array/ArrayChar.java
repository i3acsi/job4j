package ru.job4j.array;

/**
 * Обертка над строкой.
 */
public class ArrayChar {
    private char[] word;

    public ArrayChar(String line) {
        this.word = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        char[] pref = prefix.toCharArray();
        for (int i = 0; i < prefix.length(); i++) {
            if (word[i] != pref[i]) {
                return false;
            }
        }
        return true;
    }
}