package trafficmanagement;

// Implementing a priority queue for emergency vehicles
import java.util.PriorityQueue;

class Vehicle implements Comparable<Vehicle> {
    String type;
    int priority; // Higher priority for emergency vehicles

    public Vehicle(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    @Override
    public int compareTo(Vehicle other) {
        return other.priority - this.priority; // Higher priority first
    }

    @Override
    public String toString() {
        return type;
    }
}

public class EmergencyTrafficQueue {
    private PriorityQueue<Vehicle> priorityQueue;

    public EmergencyTrafficQueue() {
        this.priorityQueue = new PriorityQueue<>();
    }

    public void enqueue(String vehicleType, int priority) {
        priorityQueue.add(new Vehicle(vehicleType, priority));
        System.out.println(vehicleType + " (priority " + priority + ") added to queue.");
    }

    public void dequeue() {
        if (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll() + " passed first due to priority.");
        } else {
            System.out.println("No vehicles in queue.");
        }
    }
}
