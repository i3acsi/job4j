package ru.job4j.newSeaBattle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ConsoleDisplay implements IDisplayStrategy {
    private String ln = System.lineSeparator();
    private Map<String, Integer> map;
    private Map<Integer, String> reverse;
    private Consumer<String> out;
    private IInput iInput;
    private Map<Integer, Character> stateMap;

    public ConsoleDisplay(Map<String, Integer> map, Map<Integer, Character> stateMap, Consumer<String> out, IInput iInput) {
        this.map = map;
        this.out = out;
        this.iInput = iInput;
        this.stateMap = stateMap;
        reverse();
    }

    @Override
    public void accept(int[] coordinate) {
        StringBuilder result = new StringBuilder("\t\t\t\t");
        result.append(reverse.get(coordinate[0]));
        result.append(" ").append(coordinate[1] + 1);
        result.append(ln);
        out.accept(result.toString());
    }

    @Override
    public void accept(String msg) {
        out.accept(msg);
    }

    @Override
    public void congratulations(SimplePlayer player) {
        out.accept(String.format("Congratulations, %s", player.getName()));
    }


    @Override
    public int askMode() {
        String question = "Выберете режим игры:\r\n\t1 - Human vs Human (на одной машине)\r\n\t2 - Human vs PC(на одной машине)";
        String regEx = "([1-2])";
        return Integer.valueOf(iInput.ask(question, answr -> answr.matches(regEx)));
    }

    /**
     * Метод для получения координат кормы и носа корабля. Формат ввода А.1-А.2;
     * @return int[][] - {x1, y1}{x2, y2};
     */
    @Override
    public int[][] askCoordinates() {
        String question = "Введите координаты носа и кормы корабля (Например А.1-А.4)";
        String regEx = "([А-Я]|[A-Z])+\\.\\d+-([А-Я]|[A-Z])+\\.\\d+";
        int[][] result = new int[2][2];
        String[] data =  iInput.ask(question, answr -> answr.matches(regEx)).split("-");
        result[0] = getCoordinate(data[0]);
        result[1] = getCoordinate(data[1]);
        return result;
    }

    /**
     * Метод для получения координаты точки.
     * @return
     */
    @Override
    public int[] askCoordinate() {
        String question = "Введите координаты выстрела (Например А.4)";
        String regEx = "([А-Я]|[A-Z])+\\.\\d+";
        String coordinate = iInput.ask(question, answr -> answr.matches(regEx));
        return getCoordinate(coordinate);
    }

    /*
    Преобразует строку в координаты.
     */
    private int[] getCoordinate(String c) {
        int[] result = new int[2];
        int tableSize = map.size();
        String[] data = c.split("\\.");//?
        if (data.length != 2 || !data[0].matches("\\w+|([А-Я]+)") || !data[1].matches("\\d+")) return null;
        int y = Integer.valueOf(data[1]);
        if (0 >= y || y > tableSize) return null;
        result[1] = --y;
        result[0] = map.getOrDefault(data[0], -1);
        return result[0] == -1 ? null : result;
    }

    @Override
    public void show(SimplePlayer player, boolean hide) {
        out.accept(getString(player.myTable.getCells(), hide));
    }

    @Override
    public void display(SimplePlayer me, SimplePlayer other) {
        StringBuilder result = new StringBuilder("#################################################################")
                .append(me.name)
                .append("####################")
                .append(ln);
        String[] my = getString(me.myTable.getCells(), false).split(ln);
        String[] enemy = getString(other.myTable.getCells(), true).split(ln);
        for (int i = 0; i < my.length; i++) {
            result.append(my[i]).append("\t\t\t").append(enemy[i]).append(ln);
        }
        this.out.accept(result.toString());
    }

    private String getString(Cell[][] cells, boolean hide) {
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
                    result.append(stateMap.getOrDefault(state, 'X'));//cellDisplay.view(state));
                } else result.append(stateMap.getOrDefault(0, 'X'));
            }
        }
        return result.toString();
    }

    private void reverse() {
        reverse = new HashMap<>();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            reverse.put(entry.getValue(), entry.getKey());
        }
    }
}