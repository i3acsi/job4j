package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;

public class SQLStorage {
    private final Logger log = LoggerFactory.getLogger(SQLStorage.class);


    public void init() {
        Logger l = this.log;
        String url = "jdbc:postgresql://localhost:5432/db_134839";
        String username = "postgres";
        String password = "nicKh0tpXs2z";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password); //используется PrepareStatement чтобы защититься от sqlinjection
            PreparedStatement st = conn.prepareStatement("SELECT t.type\n"
                    + "FROM car c\n"
                    + "RIGHT OUTER JOIN transmission t ON c.id_transmission=t.id\n"
                    + "WHERE c.id IS NULL\n"
                    + "\tUNION\n"
                    + "\tSELECT e.name\n"
                    + "\tFROM car c\n"
                    + "\tRIGHT OUTER JOIN engine e ON c.id_engine=e.id\n"
                    + "\tWHERE c.id IS NULL\n"
                    + "\t\tUNION\n"
                    + "\t\tSELECT c_b.name\n"
                    + "\t\tFROM car c\n"
                    + "\t\tRIGHT OUTER JOIN car_body c_b ON c.id_car_body=c_b.id\n"
                    + "\t\tWHERE c.id IS NULL;");
            //st.setNull(1, Types.);
            //int rowsDeleted = st.executeUpdate(); При этом в PrepareStatement вторым параметром Statement.RETURN_GENERATED_KEYS - для возврата новых id
            //ResultSet generatedKeys = st.getGeneratedKeys
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s", rs.getString("type")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
