package ru.job4j.ood;

public interface IGame {
    /**
     * Результатом одной игры является либо победа одного из игроков, либо ничья
     *
     * @param player1      - кем ходит игрок? 1 - для O, 2 - для X
     * @param playerFirst - если true, то первым ходит игрок
     * @return число, соответствующее победителю -1 - ничья, 1 или 2 - O или X соответственно.
     */
    int singleGame(SimplePlayer player1, SimplePlayer player2, boolean playerFirst);
}
