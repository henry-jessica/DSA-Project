package disruptorqueue;

public class DisruptorQueue<T> {
    private final RingBuffer<T> ringBuffer;
    private final WaitStrategy waitStrategy;

    /**
     * Create a DisruptorQueue with a given size and wait strategy.
     * 
     * @param size         Must be a power of 2.
     * @param waitStrategy Implementation of how consumers wait for new data.
     */
    public DisruptorQueue(int size, WaitStrategy waitStrategy) {
        this.ringBuffer = new RingBuffer<>(size);
        this.waitStrategy = waitStrategy;
    }

    /**
     * Tries to put an item into the queue.
     * 
     * @return true if successful, false if the queue is currently full.
     */
    public boolean offer(T data) {
        return ringBuffer.publish(data);
    }

    /**
     * Non-blocking poll to retrieve an item. Returns null if none is available.
     */
    public T poll() {
        return ringBuffer.consume();
    }

    /**
     * Blocking poll that uses the configured WaitStrategy.
     * Returns the item once it becomes available.
     */
    public T pollWait() {
        T item;
        while ((item = ringBuffer.consume()) == null) {
            waitStrategy.waitFor(ringBuffer);
        }
        return item;
    }
}