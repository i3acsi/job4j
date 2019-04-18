package ru.job4j.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        for (Student s : result) {
            int score = s.getScore();
            assert (score > 70 & score <= 100);
        }
    }

    @Test
    public void from50to70() {
        List<Student> result = school.collect(students, x -> x.getScore() > 50 & x.getScore() <= 70);
        for (Student s : result) {
            int score = s.getScore();
            assert (score > 50 & score <= 70);
        }
    }

    @Test
    public void from0to50() {
        List<Student> result = school.collect(students, x -> x.getScore() >= 0 & x.getScore() <= 50);
        for (Student s : result) {
            int score = s.getScore();
            assert (score >= 0 & score <= 100);
        }
    }

    @Test
    public void collectToMapTest() {
        Map<String, Student> result = school.collectToMap(students);
        List<Student> res = new ArrayList<>(result.values());
        students.sort((Comparator.comparing(Student::getName)));
        assertThat(res, is(students));
    }

    @Test
    public void levelOfTest() {
        List<Student> result = school.levelOf(students, 90);
        List<Student> expected = students.stream().filter(x->x.getScore() > 90).sorted().collect(Collectors.toList());
        assertThat(result, is(expected));
    }
}
