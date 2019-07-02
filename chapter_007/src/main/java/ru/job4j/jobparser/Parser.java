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
import java.util.function.Predicate;

/*
парсинг HTML с помощью jsoup

Первым делом необходимо получить экземпляр класса Document из org.jsoup.nodes.Document с указанием на источник для разбора.
Им может выступать как локальный файл, так и ссылка.
 */
public class Parser {
    private final Logger log = LogManager.getLogger(Parser.class.getName());
    private Map<String, Integer> month;

    public Parser() {
        this.setMonth();
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

    public List<Vacancy> parse(Vacancy last) {
        List<Vacancy> result = new ArrayList<>();
        /*
        Использую предикат для проверки текущей вакансии. Если вакансия last, поученная из базы данных равна null,
        значит БД пуста, и получить нужно все вакансии текущего года. Если last не null то получать надо все вакансии,
        пока не получим вакансию эквивалентную последней
        */
        Predicate<Vacancy> p;
        int currentYear = LocalDate.now().getYear();
        if (last!=null) {
            p = vacancy -> vacancy.equals(last);
        } else {
            p= vacancy -> vacancy.getDateCreation().getYear() != currentYear;
        }
        /*
        int i - для подстановки в URL, count - счетчик полученных вакансий, flag - для выхода из цикла
         */
        int i = 1;
        int globalCount = 0;
        boolean flag = true;
        do {
            String url = String.format("https://www.sql.ru/forum/job-offers/%d", i++);
            Document doc = this.getDoc(url);
            Elements vacs = doc.getElementsByAttributeValue("class", "forumTable").get(0).getElementsByTag("tr");
            int count = 0;
            for (Element element : vacs) {
                String title = element.child(1).text();
                String lowTitle = title.toLowerCase();
                if (lowTitle.contains("java ") && !lowTitle.contains("java script")) {
                    String tmpURL = element.child(1).select("a").attr("href");
                    Document temp = this.getDoc(tmpURL);
                    Element vacDesc = temp.getElementsByAttributeValue("class", "msgBody").get(1);
                    String desc = vacDesc.text();
                    String author = element.child(2).text();
                    String authorURL = element.child(2).select("a").attr("href");
                    LocalDate date = this.getDate(element.child(5).text());
                    Vacancy vac = new Vacancy(title, desc, tmpURL, author, authorURL, date);
                    if (p.test(vac)) {
                        flag = false;
                        break;
                    }
                    result.add(vac);
                    count++;
                }
            }
            log.info(String.format("Were found %d Java vacancies. On page %s", count, doc.location()));
            globalCount +=count;
        } while (flag);
        log.info(String.format("Total found %d Java vacancies. On Date %s", globalCount, LocalDate.now().toString()));
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

    private Document getDoc(String url) {
        Document result = null;
        try {
            result = Jsoup.connect(url)
                    .get();
            log.info(String.format("Connected to %s on Date %s", url, LocalDate.now().toString()));
        } catch (IOException e) {
            log.error("IOException on Document init", e);
        }
        return result;
    }
}
