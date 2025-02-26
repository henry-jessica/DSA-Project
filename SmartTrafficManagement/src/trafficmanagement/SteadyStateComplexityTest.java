package trafficmanagement;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Tests the performance of the two queue implementations.
 * This test populates each data structure first, then measures the time taken
 * to add or remove an item from the populated structure. It then returns the
 * structure to its previously populated state. It repeats this process many
 * times and prints the average time taken for each population size to the
 * terminal.
 */
public class SteadyStateComplexityTest {
    // These two constants specify a range in which the priorities can fall
    static final int MIN_PRIORITY = 1;
    static final int MAX_PRIORITY = 100000;

    // Number of times to run each trial
    static final int NUM_TRIALS = 10000;

    // Number of trials discarded to warm up JVM
    static final int WARMUP_TRIALS = 1000;

    // This number grows as the test tries larger input sizes
    static int numInputs;

    // Starting and ending conditions
    static final int INIT_INPUTS = 16;
    static final int MAX_INPUTS = 1 << 20;
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
        // Test binary heap insertion
        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            // Make sure timing does not include vehicle object creation
            Vehicle v = new Vehicle("Test", randInt());

            // Always restrict timing to only the method being tested
            startTime = System.nanoTime();
            myPriorityQueue.offer(v);
            endTime = System.nanoTime();

            totalTime += (endTime - startTime);

            // remove the inserted element so n items stays constant
            myPriorityQueue.poll();
        }
        System.out.print((totalTime / NUM_TRIALS) + ", ");

        // test naive insertion
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
        // Test binary heap polling
        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            startTime = System.nanoTime();
            myPriorityQueue.poll();
            endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            myPriorityQueue.offer(new Vehicle("Test", randInt())); // Keep structure size constant
        }
        System.out.print((totalTime / NUM_TRIALS) + ", ");

        // Test naive polling
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

    /**
     * Randomizes the vehicles, giving each a unique name
     */
    private static void randomizeVehicles() {
        for (int i = 0; i < numInputs; i++) {
            vehicles[i] = new Vehicle(Integer.toString(i), randInt());
        }
    }

    /**
     * Returns a random integer between the two specified constants
     */
    private static int randInt() {
        return ThreadLocalRandom.current().nextInt(MIN_PRIORITY, MAX_PRIORITY);
    }
}