package ru.job4j.seaBattle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleCellTest {

    /**
     * Empty Cell Displayed OK.
     */
    @Test
    public void whenEmptyCellWasCreatedThanItCanBeDisplayedInConsole() {
        SimpleCell cell = new SimpleCell(1, 1, 1);
        String result = cell.toString();
        assertThat(result, is("_"));
    }

    /**
     * Empty Cell State Changed And Displayed Right.
     */
    @Test
    public void whenEmptyCellWasCreatedAndItStateChangedThanItCanBeDisplayedInConsole() {
        SimpleCell cell = new SimpleCell(1, 1, 1);
        checkState(cell, 2, "█");
        checkState(cell, 3, "▓");
        checkState(cell, 4, "░");
        checkState(cell, 5, "●");
    }

    private void checkState(SimpleCell cell, int value, String state) {
        cell.setState(value);
        String result = cell.toString();
        assertThat(result, is(state));
    }

    @Test
    public void whenEmptyCellWasCreatedAndItStateChangedWrongThanCachedException() {
        checkException(0);
        checkException(-1);
        checkException(6);
    }

    private void checkException(int wrongState) {
        SimpleCell cell = new SimpleCell(1, 1, 1);
        boolean flag = false;
        try {
            cell.setState(wrongState);
        } catch (RuntimeException e) {
            flag = true;
            assertThat(e.getMessage(), is("Wrong State Parameter"));
        }
        assertTrue(flag);
    }

    /**
     * Player use coordinates like А1 Б6 ... etc And app use coordinates  like 1 1, 2 6.
     * So it's a converter test with the right parameters.
     */
    @Test
    public void whenPutUserViewCoordinatesThanGetXYCoordinates() {
        assertThat(SimpleCell.coordinatesConvert("А1"), is(new int[]{1, 1}));
        assertThat(SimpleCell.coordinatesConvert("В6"), is(new int[]{3, 6}));
        assertThat(SimpleCell.coordinatesConvert("Д3"), is(new int[]{5, 3}));
        assertThat(SimpleCell.coordinatesConvert("Е9"), is(new int[]{6, 9}));
        assertThat(SimpleCell.coordinatesConvert("К10"), is(new int[]{10, 10}));
    }

    /**
     * Player use coordinates like А1 Б6 ... etc And app use coordinates  like 1 1, 2 6.
     * So it's a converter test with the right parameters.
     */
    @Test
    public void whenPutUserViewCoordinatesThanGetException() {
        checkUserCoordinates("A1");
        checkUserCoordinates("B2");
        checkUserCoordinates("K10");
        checkUserCoordinates("A11");
        checkUserCoordinates("Б0");
        checkUserCoordinates("Б 9+-");

    }

    private void checkUserCoordinates(String wrongXY) {
        boolean flag = false;
        try {
            SimpleCell.coordinatesConvert(wrongXY);
        } catch (RuntimeException e) {
            flag = true;
            assertThat(e.getMessage(), is("Wrong Coordinates Were Input"));
        }
        assertTrue(flag);
    }

}