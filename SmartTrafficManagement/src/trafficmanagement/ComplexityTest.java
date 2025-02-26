package trafficmanagement;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Tests the performance of the two queue implementations and prints the results
 * to the terminal.
 * This test fills each of the structures from empty many times, then prints the
 * average time taken. It performs this process for many different input sizes.
 * The range of input sizes can be specified by changing the constants.
 */
public class ComplexityTest {
    static final int MIN_PRIORITY = 1;
    static final int MAX_PRIORITY = 5;
    static final int NUM_TRIALS = 10;

    static int numInputs = 10;
    static final int INIT_INPUTS = 16;
    static final int MAX_INPUTS = 1 << 30;
    static final int GROWTH_FACTOR = 2;

    static Vehicle[] vehicles;

    public static void main(String[] args) {
        for (numInputs = INIT_INPUTS; numInputs <= MAX_INPUTS; numInputs *= GROWTH_FACTOR)
            complexityTest();
    }

    private static void complexityTest() {
        long startTime, endTime, totalTime;
        Queue<Vehicle> queue1 = new MyPriorityQueue<>();
        Queue<Vehicle> queue2 = new NaivePriorityQueue<>();

        System.out.print(numInputs + ", ");
        vehicles = new Vehicle[numInputs];

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            randomizeVehicles();
            startTime = System.nanoTime();
            for (int i = 0; i < numInputs; i++) {
                queue1.add(vehicles[i]);
            }
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        System.out.print(totalTime / NUM_TRIALS + ", ");

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            randomizeVehicles();
            startTime = System.nanoTime();
            for (int i = 0; i < numInputs; i++) {
                queue2.add(vehicles[i]);
            }
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        System.out.print(totalTime / NUM_TRIALS + ", ");

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            startTime = System.nanoTime();
            for (int i = 0; i < numInputs; i++) {
                queue1.poll();
            }
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        System.out.print(totalTime / NUM_TRIALS + ", ");

        totalTime = 0;
        for (int j = 0; j < NUM_TRIALS; j++) {
            startTime = System.nanoTime();
            for (int i = 0; i < numInputs; i++) {
                queue2.poll();
            }
            endTime = System.nanoTime();
            totalTime += endTime - startTime;
        }
        System.out.println(totalTime / NUM_TRIALS);
    }

    private static void randomizeVehicles() {
        for (int i = 0; i < numInputs; i++) {
            vehicles[i] = new Vehicle(Integer.toString(i), randInt());
        }
    }

    private static int randInt() {
        return ThreadLocalRandom.current().nextInt(MIN_PRIORITY, MAX_PRIORITY);
    }

}
