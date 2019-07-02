package ru.job4j.jobparser;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class VacancySQL implements AutoCloseable{
    private Properties properties = new Properties();
    private Connection connection;
    private List<Vacancy> vacancies;
    private final Logger log = LogManager.getLogger(VacancySQL.class.getName());

    public VacancySQL() {
        this.getProperties("parser.properties");
        // метод parse принимает Последнюю вакансию из БД
        Vacancy last = structureCheck();
        Parser parser = new Parser();
        vacancies = parser.parse(last);
    }

    private Vacancy structureCheck() {
        Vacancy result = null;
        getConnection();
        try (PreparedStatement st = connection.prepareStatement(
                "CREATE TABLE if NOT EXISTS vacancy (\n"
                        + "id SERIAL PRIMARY KEY NOT NULL,\n"
                        + "title VARCHAR (1000),\n"
                        + "description TEXT,\n"
                        + "url VARCHAR (1000),\n"
                        + "author VARCHAR (1000),\n"
                        + "author_URL VARCHAR (1000),\n"
                        + "date_creation DATE\n"
                        + ");")) {
            st.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException on CREATE TABLE", e);
        }
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM vacancy ORDER BY date_creation DESC LIMIT 1;")) {
            try (ResultSet set = st.executeQuery()){
                if (set.next()) {
                    result = Vacancy.getVac(set);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException on SELECT FROM vacancy TABLE", e);
        }
        return result;
    }

    private void save() {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO vacancy " +
                    "(title, description, url, author, author_URL, date_creation) VALUES (?, ?, ?, ?, ?, ?);")) {
                for (Vacancy v: vacancies) {
                    ps.setString(1, v.getTitle());
                    ps.setString(2, v.getDescription());
                    ps.setString(3, v.getUrl());
                    ps.setString(4, v.getAuthor());
                    ps.setString(5, v.getAuthorURL());
                    ps.setDate(6, Date.valueOf(v.getDateCreation()));
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error("SQLException on SAVE", e);
        }

    }

    private void getConnection() {
        String username = String.valueOf(this.properties.get("username"));
        String password = String.valueOf(this.properties.get("password"));
        String url = String.valueOf(this.properties.get("url"));
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getProperties(String name) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(name)) {
            this.properties.load(is);
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        try (PreparedStatement ps = connection.prepareStatement(
                "TRUNCATE TABLE vacancy RESTART IDENTITY;")) {
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        VacancySQL sql = new VacancySQL();
        sql.save();
    }
}
