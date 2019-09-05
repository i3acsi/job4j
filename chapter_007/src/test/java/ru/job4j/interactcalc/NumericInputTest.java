package ru.job4j.interactcalc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */


public class NumericInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void validInput() {
        NumericInput input = new NumericInput(new StubInput(new String[]{"4.6"}));
        assertThat(input.getDouble("test"), is(4.6));
        assertThat(this.mem.toString(), is("")
        );
    }

    @Test
    public void invalidInput() {
        NumericInput input = new NumericInput(new StubInput(new String[]{"test", "0"}));
        assertThat(input.getDouble("test"), is(0.0));
        assertThat(this.mem.toString(), is(""));
    }

    @Test
    public void useOldResultTest() {
        NumericInput input = new NumericInput(new StubInput(new String[]{""}));
        input.setCurrent(2.2);
        assertThat(input.getDouble("test"), is(2.2));
    }

}
