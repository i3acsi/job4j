package ru.job4j.filter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {
    private List<Student> students = new ArrayList<>();
    private static final Random RN = new Random();

    public School(int capacity) {
        for (int i = 0; i < capacity; i++) {
            students.add(new Student(RN.nextInt(100), String.format("name%d", i)));
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    public TreeMap<String, Student> collectToMap(List<Student> stud) {
        TreeMap<String, Student> res = new TreeMap<>();
        stud.forEach(x->res.put(x.getName(), x));
        return res;
    }

    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted()
                .takeWhile(x->(x.getScore())>bound)
                .collect(Collectors.toList());
    }
}
