package ru.job4j.ood;

import java.util.function.Consumer;

/**
 * Класс Table представляет собой поле для игры в крестики-нолики.
 * Можно создать игровое поле разного размера (по умолчанию размер равен 3). Поле всегда квадратное.
 * Содержит методы для:
 * определения победителя.
 * размещения X или O на поле по указанным координатам.
 * отображения поля в консоли.
 * очистки поля.
 */
public class Table implements ITable {
    protected Consumer<String> output;
    protected int[][] table;

    /**
     * Конструктор, принимающий в качестве параметра рамер создаваемого поля.
     *
     * @param size - размер поля. Должен быть больше 0.
     */
    public Table(int size, Consumer<String> output) {
        this.table = new int[size][size];
        this.output = output;
    }

    /**
     * Конструктор по умолчанию. Создает игровое поле 3X3
     */
    public Table(Consumer<String> output) {
        this.table = new int[3][3];
        this.output = output;
    }

    /**
     * Возвращает двумерный массив, заполненный цифрами 0,1 и 2,
     * Представляющий собой игровое поле.
     *
     * @return table - игровое поле.
     */
    public int[][] getTable() {
        return table;
    }

    /**
     * Возвращает целое число, по которому можно определить - есть ли в данный момент победитель?
     * <p>
     * -1 - поле заполнено
     * 0 - победителя нет,
     * 1- победо O
     * 2 - победа X
     */
    public int win() {
        if (isWinVertical(2)) {
            return 2;
        }
        if (isWinVertical(1)) {
            return 1;
        }
        if (isWinHorizontal(2)) {
            return 2;
        }
        if (isWinHorizontal(1)) {
            return 1;
        }
        if (isWinDiag(2)) {
            return 2;
        }
        if (isWinDiag(1)) {
            return 1;
        }
        if (isFull()) {
            return -1;
        }
        return 0;
    }

    private boolean isFull() {
        int length = table.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (table[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinVertical(int p) {
        boolean result = true;
        int length = table.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (table[j][i] != p) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean isWinHorizontal(int p) {
        boolean result = true;
        int length = table.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (table[i][j] != p) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean isWinDiag(int p) {
        boolean result = true;
        int length = table.length;
        for (int i = 0; i < length; i++) {
            if (table[i][i] != p && table[i][length - 1 - i] != p) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Размещает O или X на поле.
     *
     * @param p - player 1 для O, 2 для X;
     * @param i координата по горизонтали
     * @param j координата по вертикали
     *          координаты передаются в виде целого числа от 1 до числа,
     *          равного размеру поля
     * @return boolean result - true, если удалось разместить O по указанным координатам, false, если нет.
     */

    public boolean add(int i, int j, int p) {
        if (i > table.length || j > table.length || i < 0 || j < 0) {
            throw new RuntimeException("Ячейка не существует");
        }
        boolean result = false;
        if (table[j][i] == 0) {
            table[j][i] = p;
            result = true;
        } else {
            //throw new RuntimeException("Ячейка уже занята!");
        }
        return result;
    }

    /**
     * Вывод содержимого поля в псевдографике
     */
    @Override
    public String toString() {
        String ln = System.lineSeparator();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                switch (table[i][j]) {
                    case (0):
                        result.append("_");
                        break;
                    case (1):
                        result.append("O");
                        break;
                    case (2):
                        result.append("X");
                        break;
                    default:
                        throw new RuntimeException("Ошибка инициализации поля");
                }
                result.append(" ");
            }
            result.append(ln);
        }
        return result.toString();
    }

    /**
     * Заполняет все поле нулями - очмщает поле от X и O;
     */
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = 0;
            }
        }
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(String.format("%s", "##"));
        }
        output.accept(sb.toString());
        output.accept(toString());
    }

    //// TODO: 30.09.2019 Del
    public void fill(int[][] table) {
        this.table = table;
    }
}
