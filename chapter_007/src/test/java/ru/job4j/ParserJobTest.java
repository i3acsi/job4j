package ru.job4j;

import org.junit.Test;
import org.openqa.selenium.By;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserJobTest extends WebDriver {

    @Test
    public void firstTest() throws InterruptedException, IOException {

        String jdbc_url = "jdbc:postgresql://localhost:5432/inpProf";
        String username = "postgres";
        String password = "password";

        try {
            Connection conn = DriverManager.getConnection(jdbc_url, username, password);
            //PreparedStatement st = conn.prepareStatement("SELECT t.type\n"
            int[] c = new int[1];
            c[0] = 0;
            String[] name = new String[1];
            String[] code = new String[1];
            String[] ETKS = new String[1];
            String[] ETKS_paragroph = new String[1];
            String[] paragraph = new String[1];
            String[] operation = new String[1];
            String[] OKZ = new String[1];
            String[] category = new String[1];
            driver.get("http://oot-oos/sova");
            Files.newBufferedReader(Paths.get("D:/projects/job4j/chapter_007/profHref.txt")).lines().forEach(url -> {
                driver.get(url);
                driver.findElements(By.tagName("tr")).stream().skip(11).limit(9).forEach(x -> {
                    //
                    switch (c[0]) {
                        case 0:
                            name[0] = ((x.getText().substring(34)));
                            c[0]++;
                            break;
                        case 1:
                            code[0] = ((x.getText().substring(25)));
                            c[0]++;
                            break;
                        case 2:
                            ETKS[0] = ((x.getText().substring(5)));
                            c[0]++;
                            break;
                        case 3:
                            ETKS_paragroph[0] = ((x.getText().substring(21)));
                            c[0]++;
                            break;
                        case 4:
                            paragraph[0] = ((x.getText().substring(8)));
                            c[0]++;
                            break;
                        case 5:
                            operation[0] = ((x.getText().substring(8)));
                            c[0]++;
                            break;
                        case 6:
                            OKZ[0] = ((x.getText().substring(4)));
                            c[0]++;
                            break;
                        case 7:
                            category[0] = ((x.getText().substring(9)));
                            c[0]++;
                            break;
                        case 8:
                            c[0] = 0;
                            try {
                                PreparedStatement st = conn.prepareStatement("insert into profs (name, code, ETKS, ETKS_paragraph, paragraph, operation, OKZ, category) values (?,?,?,?,?,?,?,?) ");
                                st.setString(1, name[0]);
                                st.setString(2, code[0]);
                                st.setString(3, ETKS[0]);
                                st.setString(4, ETKS_paragroph[0]);
                                st.setString(5, paragraph[0]);
                                st.setString(6, operation[0]);
                                st.setString(7, OKZ[0]);
                                st.setString(8, category[0]);
                                System.out.println(name[0]);
                                st.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;


                    }


                });
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // driver.get("http://oot-oos/sova/dirs/dirProff/p32_1087970540");

        //.findElementsByTagName("table").forEach(str-> System.out.println(str.getAttribute("a hre")));
//        String title = driver.getTitle().trim();
//        assertThat(title, is("База данных ИЯФ"));
//        driver.findElement(By.id("inFocus")).sendKeys("Gasevskiy");
//        Thread.sleep(500);
//
//        Thread.sleep(500);
//        driver.findElement(By.name("button")).click();
//        Thread.sleep(500);
//        driver.get("https://irma.inp.nsk.su/db/OKpers?num_input=765916");
//        Thread.sleep(500);
//        Files.newBufferedReader(Paths.get("C:/Users/Gasevskiy/Desktop/Работа//Фсс/medical.csv")).lines().forEach(this::inspect);


    } //<a href="OKpers?num_input=765916">Сведения ОК </a> https://irma.inp.nsk.su/db/OKpers?num_input=765916 fio_chck

    private static String mod(String str) {
        return "/---/".equals(str) ? "null" : str;
    }

    private void inspect(String str) {
        String string = str.substring(0, str.indexOf(";")).trim();
        System.out.printf("%s;", string);
        try {
            Thread.sleep(1000);
            driver.findElement(By.name("fio_chck")).sendKeys(string);
            Thread.sleep(2000);
            //driver.findElement(By.xpath("//option[@value='          ПОИСК           ']")).click();
            driver.findElements(By.cssSelector("input[type=\"submit\"]")).get(1).click();
            //driver.findElement(By.linkText("Найти сотрудника по фамилии")).click();
            Thread.sleep(2000);
            String url = driver.getCurrentUrl();
            url = String.format("https://irma.inp.nsk.su/db/OKpers/fio_data.php?rowidx=0&usertbn=%s&num_input=765916", url.substring(url.indexOf("usertbn=") + 8, url.indexOf("&num")));
            Thread.sleep(2000);
            driver.get(url);
            Thread.sleep(1000);
            String data = (driver.findElement(By.tagName("h4")).getText());
            data = data.substring(data.indexOf("приема ") + 7, data.length());
            System.out.println(data);
            driver.get("https://irma.inp.nsk.su/db/OKpers?num_input=765916");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}