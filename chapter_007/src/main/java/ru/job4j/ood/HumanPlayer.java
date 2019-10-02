package ru.job4j.ood;

public class HumanPlayer extends SimplePlayer {
    private IInput input;

    protected HumanPlayer(int mark, ITable table, IInput input, String congratulations) {
        super(mark, table, congratulations);
        this.input = input;
    }

    @Override
    public void turn() {
        int[] c = this.getCoordinates();
        table.add(c[0], c[1], this.mark);
    }

    private int[] getCoordinates() {
        int size = table.getTable().length;
        int[] result = new int[2];
        String c = input.ask("Введите координаты в формате '2,2'", p -> {
            String[] digitS = p.split(",");
            if (digitS.length != 2) {
                return false;
            }
            for (String s : digitS) {
                if (!s.matches("\\d+")) {
                    return false;
                } else {
                    if (Integer.parseInt(s) > size) {
                        return false;
                    }
                }
            }
            return true;

        });

        int i = 0;
        for (String s : c.split(",")) {
            result[i++] = Integer.parseInt(s) - 1;
        }
        return result;
    }
}
