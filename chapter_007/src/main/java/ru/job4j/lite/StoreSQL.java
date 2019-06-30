package ru.job4j.lite;

import javax.xml.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "entries")
public class StoreSQL implements AutoCloseable {
    public List<Entry> entry = new ArrayList<>();
    private Config config;
    private Connection connection;

    public StoreSQL() {
    }

    public StoreSQL(Config config) {
        this.config = config;
        this.getConnection();

    }

    private void getConnection() {

        String url = config.get("url");
        String username = config.get("username");
        String password = config.get("password");
        String driver = config.get("driver-class-name");
        try {
            if (connection != null) {
                connection.close();
            }

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

            try (PreparedStatement st = connection.prepareStatement(
                    "CREATE TABLE if NOT EXISTS 'entry' ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "'field' INT NOT NULL);")) {
                st.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
    метод generate(int size) - генерирует в базе данных n записей.
    описывается схемой
    create table entry {
         field integer;
   }
   2. После запуска приложение:
   2.1) Если таблица entry в БД отсутствует, то создает ее.
   2.2) вставляет в таблицу entry n записей со значениями 1..N. Если в таблице account
   находились записи, то они удаляются перед вставкой.
     */

    public void generate(int size) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO entry (field) VALUES (?);")) {
                for (int i = 1; i <= size; i++) {
                    ps.setInt(1, i);
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public int clean() {
        int result = -1;
        try (PreparedStatement st = connection.prepareStatement(
                "DELETE FROM entry;\n"
                        + "DELETE FROM sqlite_sequence WHERE name='entry';")) {
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setList(List<Entry> list) {
        this.entry = list;
    }


    public List<Entry> load() {
        try (PreparedStatement st = connection.prepareStatement("SELECT count(id) FROM entry;");
             ResultSet rs = st.executeQuery()) {
            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }
            this.entry = new ArrayList<>(size);
            try (PreparedStatement st2 = connection.prepareStatement(
                    "SELECT field FROM entry;");
                 ResultSet set = st2.executeQuery()) {
                while (set.next()) {
                    this.entry.add(new Entry(set.getInt(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.entry;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}