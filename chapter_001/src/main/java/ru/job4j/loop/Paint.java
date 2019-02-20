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
			for (int i = -(h - 1); i != h; i++) {
				if (j >= Math.abs(i)) {
					result.append("^");
				} else {
					result.append(" ");
				}
			}
			result.append(ln);
		}
		return result.toString();
    }
}