package trafficmanagement;

import java.util.LinkedList;
import java.util.Queue;

public class TrafficQueue {
    private Queue<String> trafficQueue;

    public TrafficQueue() {
        this.trafficQueue = new LinkedList<>();
    }

    public void enqueue(String vehicle) {
        trafficQueue.add(vehicle);
        System.out.println(vehicle + " added to the queue.");
    }

    public void dequeue() {
        if (!trafficQueue.isEmpty()) {
            System.out.println(trafficQueue.poll() + " passed the intersection.");
        } else {
            System.out.println("No vehicles in queue.");
        }
    }

    public boolean isEmpty() {
        return trafficQueue.isEmpty();
    }
}



