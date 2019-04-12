package ru.job4j.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    private List<Student> students = new ArrayList<>();
    private static final Random RN = new Random();

    public School(int capacity) {
        for (int i = 0; i < capacity; i++) {
            students.add(new Student(RN.nextInt(100)));
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }
}
