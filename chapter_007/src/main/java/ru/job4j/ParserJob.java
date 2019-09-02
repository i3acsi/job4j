package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ParserJob {
    public static void main(String[] args) {
        ParserJob job = new ParserJob();
        String url = "D:/Users Documents/Gasevskiy/Downloads/sova.html";
        Document doc = job.getDoc(url);
        Elements vacs = doc.getElementsByAttribute("href");//getElementsByAttributeValue("class", "part").get(1).getAllElements();
        int i = 0;
        for (Element e : vacs) {
            if (i == 0) {
                System.out.println(e.absUrl("href"));
                i++;
            } else {
                i = 0;
            }
        }
    }

    private Document getDoc(String url) {
        Document result = null;
        File input = new File(url);
        try {
            result = Jsoup.parse(input, "UTF-8", "http://example.com/");
            System.out.println((String.format("Connected to %s on Date %s", url, LocalDate.now().toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
