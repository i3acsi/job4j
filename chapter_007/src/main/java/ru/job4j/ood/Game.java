package ru.job4j.ood;

import java.util.function.Consumer;

/**
 * Класс Game содержит методы для проведения одной игры.
 */
public class Game implements IGame {
    /**
     * Объекты, реализующие данный интерфейс, содержат метод ask(String question),
     * результатом которого является строка - ответ пользователя на вопрос question.
     */
    private IInput input;

    /**
     * Игровое поле - на нем можно рисовать крестики и нолики, а так же оно скажет - есть ли победитель
     * или ничья. Может отобразить себя. Его можно очистить.
     */
    private ITable table;

    /**
     * Объекты, реализующие интерфейс Logic имеют публичный метод move, который принимает объект ITable,
     * и числовой идентификатор игрока - компьютера (1 или 2 для O или X соответственно)
     * Метод move делает.
     */
    private ILogic logic;

    /**
     * Вывод текстовой информации;
     */
    private Consumer<String> output;

    public Game(IInput input, ITable table, ILogic logic, Consumer<String> output) {
        this.input = input;
        this.table = table;
        this.logic = logic;
        this.output = output;
    }

    @Override
    public int singleGame(SimplePlayer player1, SimplePlayer player2, boolean playerFirst) {
        output.accept("Координаты вводятся в форммате x,y, где x - координата на поле по горизонтали\n" +
                "\t\ty - координата на поле по вертикали.\n" +
                "\t\tМинимальное число 1, максимальное - размер поля\n" +
                "\t\tНапрмер если ввести 2,2 то это будет центр поля 3Х3, 1,1 - левый верхний угол\n");
        if (playerFirst) {
            player1.turn();
            table.show();
        }
        while (table.win() == 0) {
            player2.turn();
            table.show();
            if (table.win() != 0) {
                break;
            }
            player1.turn();
            table.show();
        }
        int result = table.win();
        table.clear();
        return result;
    }
}
