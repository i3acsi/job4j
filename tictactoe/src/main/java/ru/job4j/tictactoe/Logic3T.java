package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    private boolean isWinner(Function<Figure3T, Boolean> func) {
        boolean res = false;
        for (int i = 0; i < 3; i++) {
            res = this.fillBy(func::apply, 0, i, 1, 0)
                    || this.fillBy(func::apply, i, 0, 0, 1);
            if (res) {
                break;
            }
        }
        if (!res) {
            res = this.fillBy(func::apply, 0, 0, 1, 1)
                    || this.fillBy(func::apply, this.table.length - 1, 0, -1, 1);
        }
        return res;
    }

    public boolean hasGap() {
//        mark:
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table.length; j++) {
//                if (!this.table[i][j].hasAnyMark()) {
//                    result = true;
//                    break mark;
//                }
//            }
//        }
        return Arrays.stream(this.table).
                flatMap(Arrays::stream).
                flatMap(x -> List.of(x).stream()).
                anyMatch(x -> !x.hasAnyMark());
    }
}