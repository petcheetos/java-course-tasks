package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator {
    private final List<T> collection;
    private int currentIndex;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        currentIndex = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return collection.get(currentIndex--);
    }
}
