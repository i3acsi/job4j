package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CalcFunction {


    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> res = new ArrayList<>();
        for (double index = start; index != end; index++) {
            res.add(func.apply(index));
        }
        return res;
    }

}


