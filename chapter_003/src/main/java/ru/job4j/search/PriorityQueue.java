package ru.job4j.search;


import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
//        tasks.add(task);
//        tasks = tasks.stream().sorted(comparing(Task::getPriority)).collect(Collectors.toCollection(LinkedList::new));

        int i = 0;
        if (tasks.size() != 0) {
            for (Task t : tasks) {
                if (task.getPriority() < t.getPriority()) {
                    break;
                }
                i++;
            }
            tasks.add(i, task);
        } else tasks.add(task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public int length() {
        return tasks.size();
    }
}