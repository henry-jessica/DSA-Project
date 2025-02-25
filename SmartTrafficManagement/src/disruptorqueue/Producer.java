package disruptorqueue;

public class Producer<T> implements Runnable {
    private final DisruptorQueue<T> disruptorQueue;
    private final int numItems;
    private final int messageStart;

    public Producer(DisruptorQueue<T> disruptorQueue, int messageStart, int numItems) {
        this.disruptorQueue = disruptorQueue;
        this.messageStart = messageStart;
        this.numItems = numItems;
    }

    @Override
    public void run() {
        for (int i = 0; i < numItems; i++) {
            while (!disruptorQueue.offer((T) ("Message " + (messageStart + i)))) {
                // System.out.println("Producer Waiting!");
                Thread.yield();
            }
            // System.out.println("Produced: Message " + i);
        }
    }
}
