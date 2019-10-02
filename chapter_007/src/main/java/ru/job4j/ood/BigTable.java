package ru.job4j.ood;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class BigTable extends Table {
    public Iterator<String> iterator = new Iterator<String>() {
        int len = table.length;
        int size = len * len;
        public int counter = 0;

        @Override
        public boolean hasNext() {
            return counter <= size;
        }

        @Override
        public String next() {
            int i, j;
            i = counter / len;
            j = counter % len;
            counter++;
            if (iterator.hasNext()) {


                switch (table[i][j]) {
                    case 1:
                        return "O";
                    case 2:
                        return "X";
                    default:
                        return " ";
                }
            } else {
                return " ";
            }

        }

        @Override
        public void remove() {
            counter = 0;
        }
    };

    public BigTable(int size, Consumer<String> output) {
        super(size, output);
    }

    public BigTable(Consumer<String> output) {
        super(output);
    }

    @Override
    public void show() {
        int size = table.length;
        StringBuilder sb = new StringBuilder();

        sb.append("╔═══");
        for (int i = 0; i < size - 1; i++) {
            sb.append("╦═══");
        }
        sb.append("╗\n");
        sb.append("║ ");
        sb.append(iterator.next());
        sb.append(" ");
        for (int i = 0; i < size - 1; i++) {
            sb.append("║ ");
            sb.append(iterator.next());
            sb.append(" ");
        }
        sb.append("║\n");

        for (int j = 0; j < size - 1; j++) {
            sb.append("╠═══");
            for (int i = 0; i < size - 1; i++) {
                sb.append("╬═══");
            }
            sb.append("╣\n");
            sb.append("║ ");
            sb.append(iterator.next());
            sb.append(" ");
            for (int i = 0; i < size - 1; i++) {
                sb.append("║ ");
                sb.append(iterator.next());
                sb.append(" ");
            }
            sb.append("║\n");
        }
        sb.append("╚═══");
        for (int i = 0; i < size - 1; i++) {
            sb.append("╩═══");
        }
        sb.append("╝\n");
        iterator.remove();
        output.accept(sb.toString());
    }
}
