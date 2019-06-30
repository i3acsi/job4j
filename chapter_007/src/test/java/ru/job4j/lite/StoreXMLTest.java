package ru.job4j.lite;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreXMLTest {
    private StoreSQL sql;
    private StoreXML storeXML;

    @Before
    public void init() {
        this.sql = new StoreSQL(new Config());
        sql.clean();
        sql.generate(12);
        this.storeXML = new StoreXML(sql);
    }

    @Test
    public void test() {
        File file = new File("target_134824.xml");
        storeXML.save(file);
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            final StringBuilder sb0 = new StringBuilder();
            reader.lines().forEach(sb0::append);
            String result = sb0.toString();
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            sb.append("<entries>");
            sb.append("    <entry>");
            sb.append("        <field>1</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>2</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>3</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>4</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>5</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>6</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>7</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>8</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>9</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>10</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>11</field>");
            sb.append("    </entry>");
            sb.append("    <entry>");
            sb.append("        <field>12</field>");
            sb.append("    </entry>");
            sb.append("</entries>");
            String expected = sb.toString();
            assertThat(result, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}