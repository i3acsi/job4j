package ru.job4j.jobparser;

import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserTest {

    private static final LocalDate DATE = LocalDate.of(2019, 07, 1);
    private static final LocalTime TIME = LocalTime.of(19, 0, 0, 0);
    private static final Vacancy VACANCY = new Vacancy("Java разработчик, Москва [new]",
            "desc",
            "url",
            "author",
            "authorURL",
            LocalDateTime.of(DATE, TIME));

    public Connection init() {
        try (InputStream in = VacancySQL.class.getClassLoader().getResourceAsStream("parser.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);

        }
    }

    @Test
    public void whenAddVacancyThanDbHasIt() {
        try (VacancySQL sql = new VacancySQL(ConnectionRollback.create(this.init()))) {
            sql.clean();
            sql.save(List.of(VACANCY));
            Vacancy expected = sql.loadFromDB().get(0);
            assertThat(expected, is(VACANCY));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddVacancyThatAllreadyInThereThanDoNothing() {
        try (VacancySQL sql = new VacancySQL(ConnectionRollback.create(this.init()))) {
            sql.clean();
            sql.save(List.of(VACANCY));
            Vacancy v = new Vacancy("Java разработчик, Москва [new]", "desc", "u", "a", "au", LocalDateTime.now());
            sql.save(List.of(v));
            List<Vacancy> result = sql.loadFromDB();
            assertThat(result.size(), is(1));
            Vacancy expected = result.get(0);
            assertThat(expected, is(VACANCY));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}