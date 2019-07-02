package ru.job4j.jobparser;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VacancySQL implements AutoCloseable{
    private Config config;
    private Connection connection;
    private final Logger log = LogManager.getLogger(VacancySQL.class.getName());

    public VacancySQL() {
        this.config = new Config();
        // если данных нет - то flag = true и getVacancies должен принять true
        boolean flag = structureCheck();
        try {
            this.getVacancies(flag).forEach(x-> System.out.println(x.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    возвращает false, если структура не пустая, и соответственно true - если данных нет
     */
    private boolean structureCheck() {
        boolean result = false;
        getConnection();
        try (PreparedStatement st = connection.prepareStatement(
                "CREATE TABLE if NOT EXISTS vacancy (\n"
                        + "id SERIAL PRIMARY KEY NOT NULL,\n"
                        + "title VARCHAR (100) NOT NULL,\n"
                        + "description VARCHAR (500) NOT NULL,\n"
                        + "url VARCHAR (100) NOT NULL,\n"
                        + "author VARCHAR (100) NOT NULL,\n"
                        + "author_URL VARCHAR (100) NOT NULL,\n"
                        + "date_creation DATE NOT NULL,\n"
                        + ");")) {
            st.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException on CREATE TABLE", e);
        }
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM vacancy")) {
            try (ResultSet set = st.executeQuery()){
                if (!set.next()) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Set<Vacancy> getVacancies(boolean flag) throws IOException {
        int currentYear = LocalDate.now().getYear();
        int year;
        Set<Vacancy> result = new HashSet<>();
        int i = 1;
        do {
            String url = String.format("https://www.sql.ru/forum/job-offers/%d", i++);
            Parser p = new Parser(url);
            Set<Vacancy> temp = p.parse();
            result.addAll(temp);
            year = 0;
            if (!temp.isEmpty()) {
               year = temp. get(temp.size() - 1).getDateCreation().getYear();
            }
        } while (flag && (year == currentYear || year == 0));
        result.stream().filter()
        return result;
    }

    private void getConnection() {
        String username = String.valueOf(this.config.get("username"));
        String password = String.valueOf(this.config.get("password"));
        String url = String.valueOf(this.config.get("url"));
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
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
}
