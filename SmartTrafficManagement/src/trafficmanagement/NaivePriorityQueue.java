package trafficmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * A naive implementation of a priority queue using an {@link ArrayList}.
 * 
 * <p>
 * This implementation does not maintain heap properties but instead stores
 * elements
 * in an unsorted list. Operations like insertion are O(1), while retrieval and
 * removal of the smallest element are O(n) due to searches.
 * </p>
 * 
 * @param <T> The type of elements stored in the priority queue. Must implement
 *            {@link Comparable}.
 */
public class NaivePriorityQueue<T extends Comparable<? super T>> implements Queue<T> {
    private final ArrayList<T> list = new ArrayList<>();

    /**
     * 
     * Returns the number of elements in this priority queue.
     *
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return boolean representing whether or not the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Checks if this queue contains the specified element.
     *
     * @param o the element to check.
     * @return {@code true} if the element is present, {@code false} otherwise.
     */
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     * Returns an iterator over the elements in this queue.
     *
     * @return an iterator over the elements.
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    /**
     * @return an array containing all the elements in this queue.
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Return an array containing all the elements in this queue.
     * 
     * @param a an array in which all the elements should be stored.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    /**
     * Removes the given element from the queue. Should be avoided when using the
     * queue because it requires a linear scan.
     * 
     * @param o the object to be removed.
     * @return True if the object was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    /**
     * Checks if this queue contains all items in the given collection.
     * 
     * @param c the collection to be compared with the queue.
     * @return True if the queue contains all items in c, false otherwise.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    /**
     * Adds all elements in a given collection to the queue.
     * 
     * @param c a collection containing all the elements to be added.
     * @return True if successful, false otherwise.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    /**
     * Attempts to remove all elements in the given collection from the queue.
     * 
     * @param c a collection containing the elements to be removed.
     * @return True if successful, false otherwise.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    /**
     * Retains only those elements in this queue that are in the specified
     * collection.
     *
     * @param c the collection containing elements to retain.
     * @return {@code true} if the queue changed as a result, {@code false}
     *         otherwise.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Adds an element to the priority queue.
     *
     * @param e the element to add.
     * @return {@code true} as per {@link Queue} contract.
     */
    @Override
    public boolean add(T e) {
        return list.add(e);
    }

    /**
     * Inserts the specified element into this priority queue.
     * 
     * @param e the element to insert.
     * @return {@code true} if successful, {@code false} if an exception occurs.
     */
    @Override
    public boolean offer(T e) {
        try {
            list.add(e);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    /**
     * Retrieves and removes the smallest element of this queue.
     *
     * @return the smallest priority element.
     * @throws IllegalStateException if the queue is empty.
     */
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

    /**
     * Retrieves and removes the smallest element of this queue, or returns
     * {@code null} if empty.
     *
     * @return the smallest element, or {@code null} if empty.
     */
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

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the smallest element.
     * @throws IndexOutOfBoundsException if the queue is empty.
     */
    @Override
    public T element() {
        return list.getFirst();
    }

    /**
     * Retrieves, but does not remove, the smallest element of this queue, or
     * returns {@code null} if empty.
     *
     * @return the smallest element, or {@code null} if empty.
     */
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
