package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static ru.job4j.chess.firuges.Cell.*;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    private boolean isDiaganal(Cell source, Cell dest) {
        return (Math.abs(source.y - dest.y) == Math.abs(source.x - dest.x));
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     *
     * @param source - исходная ячейка
     * @param dest - конечная ячейка
     * @return way - массив ячеек, являющихся путем фигуры по доске
     */
    @Override
    public Cell[] way(Cell source, Cell dest){
        int dX = (source.x - dest.x) < 0 ? -1 : 1;
        int dY = (source.y - dest.y) < 0 ? -1 : 1;
        int length = Math.abs(source.x - dest.x) + 1;
        Cell[] steps;
        if (isDiaganal(source, dest)) {
            steps = new Cell[length];
            for (int i = 0; i < length; i++) {
                steps[i] = Cell.values()[(source.x - i * dX) * 8 + (source.y - i * dY)];

            }
        } else {
            throw new ImpossibleMoveException("Wrong way");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
