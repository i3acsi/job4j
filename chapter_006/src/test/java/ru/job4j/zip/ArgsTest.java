package ru.job4j.zip;

import com.google.common.base.Joiner;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArgsTest {
    @Test
    public void whenGetCorrectInputThenReturnArgs() {
        Args args = new Args(new String[]{"-d", "./", "-e", "*.java", "-o", "project.zip"});
        assertThat(args.exclude(), is("*.java"));
        assertThat(args.directory(), is("./"));
        assertThat(args.output(), is("project.zip"));
        assert (!args.isFail());
    }

    @Test
    public void whenWrongInputThanFail() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String[] input = {"-d", "./", "-e", "*java", "-o", "project.zip"};
        Args args = new Args(input);
        assertThat(out.toString(),
                Is.is(
                        Joiner.on(System.lineSeparator()).join(
                                "wrong file extensions",
                                "usage:  -d c:\\project\\job4j\\ -e *.java -o project.zip",
                                " -d <arg>   input directory to archive",
                                " -e <arg>   input file extensions to exclude",
                                " -o <arg>   output file",
                                ""
                        )));
        System.setOut(stdout);
        assert (args.isFail());
    }
}