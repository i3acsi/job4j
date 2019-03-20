package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

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

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int dY = (source.x - dest.x)<0? -1 : 1;
        int dX = (source.y - dest.y)<0? -1 : 1;
        int length = Math.abs(source.x - dest.x);
        Cell[] steps;
        //if (isDiaganal(source, dest)) {
            steps = new Cell[length];
            for (int i = 0; i < length; i++) {
               // i = i + deltaY + (deltaX * 8)
                steps[i] = Cell.values()[(8*source.y - (dY * 8 *i)+(source.x - dX * i))];
            }
        //} else steps = new Cell[]{source};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
