package ru.job4j.jobparser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class Vacancy {
    private int id;
    private String title;
    private String description;
    private String url;
    private String author;
    private String authorURL;
    private LocalDate dateCreation;
    private String ln = System.lineSeparator();

    public Vacancy(int id, String title, String description, String url, String author, String authorURL, LocalDate dateCreation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.author = author;
        this.authorURL = authorURL;
        this.dateCreation = dateCreation;
    }

    public Vacancy(String title, String description, String url, String author, String authorURL, LocalDate dateCreation) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.author = author;
        this.authorURL = authorURL;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthorURL() {
        return authorURL;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(title, vacancy.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Vacancy{" + ln +
                "id=" + id + ln +
                ", title='" + title + '\'' + ln +
                ", description='" + description + '\'' + ln +
                ", url='" + url + '\'' + ln +
                ", author='" + author + '\'' + ln +
                ", dateCreation=" + dateCreation.toString() +
                '}';
    }

    public static Vacancy getVac(ResultSet input) {
        Vacancy result = null;
        try (ResultSet set = input)
        {
            String title = set.getString("title");
            String description = set.getString("description");
            String url = set.getString("url");
            String author = set.getString("author");
            String authorURL = set.getString("author_URL");
            LocalDate dateCreation = set.getDate("date_creation").toLocalDate();
            result = new Vacancy(title, description, url, author, authorURL, dateCreation);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
