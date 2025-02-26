package trafficmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * An implementation of a priority queue using an {@link ArrayList} as a binary
 * heap.
 * 
 * <p>
 * This implementation does not maintain heap properties to improve performance.
 * Operations like insertion and removal are O(log(n)) and retrieval is O(1).
 * </p>
 * 
 * @param <T> The type of elements stored in the priority queue. Must implement
 *            {@link Comparable}.
 */
public class MyPriorityQueue<T extends Comparable<? super T>> implements Queue<T> {
    private final List<T> heap = new ArrayList<>(); // Underlying array structure for the heap

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param value the element to add
     */
    @Override
    public boolean offer(T value) {
        // Add the new element to the end of the list
        heap.add(value);
        // Restore the min-heap property by "bubbling up" if necessary
        bubbleUp(heap.size() - 1);
        return true;
    }

    /**
     * Retrieves and removes the head of this queue (smallest element),
     * or returns null if this queue is empty.
     *
     * @return the smallest element, or null if empty
     */
    @Override
    public T poll() {
        if (heap.isEmpty()) {
            return null; // or throw an exception if you prefer
        }

        // The smallest element is at the root (index 0)
        T rootValue = heap.get(0);

        // Move the last element to the root and remove the last element
        T lastValue = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            // Restore the min-heap property by "bubbling down" if necessary
            bubbleDown(0);
        }

        return rootValue;
    }

    /**
     * Retrieves, but does not remove, the head of this queue (smallest element),
     * or returns null if this queue is empty.
     *
     * @return the smallest element, or null if empty
     */
    @Override
    public T peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the size of the priority queue
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Restores the min-heap property by shifting the element at index up
     * until the heap property is satisfied.
     *
     * @param index the index of the element to bubble up
     */
    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;

        // While the node is not at the root and the current node is
        // smaller than its parent, swap with the parent.
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * Restores the min-heap property by shifting the element at index down
     * until the heap property is satisfied.
     *
     * @param index the index of the element to bubble down
     */
    private void bubbleDown(int index) {
        int size = heap.size();

        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestIndex = index;

            // Compare with left child
            if (leftChildIndex < size &&
                    heap.get(leftChildIndex).compareTo(heap.get(smallestIndex)) < 0) {
                smallestIndex = leftChildIndex;
            }

            // Compare with right child
            if (rightChildIndex < size &&
                    heap.get(rightChildIndex).compareTo(heap.get(smallestIndex)) < 0) {
                smallestIndex = rightChildIndex;
            }

            // If the smallest index is still the current index, no more bubbling down
            // needed
            if (smallestIndex == index) {
                break;
            }

            // Otherwise, swap and continue
            swap(index, smallestIndex);
            index = smallestIndex;
        }
    }

    /**
     * Swaps the elements at the given indices in the heap.
     *
     * @param i the first index
     * @param j the second index
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Returns true if this queue contains the specified object.
     */
    @Override
    public boolean contains(Object o) {
        return heap.contains(o);
    }

    /**
     * Returns an iterator over the elements in this queue.
     */
    @Override
    public Iterator<T> iterator() {
        List<T> sortedHeap = new ArrayList<>(heap);
        sortedHeap.sort(null); // Sorts in ascending order
        return sortedHeap.iterator();
    }

    /**
     * Returns an array containing all elements in this queue.
     */
    @Override
    public Object[] toArray() {
        return heap.toArray();
    }

    /**
     * Returns an array containing all elements in this queue;
     * the runtime type of the returned array is that of the specified array.
     */
    @Override
    public <U> U[] toArray(U[] a) {
        return heap.toArray(a);
    }

    /**
     * Removes a single instance of the specified element, if present.
     */
    @Override
    public boolean remove(Object o) {
        int index = heap.indexOf(o);
        if (index == -1) {
            return false; // Element not found
        }

        int lastIndex = heap.size() - 1;
        if (index == lastIndex) {
            heap.remove(lastIndex); // If it's the last element, just remove it
        } else {
            heap.set(index, heap.remove(lastIndex)); // Swap with last and remove
            bubbleDown(index); // Restore heap property
        }
        return true;
    }

    /**
     * Returns true if this queue contains all elements of the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return heap.containsAll(c);
    }

    /**
     * Adds all elements of the specified collection to this queue.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = heap.addAll(c);
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            bubbleDown(i); // Heapify the entire heap
        }
        return modified;
    }

    /**
     * Removes all of this queue's elements that are also in the specified
     * collection.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return heap.removeAll(c);
    }

    /**
     * Retains only those elements in this queue that are in the specified
     * collection.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return heap.retainAll(c);
    }

    /**
     * Removes all elements from this queue.
     */
    @Override
    public void clear() {
        heap.clear();
    }

    /**
     * Inserts the specified element into this queue.
     */
    @Override
    public boolean add(T e) {
        return offer(e);
    }

    /**
     * Removes and returns the head of this queue.
     * Throws NoSuchElementException if empty.
     */
    @Override
    public T remove() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return poll();
    }

    /**
     * Returns the head of this queue without removing it.
     * Throws NoSuchElementException if empty.
     */
    @Override
    public T element() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return peek();
    }
}