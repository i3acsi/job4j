package ru.job4j.exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Необходим написать метод, который возвращает список всех скриптов, которые нужно для загрузки входящего скрипта.
Например, чтобы выполнить скрипт 1. нужно выполнить скрипт (2, 3), которые в свою очередь зависят от 4 и 5 скрипта.
 */

public class ScriptProcess {
    public List<Integer> listLoad(Map<Integer, List<Integer>> ds, Integer scriptId) {
        List<Integer> result = new ArrayList<>();
        LinkedList<Integer> data = new LinkedList<>();
        data.offer(scriptId);
        while (!data.isEmpty()) {
            Integer el = data.getLast();
            List<Integer> list1 = ds.remove(el);
            if (list1 == null || list1.size() == 0) {
                int temp = data.removeLast();
                if (!result.contains(temp)) {
                    result.add(temp);
                }
            } else {
                list1.forEach(data::offer);
            }
        }
        result.remove(result.size() - 1);
        return result;
    }
}
