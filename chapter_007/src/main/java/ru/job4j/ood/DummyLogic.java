package ru.job4j.ood;

public class DummyLogic implements ILogic {

    /**
     * Просто размещает X или O на поле там, где есть незанятая ячейка.
     * Пустую ячейку находим последовательным сканированием поля - как только нашли, ставим свою отметку
     *
     * @param player - 1 для O, 2 для X.
     */
    public boolean move(ITable table, int player) {
        int[][] field = table.getTable();
        int size = field.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 0) {
                    return table.add(j, i, player);
                }
            }
        }
        return false;
    }
}
