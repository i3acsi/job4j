package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private DynamicList<T> list;
    private boolean haveNull = false;

    public SimpleSet() {
        list = new DynamicList<T>();
    }

    public boolean add(T value) {
        boolean result = false;
        if (!exists(value)) {
            list.add(value);
            result = true;
        }
        return result;
    }

    public int size() {
        return list.size();
    }

    private boolean exists(Object value) {
        boolean result = false;
        int size = list.size();
        if (size != 0) {
            for (Object temp : list) {
                if (Objects.equals(temp, value)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    @Override
    public Iterator<T> iterator() {
        Iterator iterator = list.iterator();
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return (T) iterator.next();
            }
        };
    }
}
