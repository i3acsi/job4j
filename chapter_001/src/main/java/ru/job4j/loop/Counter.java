package ru.job4j.loop;

/**
* Counter..
* @author Vasiliy Gasevskiy
* @since 20.02.2019
* @version 1
*/

public class Counter {
/**
* Method add.
* @param start Start int.
* @param finish Finish int.
* @return Summ int.
*/
	public int add(int start, int finish) {
		int result = 0;
		for (int i = start; i <= finish; i++) {
		result = (i % 2 == 0) ? result + i : result;
		}
		return result;
    }
}