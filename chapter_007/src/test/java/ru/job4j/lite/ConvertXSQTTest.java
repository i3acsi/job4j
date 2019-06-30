package ru.job4j.lite;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertXSQTTest {
    @Test
    public void test() {
        ConvertXSQT xsqt = new ConvertXSQT();
        File source = new File("target_134824.xml");
        File dest = new File("XSTL_134824.xml");
        File scheme = new File("scheme.xsl");
        xsqt.convert(source, dest, scheme);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<entries>");
        sb.append("<entry field=\"1\"/>");
        sb.append("<entry field=\"2\"/>");
        sb.append("<entry field=\"3\"/>");
        sb.append("<entry field=\"4\"/>");
        sb.append("<entry field=\"5\"/>");
        sb.append("<entry field=\"6\"/>");
        sb.append("<entry field=\"7\"/>");
        sb.append("<entry field=\"8\"/>");
        sb.append("<entry field=\"9\"/>");
        sb.append("<entry field=\"10\"/>");
        sb.append("<entry field=\"11\"/>");
        sb.append("<entry field=\"12\"/>");
        sb.append("</entries>");
        String expected = sb.toString();
        try (BufferedReader reader = Files.newBufferedReader(dest.toPath())) {
            sb = new StringBuilder();
            reader.lines().forEach(sb::append);
            String result = sb.toString();
            assertThat(result, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}