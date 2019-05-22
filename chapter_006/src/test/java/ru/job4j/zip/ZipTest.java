package ru.job4j.zip;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ZipTest {
    private static final String SP = File.separator;
    private static final String DIR = "." + SP;

    @Test
    public void argsTestOk() {
        Args args = new Args(new String[]{"-d", "../", "-e", "*.", "-o", "pack.zip"});
        assert (args.directory().equals("../"));
        assert (args.exclude().equals("*."));
        assert (args.output().equals("pack.zip"));
    }

    @Test
    public void argTestFail() {
        Args args = new Args(new String[]{"-d", "0", "-e", "+.", "-o", "pack.zi"});
        assert (args.directory() == null);
        assert (args.exclude() == null);
        assert (args.output() == null);
    }

    @Test
    public void createZipFileTest() {

        File arch = new File(DIR + "packnew.zip");
        if (arch.exists()) {
            arch.delete();
        }

        Zip zip = new Zip();
        zip.pack("." + DIR + "chapter_003", "*.java,*.,*.class", "." + DIR + "packnew.zip");
        File result = new File("." + DIR + "packnew.zip");
        assert (result.exists());
    }

}