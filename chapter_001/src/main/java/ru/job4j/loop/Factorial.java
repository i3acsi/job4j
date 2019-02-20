package ru.job4j.loop;

/**
* Factorial.
* @author Vasiliy Gasevskiy
* @since 20.02.2019
* @version 1
*/

public class Factorial {
/**
* Method calc.
* @param value число.
* @return factorial.
*/
	public int calc(int value) {
		int result = 1;
		for (int i = value; i >= 0; i--) {
		result = (i > 0) ? result * i : result * 1;
		}
		return result;
    }
}