package ru.job4j.jobparser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/*
парсинг HTML с помощью jsoup

Первым делом необходимо получить экземпляр класса Document из org.jsoup.nodes.Document с указанием на источник для разбора.
Им может выступать как локальный файл, так и ссылка.
 */
public class Parser {
    private final Logger log = LogManager.getLogger(Parser.class.getName());
    private Document doc;
    private Map<String, Integer> month;

    public Parser(String url) {
        Logger l = this.log;
        this.setMonth();
        try {
            this.doc =  Jsoup.connect(url)
                    .get();
            log.info(String.format("Connected to %s on Date %s", url, LocalDate.now().toString()));
        } catch (IOException e) {
            log.error("IOException on Document init", e);
        }
    }

    private void setMonth() {
        this.month = new HashMap<>(16);
        this.month.put("янв", 1);
        this.month.put("фев", 2);
        this.month.put("мар", 3);
        this.month.put("апр", 4);
        this.month.put("май", 5);
        this.month.put("июн", 6);
        this.month.put("июл", 7);
        this.month.put("авг", 8);
        this.month.put("сен", 9);
        this.month.put("окт", 10);
        this.month.put("ноя", 11);
        this.month.put("дек", 12);
    }


    public Set<Vacancy> parse() throws IOException {
        Set<Vacancy> result = new HashSet<>();
        Elements vacs = doc.getElementsByAttributeValue("class", "forumTable").get(0).getElementsByTag("tr");
        int count = 0;
        for (Element element : vacs) {
            String title = element.child(1).text();
            String lowTitle = title.toLowerCase();
            if (lowTitle.contains("java ") && !lowTitle.contains("java script")){
                String url = element.child(1).select("a").attr("href");
                Document temp = Jsoup.connect(url).get();
                Element vacDesc = temp.getElementsByAttributeValue("class", "msgBody").get(1);
                String desc = vacDesc.text();
                String author = element.child(2).text();
                String authorURL = element.child(2).select("a").attr("href");
                LocalDate date = this.getDate(element.child(5).text());
                Vacancy vac = new Vacancy(title, desc, url, author, authorURL, date);
                result.add(vac);
                count++;
            }
        }
        log.info(String.format("Were found %d Java vacancies. On page %s, On Date %s", count, doc.location(), LocalDate.now().toString()));
        return result;
    }

    private LocalDate getDate(String date) {
        LocalDate result = null;
        String tmpDate = date.split(",")[0];
        if ("сегодня".equals(tmpDate)) {
            result = LocalDate.now();
        } else if ("вчера".equals(tmpDate)) {
            result = LocalDate.now().minusDays(1);
        } else {
            String[] day = tmpDate.split(" ");
            result = LocalDate.of(Integer.valueOf(String.format("20%s",day[2]))
                    , month.get(day[1])
                    , Integer.valueOf(day[0]));

        }
        return result;
    }

//    public static void main(String[] args) {
//        Parser parser = new Parser("https://www.sql.ru/forum/job-offers/1");
//        try {
//            parser.init();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
