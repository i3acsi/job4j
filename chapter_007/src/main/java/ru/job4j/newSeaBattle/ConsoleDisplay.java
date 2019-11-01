package ru.job4j.newSeaBattle;

import java.util.Map;
import java.util.function.Consumer;

public class ConsoleDisplay implements IDisplayStrategy {
    private String ln = System.lineSeparator();
    private Map<String, Integer> map;
    private Consumer<String> out;
    private IInput iInput;
    private Map<Integer, Character> stateMap;

    public ConsoleDisplay(Map<String, Integer> map, Map<Integer, Character> stateMap, Consumer<String> out, IInput iInput) {
        this.map = map;
        this.out = out;
        this.iInput = iInput;
        this.stateMap = stateMap;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    @Override
    public String askCoordinates() {
        String question = "Введите координаты носа и кормы корабля (Например А.1-А.4)";
        String regEx = "([А-Я]|[A-Z])+\\.\\d+-([А-Я]|[A-Z])+\\.\\d+";
        return getCoordinate(question, regEx);
    }

    @Override
    public String askCoordinate() {
        String question = "Введите координаты выстрела (Например А4)";
        String regEx = "([А-Я]|[A-Z])+\\.\\d+";
        return getCoordinate(question, regEx);
    }

    @Override
    public void congratulations(String name) {
        this.out.accept(String.format("Congratulations, %s", name));
    }

    @Override
    public int[] getCoordinate(String c) {
        int[] result = new int[2];
        int tableSize = map.size();
        String[] data = c.split("\\.");//?
        if (data.length != 2 || !data[0].matches("\\w+|([А-Я]+)") || !data[1].matches("\\d+")) return null;
        int y = Integer.valueOf(data[1]);
        if (0 >= y && y > tableSize) return null;
        result[1] = --y;
        result[0] = map.getOrDefault(data[0], -1);
        return result[0] == -1 ? null : result;
    }

    private String getCoordinate(String question, String regEx) {
        return iInput.ask(question, answr -> answr.matches(regEx));
    }

    public Consumer<String> getOut() {
        return out;
    }

    @Override
    public void show(Cell[][] cells, boolean hide) {
        out.accept(getString(cells, hide));
    }

    @Override
    public void display(Table myTable, Table otherTable, String name) {
        StringBuilder result = new StringBuilder("#################################################################")
                .append(name)
                .append("####################")
                .append(ln);
        String[] my = getString(myTable.getCells(), false).split(ln);
        String[] other = getString(otherTable.getCells(), true).split(ln);
        for (int i = 0; i < my.length; i++) {
            result.append(my[i]).append("\t\t\t").append(other[i]).append(ln);
        }
        this.out.accept(result.toString());
    }

    @Override
    public String getString(Cell[][] cells, boolean hide) {
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
                    if (hide && state == 1) state = 0;
                    result.append(stateMap.getOrDefault(state,'X'));//cellDisplay.view(state));
                } else result.append(stateMap.getOrDefault(0,'X'));
            }
        }
        return result.toString();
    }


}

