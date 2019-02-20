package ru.job4j.loop;

/**
 * Board.
 *
 * @author Vasiliy Gasevskiy
 * @version 1
 * @since 20.02.2019
 */

public class Board {
    /**
     * Method paint.
     *
     * @param width ширина.
     * @param height высота.
	 * @return chessmateBoard.
     */
    public String paint(int width, int height) {
		String ln = System.lineSeparator();
        StringBuilder result = new StringBuilder();
        boolean isX = true;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isX) {
                    result.append("X");
                    isX = !isX;
                } else {
                    result.append(" ");
                    isX = !isX;
                }
            }
            result.append(ln);
        }
        return result.toString();
    }
}