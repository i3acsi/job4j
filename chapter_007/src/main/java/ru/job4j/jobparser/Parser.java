package ru.job4j.jobparser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
парсинг HTML с помощью jsoup

Первым делом необходимо получить экземпляр класса Document из org.jsoup.nodes.Document с указанием на источник для разбора.
Им может выступать как локальный файл, так и ссылка.
 */
public class Parser {
    private final Logger log = LogManager.getLogger(Parser.class.getName());
    private List<Vacancy> list;
    private Document doc;

    public Parser(String url) {
        Logger l = this.log;
        this.list = new ArrayList<>();
        try {
            this.doc =  Jsoup.connect(url)
                    .get();
        } catch (IOException e) {
            log.error("IOException on Document init", e);
        }
    }

    public void init() {
        Elements vacs = doc.getElementsByTag("<td class=\"postslisttopic\">")
        //vacs.forEach(System.out::println);
        for (Element element : vacs)
            System.out.println(element.text());
    }

    public static void main(String[] args) {
        Parser parser = new Parser("https://www.sql.ru/forum/job-offers/1");
        parser.init();
    }
}
