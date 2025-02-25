package disruptorqueue;

class Consumer<T> implements Runnable {
    private final DisruptorQueue<T> disruptorQueue;
    private final int numItems;

    public Consumer(DisruptorQueue<T> disruptorQueue, int numItems) {
        this.disruptorQueue = disruptorQueue;
        this.numItems = numItems;
    }

    @Override
    public void run() {
        for (int i = 0; i < numItems; i++) {
            T data;
            while ((data = disruptorQueue.poll()) == null) {
                System.out.println("Consumer waiting");
            }
            System.out.println("Consumed: " + data);
        }
    }
}