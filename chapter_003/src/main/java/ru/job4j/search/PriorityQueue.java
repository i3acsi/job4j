package ru.job4j.search;


import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
//        int i = tasks.size();
//        for (int index = 0; index < tasks.size(); index++) {
//            if (task.getPriority() < tasks.get(index).getPriority()) {
//                i = index;
//                break;
//            }
//        }
//        tasks.add(i, task);
        var i = tasks.size();
        for (var t : tasks) {
            if (task.getPriority() < t.getPriority()) {
                i--;
            }
        }
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public int length() {
        return tasks.size();
    }
}