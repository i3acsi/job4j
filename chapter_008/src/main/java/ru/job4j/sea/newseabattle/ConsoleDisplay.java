package ru.job4j.sea.newseabattle;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleDisplay implements IDisplayStrategy {
    private String ln = System.lineSeparator();
    private Map<String, Integer> map;
    private Map<Integer, String> reverse;
    private Consumer<String> out;
    private IInput iInput;
    private Map<Integer, String> stateMap;

    public ConsoleDisplay(Map<String, Integer> map, Map<Integer, String> stateMap, Consumer<String> out, IInput iInput) {
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
        String question = "Выберете режим игры:\r\n\t1 - Human vs Human (на одной машине)"
                + "\r\n\t2 - Human vs PC(на одной машине)\r\n\t3 - Human vs Human (по сети)";
        String regEx = "([1-3])";
        return Integer.valueOf(iInput.ask(question, answr -> answr.matches(regEx)));
    }

    @Override
    public String askIp() {
        String question = "Введите ip адрес для соединения или ничего не ничего не вводите для старта сервера";
        String empty = "";
        String regEx = String.format("(^(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$)|(%s)", empty);
        return iInput.ask(question, answr -> answr.matches(regEx));
    }

    /**
     * Метод для получения координат кормы и носа корабля. Формат ввода А.1-А.2;
     *
     * @return int[][] - {x1, y1}{x2, y2};
     */
    @Override
    public int[][] askCoordinates() {
        String question = "Введите координаты носа и кормы корабля (Например А.1-А.4)";
        String regEx = "([А-Я]|[A-Z])+\\.\\d+-([А-Я]|[A-Z])+\\.\\d+";
        int[][] result = new int[2][2];
        String[] data = iInput.ask(question, answr -> answr.matches(regEx)).split("-");
        result[0] = getCoordinate(data[0]);
        result[1] = getCoordinate(data[1]);
        return result;
    }

    /**
     * Метод для получения координаты точки.
     *
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
        String[] data = c.split("\\.");
        if (data.length != 2 || !data[0].matches("\\w+|([А-Я]+)") || !data[1].matches("\\d+")) return null;
        int y = Integer.valueOf(data[1]);
        if (0 >= y || y > tableSize) return null;
        result[1] = --y;
        result[0] = map.getOrDefault(data[0], -1);
        return result[0] == -1 ? null : result;
    }

    @Override
    public void show(SimplePlayer player, boolean hide) {
        try {
            out.accept(getString(player.myTable.getCells(), hide));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display(SimplePlayer me, SimplePlayer other) {
        StringBuilder result = new StringBuilder("#################################################################")
                .append(me.name)
                .append("####################")
                .append(ln);
        String[] my = new String[0];
        try {
            my = getString(me.myTable.getCells(), false).split(ln);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] enemy = new String[0];
        try {
            enemy = getString(other.myTable.getCells(), true).split(ln);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < my.length; i++) {
            result.append(my[i]).append("\t\t\t").append(enemy[i]).append(ln);
        }
        this.out.accept(result.toString());
    }

    private String getString(Cell[][] cells, boolean hide) throws UnsupportedEncodingException {
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
                    String toAppend = (stateMap.getOrDefault(state, "X")); // parse
                    result.append(toAppend);
                } else {
                    String toAppend = (stateMap.getOrDefault(0, "X")); // parse
                    result.append(toAppend);
                }
            }
        }
        //String rslt = new String(result.toString().getBytes("Cp1251"), "Cp1251");
        return result.toString();
    }

    private void reverse() {
        reverse = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            reverse.put(entry.getValue(), entry.getKey());
        }
    }

//    static String parse(String s) {
//
//        StringBuilder sb = new StringBuilder();
//
//        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
//        Matcher m = p.matcher(s);
//
//        int lastIndex = 0;
//        while (m.find()) {
//
//            sb.append(s.substring(lastIndex, m.start()));
//            lastIndex = m.end();
//
//            sb.append((char) Integer.parseInt(m.group(1), 16));
//        }
//
//        if (lastIndex < s.length()) sb.append(s.substring(lastIndex));
//
//        return sb.toString();
//    }
}