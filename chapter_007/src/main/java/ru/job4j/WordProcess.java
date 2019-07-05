package ru.job4j;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class WordProcess implements AutoCloseable {
    private Connection connection;
    private Properties properties = new Properties();

    public static void main(String[] args) {
        WordProcess wp = new WordProcess();
        Map<Integer, Person> map = wp.loadFromDocx();
        wp.saveToDB(map);
    }

    public WordProcess() {
        getProperties("wordprocess.properties");
        structureCheck();
    }

    public boolean structureCheck() {
        boolean exists = false;

        String url = properties.getProperty("url").replaceFirst("inpmedical", "");
        getConnection(url);
        try (PreparedStatement st = connection.prepareStatement("SELECT datname FROM pg_database;");
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                if ("inpmedical".equals(rs.getString("datname"))) {
                    exists = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!exists) {
            try (PreparedStatement st = connection.prepareStatement("CREATE DATABASE inpmedical;")) {
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        getConnection(properties.getProperty("url"));

        try (PreparedStatement st = connection.prepareStatement(
                "CREATE TABLE if NOT EXISTS person (\n"
                        + "id SERIAL PRIMARY KEY NOT NULL,\n"
                        + "firstName VARCHAR (100) NOT NULL,\n"
                        + "surName VARCHAR (100) NOT NULL,\n"
                        + "patronymic VARCHAR (100) NOT NULL,\n"
                        + "birthday VARCHAR (100) NOT NULL,\n"
                        + "gender VARCHAR (50) NOT NULL,\n"
                        + "department VARCHAR (50) NOT NULL,\n"
                        + "profession VARCHAR (200) NOT NULL,\n"
                        + "factor VARCHAR (5000) NOT NULL,\n"
                        + "medicalExaminations VARCHAR (10000) NOT NULL,\n"
                        + "medicalExaminationPrice INT NOT NULL\n"
                        + ");")) {
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return this.connection != null;
    }

    private void getProperties(String name) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(name)) {
            this.properties.load(is);
            Class.forName(properties.getProperty("driver-class-name"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getConnection(String url) {
        //String url = String.valueOf(this.properties.getProperty("url"));
        String username = String.valueOf(this.properties.getProperty("username"));
        String password = String.valueOf(this.properties.getProperty("password"));
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(url, username, password);
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

    private Map<Integer, Person> loadFromDocx() {
        Map<Integer, Person> personMap = new HashMap<>(600); // 422записи/075 + запас
        File file = new File("./doc.docx");
        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {
            XWPFDocument docxFile = new XWPFDocument(fileInputStream);
            Iterator<IBodyElement> bodyElementIterator = docxFile.getBodyElementsIterator();
            while (bodyElementIterator.hasNext()) {
                int[] i = new int[1];
                IBodyElement element = bodyElementIterator.next();
                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        int r = table.getNumberOfRows();
                        for (int j = 0; j < r; j++) {
                            XWPFTableRow row = table.getRow(j);
                            String[] name = row.getCell(1).getText().split(" ");
                            String firstName = name[0].replaceAll(" ", "");
                            String surName = name[1].replaceAll(" ", "");
                            String patronymic = name[2].replaceAll(" ", "");
                            String birthday = row.getCell(2).getText().replaceAll(" ", "");
                            String gender = row.getCell(3).getText().replaceAll(" ", "");
                            String department = row.getCell(4).getText().replaceAll(" ", "");
                            String profession = row.getCell(5).getText();
                            StringBuilder sb = new StringBuilder();
                            row.getCell(6).getTables().forEach(t -> sb.append(t.getText()));
                            String factor = sb.toString();
                            StringBuilder sb2 = new StringBuilder();
                            row.getCell(7).getTables().forEach(t -> sb2.append(t.getText()));
                            String medicalExaminations = sb2.toString();
                            int medicalExaminationPrice = Integer.valueOf(row.getCell(8).getText().replaceAll(" ", ""));
                            personMap.put(i[0]++, new Person(firstName, surName, patronymic, birthday, gender, department, profession, factor, medicalExaminations, medicalExaminationPrice));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personMap;
    }

    private void saveToDB(Map<Integer, Person> data) {
        data.values().forEach(System.out::println);
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO person (firstName, surName, patronymic, birthday, gender, department, profession, factor, medicalExaminations, medicalExaminationPrice)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
                data.values().forEach(x -> {
                    try {
                        ps.setString(1, x.getFirstName());
                        ps.setString(2, x.getSurName());
                        ps.setString(3, x.getPatronymic());
                        ps.setString(4, x.getBirthday());
                        ps.setString(5, x.getGender());
                        ps.setString(6, x.getDepartment());
                        ps.setString(7, x.getProfession());
                        ps.setString(8, x.getFactor());
                        ps.setString(9, x.getMedicalExaminations());
                        ps.setInt(10, x.getMedicalExaminationPrice());
                        ps.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                ps.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


class Person {
    private String firstName;
    private String surName;
    private String patronymic;
    private String birthday;
    private String gender;
    private String department;
    private String profession;
    private String factor;
    private String medicalExaminations;
    private int medicalExaminationPrice;

    public Person(String firstName, String surName, String patronymic, String birthday, String gender, String department, String profession, String factor, String medicalExaminations, int medicalExaminationPrice) {
        this.firstName = firstName;
        this.surName = surName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.gender = gender;
        this.department = department;
        this.profession = profession;
        this.factor = factor;
        this.medicalExaminations = medicalExaminations;
        this.medicalExaminationPrice = medicalExaminationPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfession() {
        return profession;
    }

    public String getFactor() {
        return factor;
    }

    public String getMedicalExaminations() {
        return medicalExaminations;
    }

    public int getMedicalExaminationPrice() {
        return medicalExaminationPrice;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", factor='" + factor + '\'' +
                ", medicalExaminations='" + medicalExaminations + '\'' +
                ", medicalExaminationPrice=" + medicalExaminationPrice +
                '}';
    }
}


