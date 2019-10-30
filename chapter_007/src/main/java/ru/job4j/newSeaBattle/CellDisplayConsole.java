package ru.job4j.newSeaBattle;

import java.util.Map;

public class CellDisplayConsole {
    private Map<Integer, Character> map;

    public CellDisplayConsole(Map<Integer, Character> map) {
        this.map = map;
    }

    public Character view(int state) {
        return map.get(state);
    }
}
