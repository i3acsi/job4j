package ru.job4j.jobparser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserTest {
    private Connection conn;

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

    @Before
    public void connect() throws SQLException {
        this.conn = ConnectionRollback.create(this.init());
    }

    @After
    public void connectClose() throws SQLException {
        if (!conn.isClosed()) {
            this.conn.close();
        }
    }

    @Test
    public void test() {
        LocalDate date = LocalDate.of(2019, 07, 1);
        LocalTime time = LocalTime.of(19, 0, 0, 0);
        Vacancy vacancy = new Vacancy("Java разработчик, Москва [new]",
                "desc",
                "url",
                "author",
                "authorURL",
                LocalDateTime.of(date, time));
        Parser parser = new Parser();
        Set<Vacancy> result = parser.parse(vacancy);
        result.forEach(x-> assertThat(x.getTitle().toLowerCase().contains("java"), is(true)));
    }

    @Test
    public void sqlTest() throws SQLException {
        try (VacancySQL sql = new VacancySQL(conn);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM vacancy;");
             ResultSet set = ps.executeQuery()) {
            if (set.next()) {
                String title = (set.getString("title"));
                assertThat(title.toLowerCase().contains("java"), is(true));
            }

        }
    }
}