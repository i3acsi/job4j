package ru.job4j.condition;

/**
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 19.02.2019
 */
public class Point {
    private int x;
	private int y;

	public  Point(int x, int y) {
	    this.x = x;
		this.y = y;
	}

	public double distanceTo(Point that) {
		return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
	}
}