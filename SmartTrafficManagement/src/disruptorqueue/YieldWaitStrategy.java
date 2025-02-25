package disruptorqueue;

public class YieldWaitStrategy implements WaitStrategy {
    @Override
    public void waitFor(RingBuffer<?> buffer) {
        while (!buffer.hasValue()) {
            Thread.yield();
        }
    }
}
