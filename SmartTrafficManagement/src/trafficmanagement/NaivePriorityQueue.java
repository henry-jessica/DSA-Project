package trafficmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class NaivePriorityQueue<T extends Comparable<? super T>> implements Queue<T> {
    private final ArrayList<T> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean add(T e) {
        return list.add(e);
    }

    @Override
    public boolean offer(T e) {
        try {
            list.add(e);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    @Override
    public T remove() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        // Same logic as poll(), but we throw if empty
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return list.remove(minIndex);
    }

    @Override
    public T poll() {
        if (list.isEmpty()) {
            return null;
        }

        // Find the minimum element (O(n) operation)
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(minIndex)) < 0) {
                minIndex = i;
            }
        }

        // Remove and return the minimum element
        return list.remove(minIndex);
    }

    @Override
    public T element() {
        return list.getFirst();
    }

    @Override
    public T peek() {
        if (list.isEmpty()) {
            return null;
        }

        // Find the minimum element (O(n) operation)
        T minValue = list.get(0);
        for (T value : list) {
            if (value.compareTo(minValue) < 0) {
                minValue = value;
            }
        }
        return minValue;
    }

}
