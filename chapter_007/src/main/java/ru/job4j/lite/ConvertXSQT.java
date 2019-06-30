package ru.job4j.lite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;

public class ConvertXSQT {
    private final String ln = System.lineSeparator();

    public void convert(File source, File dest, File scheme) {
        isSchemeExists(scheme);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void isSchemeExists(File scheme) {
        if (!scheme.exists()) {
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(scheme.toPath()))) {
                writer.println("<?xml version=\"1.0\"?>");
                writer.println("<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">");
                writer.println("    <xsl:template match=\"/\">");
                writer.println("        <entries>");
                writer.println("            <xsl:for-each select=\"entries/entry\">");
                writer.println("                <entry>");
                writer.println("                    <xsl:attribute name=\"field\">");
                writer.println("                        <xsl:value-of select=\"field\"/>");
                writer.println("                    </xsl:attribute>");
                writer.println("                </entry>");
                writer.println("            </xsl:for-each>");
                writer.println("        </entries>");
                writer.println("    </xsl:template>");
                writer.print("</xsl:stylesheet>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}