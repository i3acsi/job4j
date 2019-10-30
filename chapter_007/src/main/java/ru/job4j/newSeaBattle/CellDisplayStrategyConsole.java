package ru.job4j.newSeaBattle;

import java.util.Map;

public class CellDisplayStrategyConsole implements ICellDisplayStrategy {
    private Map<Integer, Character> map;

    public CellDisplayStrategyConsole(Map<Integer, Character> map) {
        this.map = map;
    }

    @Override
    public Character view(int state) {
        return map.get(state);
    }
}
