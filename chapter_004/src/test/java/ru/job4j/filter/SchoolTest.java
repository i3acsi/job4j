package ru.job4j.filter;

import org.junit.Test;

import java.util.List;

public class SchoolTest {
    private static School school = new School(100);
    private static List<Student> students = school.getStudents();

    @Test
    public void from70to100() {
        List<Student> result = school.collect(students, x -> x.getScore() > 70 & x.getScore() <= 100);
        System.out.println("from 70 to 100");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(score);
            assert (score > 70 & score <= 100);
        }
    }

    @Test
    public void from50to70() {
        List<Student> result = school.collect(students, x -> x.getScore() > 50 & x.getScore() <= 70);
        System.out.println("from 50 to 70");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(score);
            assert (score > 50 & score <= 70);
        }
    }

    @Test
    public void from0to50() {
        List<Student> result = school.collect(students, x -> x.getScore() >= 0 & x.getScore() <= 50);
        System.out.println("from 0 to 50");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(score);
            assert (score >= 0 & score <= 100);
        }
    }
}
