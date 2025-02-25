package disruptorqueue;

public interface WaitStrategy {
    public void waitFor(RingBuffer<?> buffer);
}
