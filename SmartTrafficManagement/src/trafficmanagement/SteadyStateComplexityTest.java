package trafficmanagement;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class SteadyStateComplexityTest {
    static final int MIN_PRIORITY = 1;
    static final int MAX_PRIORITY = 100000;
    static final int NUM_TRIALS = 10000; // More trials for stable measurements
    static final int WARMUP_TRIALS = 1000; // JVM warm-up iterations

    static int numInputs;
    static final int INIT_INPUTS = 16;
    static final int MAX_INPUTS = 1 << 30; // Avoid excessive memory use
    static final int GROWTH_FACTOR = 2;

    static Vehicle[] vehicles;

    public static void main(String[] args) {
        System.out.println("Size, MyPQ_Insert(ns), NaivePQ_Insert(ns), MyPQ_Poll(ns), NaivePQ_Poll(ns)");

        for (numInputs = INIT_INPUTS; numInputs <= MAX_INPUTS; numInputs *= GROWTH_FACTOR) {
            complexityTest();
        }
    }

    private static void complexityTest() {
        long startTime, endTime, totalTime;
        Queue<Vehicle> myPriorityQueue = new MyPriorityQueue<>();
        Queue<Vehicle> naivePriorityQueue = new NaivePriorityQueue<>();

        System.out.print(numInputs + ", ");
        vehicles = new Vehicle[numInputs];

        /** STEP 1: Fill Data Structure to Steady-State */
        randomizeVehicles();
        for (int i = 0; i < numInputs; i++) {
            myPriorityQueue.offer(vehicles[i]);
            naivePriorityQueue.offer(vehicles[i]);
        }

        /** STEP 2: Warm-Up Runs (JVM Optimization) */
        for (int j = 0; j < WARMUP_TRIALS; j++) {
            myPriorityQueue.poll();
            myPriorityQueue.offer(new Vehicle("WARMUP", randInt()));

            naivePriorityQueue.poll();
            naivePriorityQueue.offer(new Vehicle("WARMUP", randInt()));
        }

        /** STEP 3: Measure Inserts at Steady-State */
        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            Vehicle v = new Vehicle("Test", randInt());
            startTime = System.nanoTime();
            myPriorityQueue.offer(v);
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            myPriorityQueue.poll(); // Keep structure size constant
        }
        System.out.print((totalTime / NUM_TRIALS) + ", ");

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            Vehicle v = new Vehicle("Test", randInt());
            startTime = System.nanoTime();
            naivePriorityQueue.offer(v);
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            naivePriorityQueue.poll(); // Keep structure size constant
        }
        System.out.print((totalTime / NUM_TRIALS) + ", ");

        /** STEP 4: Measure Poll (Dequeue) at Steady-State */
        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            startTime = System.nanoTime();
            myPriorityQueue.poll();
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            myPriorityQueue.offer(new Vehicle("Test", randInt())); // Keep structure size constant
        }
        System.out.print((totalTime / NUM_TRIALS) + ", ");

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            startTime = System.nanoTime();
            naivePriorityQueue.poll();
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            naivePriorityQueue.offer(new Vehicle("Test", randInt())); // Keep structure size constant
        }
        System.out.println(totalTime / NUM_TRIALS);
    }

    /** Ensures consistent randomized data */
    private static void randomizeVehicles() {
        for (int i = 0; i < numInputs; i++) {
            vehicles[i] = new Vehicle(Integer.toString(i), randInt());
        }
    }

    /** Generates a random priority */
    private static int randInt() {
        return ThreadLocalRandom.current().nextInt(MIN_PRIORITY, MAX_PRIORITY);
    }
}