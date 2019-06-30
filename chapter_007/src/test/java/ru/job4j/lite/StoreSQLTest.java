package ru.job4j.lite;

import org.junit.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
    private static Config config = new Config();
    private static StoreSQL sql = new StoreSQL(config);


    @BeforeClass
    public static void clean() {
        System.out.println("execute before");
        sql.clean();
    }

    @AfterClass
    public static void close() {
        System.out.println("execute after");
        try {
            sql.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateThenLoadAndClean() {
        System.out.println("execute generateThenLoadAndClean");
        sql.generate(12);

        List<Entry> result = sql.load();
        assert result.size() == 12;
        assert sql.clean() == 12;
        Iterator<Entry> iterator = result.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            assertThat(iterator.next().getField(), is(i++));
        }
    }

}