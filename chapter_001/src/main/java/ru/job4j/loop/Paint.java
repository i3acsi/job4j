package ru.job4j.loop;

/**
* Paint  piramid.
* @author Vasiliy Gasevskiy
* @since 20.02.2019
* @version 1
*/

public class Paint {
/**
* Method piramid.
* @param h высота пирамиды.
* @return piramid.
*/
	 public String piramid(int h) {
		String ln = System.lineSeparator();
		StringBuilder result = new StringBuilder();
		for (int j = 0; j != h; j++) {
			for (int i = 0; i != (h * 2) - 1; i++) {
				if (j >= Math.abs(i - (h - 1))) {
					result.append("^");
				} else {
					result.append(" ");
				}
			}
			result.append(ln);
		}
		return result.toString();
    }
	
	
/**
* Method rightTrl.
* @param h высота пирамиды.
* @return rightTrl.
*/
	public String rightTrl(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина будет равна высоте. 
        int width = height; 
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != width; column++) {
                // если строка равна ячейке, то рисуем галку. 
                // в данном случае строка определяет, сколько галок будет в строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }
	
	public String leftTrl(int height) {
    StringBuilder screen = new StringBuilder();
    int width = height;
    for (int row = 0; row != height; row++) {
        for (int column = 0; column != width; column++) {
            if (row >= width - column - 1) {
                screen.append("^");
            } else {
                screen.append(" ");
            }
        }
        screen.append(System.lineSeparator());
    }
    return screen.toString();
}
}