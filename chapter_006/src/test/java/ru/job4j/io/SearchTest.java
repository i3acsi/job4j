package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchTest {
    private static final String SEPARATOR = File.separator;
    private static final String ROOT = System.getProperty("java.io.tmpdir") + SEPARATOR + "ROOT";
    private static final List<String> EXTS = List.of("info", "txt");
    private String[] files;

    @Before
    public void init() {
        File file = new File(ROOT);
        assert !file.exists() || (MyFiles.deleteDirectory(file));
        files = new String[]{
                "unit_d_file1.info",
                "unit_a_file1.info",
                "unit_a_file2.txt",
                "unit_e_file1.txt",
                "root_file1.txt",
                "root_file2.txt",
                "root_file3.info",
                "unit_g_file1.info",
                "unit_g_file2.txt",
                "unit_f_file1.info",
                "unit_f_file2.txt",
                "unit_h_file1.info",
                "unit_h_file2.txt",
                "unit_h_file3.info",
                "unit_h_file4.txt",
                "unit_c_file1.info",
                "unit_c_file2.txt"
        };

        MyFiles.makeDir(ROOT);
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_A");
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_A" + SEPARATOR + "UNIT_D");
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_A" + SEPARATOR + "UNIT_E");

        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_B");
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_B" + SEPARATOR + "UNIT_F");
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_B" + SEPARATOR + "UNIT_F" + SEPARATOR + "UNIT_G");

        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_C");
        MyFiles.makeDir(ROOT + SEPARATOR + "UNIT_C" + SEPARATOR + "UNIT_H");

        MyFiles.addFile(ROOT + SEPARATOR + "UNIT_A" + SEPARATOR + "UNIT_D" + SEPARATOR + files[0]);
        MyFiles.addFile(ROOT + SEPARATOR + "UNIT_A" + SEPARATOR + "UNIT_E" + SEPARATOR + files[3]);

        for (int i = 0; i < 2; i++) {
            MyFiles.addFile(ROOT + SEPARATOR + "UNIT_A" + SEPARATOR + files[1 + i]);
            MyFiles.addFile(ROOT + SEPARATOR + "UNIT_B" + SEPARATOR + "UNIT_F" + SEPARATOR + "UNIT_G" + SEPARATOR + files[7 + i]);
            MyFiles.addFile(ROOT + SEPARATOR + "UNIT_B" + SEPARATOR + "UNIT_F" + SEPARATOR + files[9 + i]);
            MyFiles.addFile(ROOT + SEPARATOR + "UNIT_C" + SEPARATOR + files[15 + i]);
        }

        for (int i = 0; i < 3; i++) {
            MyFiles.addFile(ROOT + SEPARATOR + files[4 + i]);
        }

        for (int i = 0; i < 4; i++) {
            MyFiles.addFile(ROOT + SEPARATOR + "UNIT_C" + SEPARATOR + "UNIT_H" + SEPARATOR + files[11 + i]);
        }
    }

    @Test
    public void test() {
        Search search = new Search();
        List<String> result = new ArrayList<>();
        List<File> list = search.files(ROOT, EXTS, true);
        for (File f : list) {
            result.add(f.getName());
        }
        Iterator<String> stringIterator = result.iterator();
        Iterator<File> i = list.iterator();
        while (i.hasNext()) {
            System.out.println();
            assertThat(i.next().getName(), is(stringIterator.next()));
        }
    }
}