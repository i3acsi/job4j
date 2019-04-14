package ru.job4j.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    private static School school;
    private static List<Student> students;

    @Before
    public void before() {
        school = new School(100);
        students = school.getStudents();
    }

    @After
    public void after() {
        students.clear();
    }

    @Test
    public void from70to100() {
        List<Student> result = school.collect(students, x -> x.getScore() > 70 & x.getScore() <= 100);
        System.out.println("from 70 to 100");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(s.toString());
            assert (score > 70 & score <= 100);
        }
    }

    @Test
    public void from50to70() {
        List<Student> result = school.collect(students, x -> x.getScore() > 50 & x.getScore() <= 70);
        System.out.println("from 50 to 70");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(s.toString());
            assert (score > 50 & score <= 70);
        }
    }

    @Test
    public void from0to50() {
        List<Student> result = school.collect(students, x -> x.getScore() >= 0 & x.getScore() <= 50);
        System.out.println("from 0 to 50");
        for (Student s : result) {
            int score = s.getScore();
            System.out.println(s.toString());
            assert (score >= 0 & score <= 100);
        }
    }

    @Test
    public void collectToMapTest() {
        Map<String, Student> result = school.collectToMap(students);
        List<Student> res = new ArrayList<>(result.values());
        //res.sort(Student::compareTo);
        students.sort(Student::compareTo);
        assertThat(res, is(students));
    }
}
