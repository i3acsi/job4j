package ru.job4j.jobparser;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    public Config() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("parser.properties")) {
            values.load(in);
            Class.forName(values.getProperty("driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
