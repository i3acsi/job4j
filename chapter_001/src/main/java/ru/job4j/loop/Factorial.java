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
		if (value == 0) {
			return 1;
		}
		return value * calc(value - 1);
    }
}