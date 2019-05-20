package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.List;

public class MyFilesTest {
    private static final String ROOT = System.getProperty("java.io.tmpdir") + File.separator + "ROOT";
    private static final String FILENAME = System.getProperty("java.io.tmpdir") + File.separator + "unavailable.csv";

    @Test
    public void checkFileExtensionAndAddFileTest() {
        assert (MyFiles.addFile(FILENAME));
        File file = new File(FILENAME);
        List<String> ext1 = List.of("csv");
        List<String> ext2 = List.of("txt");
        assert (MyFiles.checkFileExtension(file, ext1));
        assert (!MyFiles.checkFileExtension(file, ext2));
        assert (file.delete());
    }

    @Test
    public void getExt() {
        assert (MyFiles.addFile(FILENAME));
        File file = new File(FILENAME);
        assert !file.exists() || (MyFiles.getFileExtension(file).equals("csv"));
        assert (file.delete());
    }

    @Test
    public void makeDirAndThenDelDirTest() {
        File dir = new File(ROOT);
        if (dir.exists()) {
            dir.delete();
        } else {
            assert (MyFiles.makeDir(ROOT));
            assert (dir.isDirectory());
            assert (MyFiles.deleteDirectory(dir));
        }
    }
}