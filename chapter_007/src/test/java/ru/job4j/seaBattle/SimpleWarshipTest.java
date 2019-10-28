package ru.job4j.seaBattle;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleWarshipTest {
    @Test
    public void whenConstructorGetStringWithCoordinatesThanItInitTheListOfCells() {
        SimpleWarship warship = new SimpleWarship("А1-А4");
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(0, 0, 2),
                new SimpleCell(0, 1, 2),
                new SimpleCell(0, 2, 2),
                new SimpleCell(0, 3, 2)
        );
        assertThat (expected.equals(result), is(true));
    }

    @Test
    public void whenCreateSingleDeckShipThanGetIt() {
        SimpleWarship warship = new SimpleWarship("А1-А1");
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(0, 0, 2)
        );
        assertThat (expected.equals(result), is(true));
    }

    @Test
    public void whenConstructorGetStringWithCoordinatesThanItInitTheListOfCells2() {
        SimpleWarship warship = new SimpleWarship("А1-Г1");
        List<SimpleCell> result = warship.getCells();
        List<SimpleCell> expected = List.of(
                new SimpleCell(0, 0, 2),
                new SimpleCell(1, 0, 2),
                new SimpleCell(2, 0, 2),
                new SimpleCell(3, 0, 2)
        );
        assertThat(expected, is(result));
    }

    @Test
    public void whenCreatedTwoShipsWithMirrorCoordinatesAndTheyAreEquivalent() {
        SimpleWarship warship1 = new SimpleWarship("К10-Ж10");
        SimpleWarship warship2 = new SimpleWarship("Ж10-К10");
        assertThat(warship1, is(warship2));
        List<SimpleCell> result1 = warship1.getCells();
        List<SimpleCell> result2 = warship2.getCells();
        assertThat(result1, is(result2));
    }

//    @Test
//    public void whenTryToInitShipWithWrongParameterSizeThanGetException() {
//        boolean result = false;
//        try {
//            new SimpleWarship("Г5-Г2");
//        } catch (InitException e) {
//            assertThat(e.getMessage(), is("Ship initialization fail: wrong size."));
//            result = true;
//        }
//        assertThat(result, is(true));
//    }

    @Test
    public void whenTryToInitShipWithWrongCoordinatesThanGetException() {
        boolean result = false;
        try {
            new SimpleWarship("Б5-А2");
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
            new SimpleWarship("Б5,А2");
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
            new SimpleWarship("Б11-А2");
        } catch (InitException e) {
            assertThat(e.getMessage(), is("Cell init fail: wrong coordinates were input."));
            result = true;
        }
        assertThat(result, is(true));
    }

    @Test
    public void whenAcceptDamageAndHitThanGetTrue() {
        SimpleWarship ship = new SimpleWarship("Б2-Б3");
        assertTrue(ship.acceptDamage(new SimpleCell(1,1, 1)));
    }

    @Test
    public void whenAcceptDamageAndMissThanGetFalse() {
        SimpleWarship ship = new SimpleWarship("Б2-Б3");
        assertFalse(ship.acceptDamage(new SimpleCell(2,2, 1)));
    }

    @Test
    public void whenAcceptDamageAndKillThanGetTrueAndThanWhenShootAgainGetFalse() {
        SimpleWarship ship = new SimpleWarship("Б2-Б2");
        assertTrue(ship.acceptDamage(new SimpleCell(1,1, 1)));
        assertTrue(ship.isKilled());
        assertFalse(ship.acceptDamage(new SimpleCell(1,1, 1)));
    }
}