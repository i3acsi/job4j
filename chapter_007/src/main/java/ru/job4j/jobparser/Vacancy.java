package ru.job4j.jobparser;

import java.sql.Timestamp;
import java.util.Objects;

public class Vacancy {
    private int id;
    private String title;
    private String description;
    private String url;
    private String author;
    private long dateCreation;

    public Vacancy(int id, String title, String description, String url, String author, long dateCreation) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.author = author;
        this.dateCreation = dateCreation;
    }

    public Vacancy(String title, String description, String url, String author, long dateCreation) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public long getDateCreation() {
        return dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return dateCreation == vacancy.dateCreation &&
                Objects.equals(title, vacancy.title) &&
                Objects.equals(description, vacancy.description) &&
                Objects.equals(url, vacancy.url) &&
                Objects.equals(author, vacancy.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, url, author, dateCreation);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
