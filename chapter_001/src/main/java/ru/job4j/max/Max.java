package ru.job4j.max;

/**
 * Maximum.
 *
 * @author Vasiliy Gasevskiy
 * @version 1
 * @since 20.02.2019
 */

public class Max {
	/**
	 * Method max.
	 *
	 * @param first  First int.
	 * @param second Second int.
	 * @return Max int.
	 */
	public int max(int first, int second) {
		return first >= second ? first : second;
	}

	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}