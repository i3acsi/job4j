package ru.job4j;

import org.junit.Test;
import org.openqa.selenium.By;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParserJobTest extends WebDriver {

    @Test
    public void firstTest() throws InterruptedException, IOException {
        driver.get("https://irma.inp.nsk.su/db/");
        String title = driver.getTitle().trim();
        assertThat(title, is("База данных ИЯФ"));
        driver.findElement(By.id("inFocus")).sendKeys("Gasevskiy");
        Thread.sleep(500);
        driver.findElement(By.name("log_pass")).sendKeys("678iop");
        Thread.sleep(500);
        driver.findElement(By.name("button")).click();
        Thread.sleep(500);
        driver.get("https://irma.inp.nsk.su/db/OKpers?num_input=765916");
        Thread.sleep(500);
        Files.newBufferedReader(Paths.get("C:/Users/Gasevskiy/Desktop/Работа//Фсс/medical.csv")).lines().forEach(this::inspect);


    } //<a href="OKpers?num_input=765916">Сведения ОК </a> https://irma.inp.nsk.su/db/OKpers?num_input=765916 fio_chck

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