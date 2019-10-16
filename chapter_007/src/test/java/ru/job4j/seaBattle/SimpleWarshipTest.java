package ru.job4j.seaBattle;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleWarshipTest {
    @Test
    public void whenConstructorGetStringWithCoordinatesThanItInitTheListOfCells() {
        SimpleWarship warship = new SimpleWarship("А1-А4", 4);
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(1, 1, 2),
                new SimpleCell(1, 2, 2),
                new SimpleCell(1, 3, 2),
                new SimpleCell(1, 4, 2)
        );
        assertThat(expected, is(result));
    }

    @Test
    public void whenCreateSingleDeckShipThanGetIt() {
        SimpleWarship warship = new SimpleWarship("А1-А1", 1);
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(1, 1, 2)
        );
        assertThat(expected, is(result));
    }

    @Test
    public void whenConstructorGetStringWithCoordinatesThanItInitTheListOfCells2() {
        SimpleWarship warship = new SimpleWarship("А1-Г1", 4);
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(1, 1, 2),
                new SimpleCell(2, 1, 2),
                new SimpleCell(3, 1, 2),
                new SimpleCell(4, 1, 2)
        );
        assertThat(expected, is(result));
    }

    @Test
    public void whenCreatedTwoShipsWithMirrorCoordinatesAndTheyAreEquivalent() {
        SimpleWarship warship1 = new SimpleWarship("К10-Ж10", 4);
        SimpleWarship warship2 = new SimpleWarship("Ж10-К10", 4);
        assertThat(warship1, is(warship2));
        List<SimpleCell> result1 = warship1.getCells();
        List<SimpleCell> result2 = warship2.getCells();
        assertThat(result1, is(result2));
    }

    @Test
    public void whenTryToInitShipWithWrongParameterSizeThanGetException() {
        boolean result = false;
        try {
            new SimpleWarship("Г5-Г2", 3);
        } catch (InitException e) {
            assertThat(e.getMessage(), is("Ship initialization fail: wrong size."));
            result = true;
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenTryToInitShipWithWrongCoordinatesThanGetException() {
        boolean result = false;
        try {
            new SimpleWarship("Б5-А2", 4);
        } catch (InitException e) {
            assertThat(e.getMessage(), is("Ship initialization fail: ship cells should be on one line."));
            result = true;
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenTryToInitShipWithMistakeInCoordinatesThanGetException() {
        boolean result = false;
        try {
            new SimpleWarship("Б5,А2", 4);
        } catch (InitException e) {
            assertThat(e.getMessage(), is("Ship initialization fail: wrong coordinates were input."));
            result = true;
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenTryToInitShipWithMistakeInCoordinates2ThanGetException() {
        boolean result = false;
        try {
            new SimpleWarship("Б11-А2", 4);
        } catch (InitException e) {
            assertThat(e.getMessage(), is("Cell init fail: wrong coordinates were input."));
            result = true;
        }
        assertThat(result, is(true));
    }
}