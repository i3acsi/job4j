package ru.job4j.list;

import java.util.*;

public class ArrayCompare {
    static <T> boolean compareArrays(T[] array1, T[] array2) {
        boolean result = true;
        if (array1.length != array2.length) {
            result = false;
        } else {
            int length = (int) (array1.length * 1.25);
            Map<T, Integer> map = new HashMap<>(length);

            for (T t : array1) {
                map.computeIfPresent(t, (key, value) -> ++value);
                map.putIfAbsent(t, 0);
            }

            Integer i;
            for (T t : array2) {
                i = map.computeIfPresent(t, (key, value) -> {
                    --value;
                    return value;
                });
                if (i == null || i < -1) {
                    result = false;
                    break;
                }
            }
        }
//                Integer i1 = map.get(t);
//                if (i1 == null || i1 < 0) {
//                    result = false;
//                    break;
//                } else {
//                    map.put(t, --i1);


//            Set<T> set = new HashSet<>(Arrays.asList(array1));
//            for (T t : array2) {
//                if (!set.remove(t)) {
//                    result = false;
//                    break;
//                }
//            }

        return result;
    }
}
