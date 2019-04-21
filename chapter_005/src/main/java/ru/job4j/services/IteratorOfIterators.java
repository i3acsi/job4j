package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorOfIterators {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> innerIterator = it.next();

            @Override
            public boolean hasNext() {
                if (!innerIterator.hasNext() & it.hasNext()) {
                    innerIterator = it.next();
                }
                return innerIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return innerIterator.next();
            }
        };
    }
}



