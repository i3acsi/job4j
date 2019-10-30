package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public class ConsoleDisplay implements IDisplayStrategy {
    private String ln = System.lineSeparator();
    private Map<Character, Integer> map;
    private ICellDisplayStrategy cellDisplay;
    private Consumer<String> out;

    public ConsoleDisplay(Map<Character, Integer> map, ICellDisplayStrategy cellDisplay, Consumer<String> out) {
        this.map = map;
        this.cellDisplay = cellDisplay;
        this.out = out;
    }

    @Override
    public void show(Cell[][] cells, boolean hide) {
        int size = cells.length;
        StringBuilder result = new StringBuilder("\t");
        map.keySet().forEach(ch -> result.append("\t").append(ch));
        for (int y = 0; y < size; y++) {
            result.append(ln).append("\t").append(y + 1);
            for (int x = 0; x < size; x++) {
                result.append("\t");
                Cell cell = cells[x][y];
                if (cell != null) {
                    int state = cell.getState();
                    if (hide && state == 1) state =0;
                    result.append(cellDisplay.view(state));
                } else result.append(cellDisplay.view(0));
            }
        }
        out.accept(result.toString());
    }
}

