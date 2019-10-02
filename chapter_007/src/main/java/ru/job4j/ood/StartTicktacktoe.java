package ru.job4j.ood;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;

import javax.sql.rowset.Predicate;
import java.util.function.Consumer;

/**
 * Создание игры крестики нолики.
 * <p>
 * Вывод данных в консоль в псевдографики.
 * <p>
 * Пользователь начинает игру. Вводи координаты точки.
 * <p>
 * Предусмотреть, чтобы компьютер мог начать игру.
 * <p>
 * Предусмотреть увеличение поле. По умолчанию используется поле 3 на 3.
 * <p>
 * Предусмотреть усложнение логики игры. Выигрывает тот кто соберет 5 подряд.
 */
public class StartTicktacktoe {
    private ITable table;
    private IGame game;
    private IInput input;
    private Consumer<String> output;

    public StartTicktacktoe(ITable table, IGame game, IInput input, Consumer<String> output) {
        this.table = table;
        this.game = game;
        this.input = input;
        this.output = output;
    }

    public void start(int value) {
        int p1Win = 0, p2Win = 0;
        SimplePlayer player1 = null;
        SimplePlayer player2 = null;
        //Условие итоговой победы: Количество побед = 5;
        while (p1Win != value && p2Win != value) {
            String order = input.ask("введите y, если хотите играть за Х, n - за O и с - для PC vs PC",
                    p -> p.matches("[ync]"));
            int player1Mark = "y".equals(order) ? 2 : 1;
            int player2Mark = player1Mark == 2 ? 1 : 2;
            player1 = "c".equals(order) ?
                    new PCPlayer(player1Mark, table, new DummyLogic(), "Победил компьютер")
                    : new HumanPlayer(player1Mark, table, input, "Ура, вы победили! Поздравляю!");
            player2 = new PCPlayer(player2Mark, table, new DummyLogic(), "Победил компьютер");
            boolean playerFirst = "y".equals(input.ask("введите y, чтобы player1 ходил первым", p -> p.matches("[yn]")));
            int win = game.singleGame(player1, player2, playerFirst);
            switch (win) {
                case 1:
                    if (player1Mark == 1) {
                        p1Win++;
                    } else {
                        p2Win++;
                    }
                    break;
                case 2:
                    if (player1Mark == 2) {
                        p1Win++;
                    } else {
                        p2Win++;
                    }
                    break;
                default:
                    break;
            }
            output.accept(String.format("Счет в игре player1 %d : player2 %d", p1Win, p2Win));
        }
        if (p1Win > p2Win) {
            output.accept(player1.getCongratulationsOF());
        } else if (p1Win < p2Win) {
            output.accept(player2.getCongratulationsOF());
        }
    }


    public static void main(String[] args) {
        Consumer<String> output = System.out::println;
        IInput input = new PredicateInput();
        ITable table = new BigTable(output);
        ILogic logic = new DummyLogic();
        IGame game = new Game(input, table, logic, output);
        StartTicktacktoe startTicktacktoe = new StartTicktacktoe(table, game, input, output);
        startTicktacktoe.start(2);
    }
}
