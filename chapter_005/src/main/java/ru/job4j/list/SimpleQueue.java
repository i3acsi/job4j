package ru.job4j.list;

/**
 * FILO - Очередь
 * @param <T>
 */

public class SimpleQueue<T> {
    public SimpleStack<T> list1 = new SimpleStack<>();
    public SimpleStack<T> list2 = new SimpleStack<>();

    public T poll() {
        migrate(list2, list1);
        return list2.poll();
    }

    public void push(T value) {
        migrate(list1, list2);
        list1.push(value);
    }

    private void migrate(SimpleStack<T> stack, SimpleStack<T> stack2) {
        int length = stack2.size();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                stack.push(stack2.poll());
            }
        }
    }
}
