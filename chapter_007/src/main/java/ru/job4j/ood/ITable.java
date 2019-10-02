package ru.job4j.ood;

public interface ITable {
    /**
     * Получить представление игрового поля в виде двумерного массива.
     *
     * @return table - игровое поле, заполненное 1,2 или 0.
     * Что соответствует O,X или пустой ячейке.
     */
    int[][] getTable();

    /**
     * Получить текущее игровое состояние: 1 или 2 - победа, -1 - ничья, или 0 - незавершенная игра.
     *
     * @return win - код состояния игры.
     */
    int win();

    /**
     * Поставить X или O на поле
     *
     * @param i, j - координаты
     * @param p  - код игрока
     * @return boolean result
     */
    boolean add(int i, int j, int p);

    /**
     * Очистить игровое поле
     */
    void clear();

    void show();

    void fill(int[][] data);
}
