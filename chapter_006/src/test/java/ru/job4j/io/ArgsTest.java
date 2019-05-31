package ru.job4j.io;

import com.google.common.base.Joiner;
import org.hamcrest.core.Is;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArgsTest {
    @Test
    public void whenInputStringWithMaskThenGetArgs() {
        String[] input = {"-d", "./", "-n", "-m", "*.java", "-o", "result.txt"};
        Args args = new Args(input);

        assertThat(args.getDirectory(), is("./"));
        assertThat(args.getName(), is("*.java"));
        assertThat(args.getOutput(), is("result.txt"));
        assertThat(args.getParameter(), is("-m"));
        assert (!args.isFail());
    }

    @Test
    public void whenInputStringWithFileNameThenGetArgs() {
        String[] input = {"-d", "./", "-n", "-f", "Main.java", "-o", "result.txt"};
        Args args = new Args(input);

        assertThat(args.getDirectory(), is("./"));
        assertThat(args.getName(), is("Main.java"));
        assertThat(args.getOutput(), is("result.txt"));
        assertThat(args.getParameter(), is("-f"));
        assert (!args.isFail());
    }

    @Test
    public void whenInputStringWithExpressionThenGetArgs() {
        String[] input = {"-d", "./", "-n", "-r", "Main", "-o", "result.txt"};
        Args args = new Args(input);

        assertThat(args.getDirectory(), is("./"));
        assertThat(args.getName(), is("Main"));
        assertThat(args.getOutput(), is("result.txt"));
        assertThat(args.getParameter(), is("-r"));
        assert (!args.isFail());
    }

    @Test
    public void whenWrongInputThanFail() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String[] input = {"-d", "./", "-n", "-m", "Main", "-o", "result.txt"};
        Args args = new Args(input);
        assertThat(out.toString(),
                Is.is(
                        Joiner.on(System.lineSeparator()).join(
                                "wrong file parameter name to search",
                                "usage:",
                                " -d c:\\project\\job4j\\ -n -m *.java -o result.txt",
                                " -d c:\\project\\job4j\\ -n -f Main.java -o result.txt",
                                " -d c:\\project\\job4j\\ -n -r Main -o result.txt",
                                "--------------------------------------------------",
                                " -d <arg>   input directory to search",
                                " -n <arg>   input parameter  -m and mask to search file by mask",
                                "            input parameter  -f and file name to search file by name",
                                "            input parameter  -r and regular expression to search file by",
                                "            regular expression",
                                " -o <arg>   output file", ""
                        )));
        System.setOut(stdout);
        assert (args.isFail());
    }


}