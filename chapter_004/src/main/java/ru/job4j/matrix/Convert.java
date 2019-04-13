package ru.job4j.matrix;

import java.util.Arrays;
import java.util.stream.Stream;

public class Convert {

    public int[] convertMatrix(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(Arrays::stream).mapToInt(i -> i).toArray();
    }
}
