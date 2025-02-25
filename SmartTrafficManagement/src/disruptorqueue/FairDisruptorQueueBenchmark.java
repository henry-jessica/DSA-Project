package disruptorqueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class FairDisruptorQueueBenchmark {
    public static void main(String[] args) throws InterruptedException {
        int stride = 20;
        int totalMessages = 4 * stride;
        int numTrials = 1;

        // warmup runs
        benchmarkQueue(stride, totalMessages, numTrials);

        benchmarkQueue(stride, totalMessages, numTrials);
        benchmarkDisruptorQueue(stride, totalMessages, numTrials);
    }

    private static void benchmarkQueue(int stride, int totalMessages, int numTrials) {
        long startTime, endTime, totalTime;

        totalTime = 0;
        for (int i = 0; i < numTrials; i++) {
            // ========== FAIR BENCHMARK FOR CONCURRENT LINKED QUEUE ==========
            Queue<String> queue = new ConcurrentLinkedQueue<>();
            AtomicInteger queueConsumedCount = new AtomicInteger(0);

            startTime = System.nanoTime();

            new Thread(() -> produceMessages(queue, 0, stride)).start();
            new Thread(() -> produceMessages(queue, stride, stride)).start();
            new Thread(() -> produceMessages(queue, 2 * stride, stride)).start();
            new Thread(() -> produceMessages(queue, 3 * stride, stride)).start();
            new Thread(() -> produceMessages(queue, 4 * stride, stride)).start();

            new Thread(() -> consumeMessages(queue, queueConsumedCount,
                    totalMessages)).start();

            while (queueConsumedCount.get() < totalMessages) {
                Thread.yield();
            }

            endTime = System.nanoTime();
            totalTime += endTime - startTime;
            // System.out.println("ConcurrentLinkedQueue finished in " + (endTime -
            // startTime) / 1_000_000.0 + " ms");
        }
        System.out.println(totalTime / numTrials);
    }

    private static void benchmarkDisruptorQueue(int stride, int totalMessages, int numTrials) {
        long startTime;
        long endTime;
        long totalTime;
        totalTime = 0;
        for (int i = 0; i < numTrials; i++) {
            // ========== FAIR BENCHMARK FOR DISRUPTOR ==========
            DisruptorQueue<String> disruptorQueue = new DisruptorQueue<>(64, new YieldWaitStrategy());
            AtomicInteger disruptorConsumedCount = new AtomicInteger(0);

            startTime = System.nanoTime();

            // Use the same number of producers and consumers
            new Thread(() -> produceMessages(disruptorQueue, 0, stride)).start();
            new Thread(() -> produceMessages(disruptorQueue, stride, stride)).start();
            new Thread(() -> produceMessages(disruptorQueue, 2 * stride, stride)).start();
            new Thread(() -> produceMessages(disruptorQueue, 3 * stride, stride)).start();
            new Thread(() -> produceMessages(disruptorQueue, 4 * stride, stride)).start();

            new Thread(() -> consumeMessages(disruptorQueue, disruptorConsumedCount,
                    totalMessages)).start();

            // Wait for completion
            while (disruptorConsumedCount.get() < totalMessages) {
                Thread.yield();
            }

            endTime = System.nanoTime();
            totalTime += endTime - startTime;
            // System.out.println("Disruptor queue finished in " + (endTime - startTime)
            // 1_000_000.0 + " ms");
        }
        System.out.println(totalTime / numTrials);
    }

    private static void produceMessages(Queue<String> queue, int start, int count) {
        for (int i = 0; i < count; i++) {
            queue.offer("Message " + (start + i));
        }
    }

    private static void produceMessages(DisruptorQueue<String> queue, int start, int count) {
        for (int i = 0; i < count; i++) {
            queue.offer("Message " + (start + i));
        }
    }

    private static void consumeMessages(Queue<String> queue, AtomicInteger consumedCount, int totalMessages) {
        String message;
        while (consumedCount.get() < totalMessages) {
            if ((message = queue.poll()) != null) {
                consumedCount.getAndIncrement();
            } else {
                Thread.yield();
                // System.out.println("consumer waiting");
            }
        }
    }

    private static void consumeMessages(DisruptorQueue<String> queue, AtomicInteger consumedCount, int totalMessages) {
        String message;
        while (consumedCount.get() < totalMessages) {
            if ((message = queue.poll()) != null) {
                consumedCount.getAndIncrement();
            } else {
                Thread.yield();
                // System.out.println("Disruptor consumer waiting - " + consumedCount.get());
            }
        }
    }
}