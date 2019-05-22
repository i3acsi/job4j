package ru.job4j.zip;

import ru.job4j.io.Search;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static final String SP = File.separator;
    private static final String DIR = "." + SP;

    public void pack(String source, String exclude, String target) {
        File file = new File(source);
        int length = 0;
        try {
            length = file.getCanonicalPath().length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> exts = extensions(exclude);

        Search search = new Search();
        List<File> sources = search.files(source, exts, false);

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File f : sources) {
                zip.putNextEntry(new ZipEntry(f.getCanonicalPath().substring(length)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(target))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> extensions(String exclude) {
        List<String> result = Arrays.asList(exclude.split(","));
        return result.stream().map(x -> x.substring(2)).collect(Collectors.toList());
    }
}
