package ru.job4j.jobparser;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.quartz.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class VacancySQL implements AutoCloseable, Job {
    private Config properties;
    private Connection connection;
    private Set<Vacancy> vacancies;
    private Set<Vacancy> loaded;
    private final Logger log = LogManager.getLogger(VacancySQL.class.getName());

    public VacancySQL(Connection conn) {
        this.properties = new Config();
        // метод parse принимает Последнюю вакансию из БД
        this.connection = conn;
        Vacancy last = structureCheck();
        this.loadFromDB();
        Parser parser = new Parser();
        this.vacancies = parser.parse(last);
    }

    public VacancySQL() {
        this.properties = new Config();
        // метод parse принимает Последнюю вакансию из БД
        Vacancy last = structureCheck();
        this.loadFromDB();
        Parser parser = new Parser();
        this.vacancies = parser.parse(last);
    }

    private Vacancy structureCheck() {
        Vacancy result = null;
        if (connection == null) {
            getConnection();
        }
        try (PreparedStatement st = connection.prepareStatement(
                "CREATE TABLE if NOT EXISTS vacancy (\n"
                        + "id SERIAL PRIMARY KEY NOT NULL,\n"
                        + "title VARCHAR (1000),\n"
                        + "description TEXT,\n"
                        + "url VARCHAR (1000),\n"
                        + "author VARCHAR (1000),\n"
                        + "author_URL VARCHAR (1000),\n"
                        + "date_creation TIMESTAMP \n"
                        + ");")) {
            st.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException on CREATE TABLE", e);
        }
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM vacancy ORDER BY date_creation DESC LIMIT 1;")) {
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    result = Vacancy.getVac(set);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException on SELECT FROM vacancy TABLE", e);
        }
        return result;
    }

    private void loadFromDB() {
        if (this.loaded != null) {
            this.loaded.clear();
        } else {
            this.loaded = new HashSet<>();
        }
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM vacancy;")) {
            try (ResultSet set = st.executeQuery()) {
                while (set.next()) {
                    Vacancy temp = Vacancy.getVac(set);
                    this.loaded.add(temp);
                }
            }
        } catch (SQLException e) {
            log.error("SQLException on SELECT FROM vacancy TABLE", e);
        }
    }

    public void save() {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO vacancy "
                    + "(title, description, url, author, author_URL, date_creation) VALUES (?, ?, ?, ?, ?, ?);")) {
                this.vacancies.removeAll(this.loaded);
                int count = 0;
                for (Vacancy v : vacancies) {
                    ps.setString(1, v.getTitle());
                    ps.setString(2, v.getDescription());
                    ps.setString(3, v.getUrl());
                    ps.setString(4, v.getAuthor());
                    ps.setString(5, v.getAuthorURL());
                    ps.setTimestamp(6, Timestamp.valueOf(v.getDateCreation()));
                    ps.addBatch();
                    count++;
                }
                ps.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
                log.info(String.format("Totally added to DB %d Java vacancies. On Date %s", count, LocalDateTime.now().toString()));
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
            log.error("SQLException on getConnection", e);
        }
    }

    public void clean() {
        try (PreparedStatement ps = connection.prepareStatement(
                "TRUNCATE TABLE vacancy RESTART IDENTITY;")) {
            ps.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException on TRUNCATE TABLE vacancy", e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("SQLException on close connection", e);
            }
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("In VacancySQL - executing its JOB at "
                + LocalDateTime.now().toString() + " by " + context.getTrigger().getDescription());
        try (VacancySQL sql = new VacancySQL()) {
            sql.save();
        }
    }


}