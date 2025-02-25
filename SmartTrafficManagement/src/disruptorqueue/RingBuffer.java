package disruptorqueue;

import java.util.concurrent.atomic.AtomicLong;

public class RingBuffer<T> {

    private final Event<T>[] buffer;
    private final int size;
    private final AtomicLong producerIndex = new AtomicLong(0);
    private final AtomicLong consumerIndex = new AtomicLong(0);

    @SuppressWarnings("unchecked")
    public RingBuffer(int size) {
        this.size = size;
        this.buffer = (Event<T>[]) new Event[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = new Event<>();
        }
    }

    public boolean publish(T data) {
        long next;
        do {
            next = producerIndex.get();

            // If there's no room (producerIndex - consumerIndex == size),
            // wait for consumer to catch up.
            // "Thread.onSpinWait()" is a hint for the JVM
            // and "Thread.yield()" gives up the CPU if needed.
            long wrapPoint = next - size;
            if (consumerIndex.get() <= wrapPoint) {
                Thread.onSpinWait();
                Thread.yield();
            }
        } while (!producerIndex.compareAndSet(next, next + 1));

        // Write data into the claimed slot
        int slot = (int) (next % size);
        buffer[slot].set(data);

        return true;
    }

    public T consume() {
        long current;
        do {
            current = consumerIndex.get();
            if (current >= producerIndex.get()) {
                // Buffer is empty
                return null;
            }
        } while (!consumerIndex.compareAndSet(current, current + 1));

        // Read data from the correct slot
        int slot = (int) (current % size);
        T data = buffer[slot].get();

        // Clear the slot after reading
        buffer[slot].set(null);

        return data;
    }

    public boolean hasValue() {
        return consumerIndex.get() < producerIndex.get();
    }

    // Trivial Event holder class; data is volatile for safe cross-thread
    // visibility.
    private static class Event<E> {
        private volatile E data;

        public E get() {
            return data;
        }

        public void set(E data) {
            this.data = data;
        }
    }
}