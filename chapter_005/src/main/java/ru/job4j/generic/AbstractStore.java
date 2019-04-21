package ru.job4j.generic;

import java.util.Iterator;
import java.util.function.Consumer;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;
   // private int position = 0;
    private static final int SIZE = 10;
    private  Iterator<T> iterator;

    public AbstractStore() {
        this.store = new SimpleArray<>(SIZE);
    }

    @Override
    public void add(T item) {
        store.add(item);
    }

    @Override
    public boolean replace(String id, T item) {
        int position = findPosition(id);
        return check(id, x->x.set(position, item));
    }

    @Override
    public boolean delete(String id) {
        int position = findPosition(id);
        return check(id, x->x.remove(position));
    }

    @Override
    public T findById(String id) {
        int position = findPosition(id);
        return position == -1 ? null : store.get(position);
    }

    private int findPosition(String id) {
        iterator = store.iterator();
        int result = -1, temp = 0;
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (id.equals(item.getId())) {
                result = temp;
                break;
            }
            temp++;
        }
        return result;
    }

    public Iterator<T> iterator() {
        return  store.iterator();
    }

    public int size() {
        return store.size();
    }

    private boolean check(String id, Consumer<SimpleArray> func) {
        int position = findPosition(id);
        boolean result = false;
        if (position <= store.size()) {
            func.accept(store);
            result = true;
        }
        return result;
    }
}
