package ru.job4j.calculate;

/**
 * Output to the console text "Hello World"
 *
 * @author Vasiliy Gasevskiy
 * @version 1
 * @since 15.02.2019
 */

public class Calculate {
    /**
     * Method main
     *
     * @param args - args.
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    /**
     * Method echo.
     *
     * @param name Your name.
     * @return Echo plus your name.
     */
    public String echo(String name) {
        return "Echo, echo, echo : " + name;
    }
}