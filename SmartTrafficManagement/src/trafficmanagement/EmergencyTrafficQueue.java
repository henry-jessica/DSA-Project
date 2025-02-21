package trafficmanagement;

// here we are implementing the priority queue

import java.util.PriorityQueue;

public class EmergencyTrafficQueue {
    private PriorityQueue<Vehicle> priorityQueue;

    public EmergencyTrafficQueue() {
        this.priorityQueue = new PriorityQueue<>();
    }
    //Determining the priority of the vehicle and the type
    public void enqueue(String vehicleType, int priority) {
        priorityQueue.add(new Vehicle(vehicleType, priority));
        System.out.println(vehicleType + " (priority " + priority + ") added to queue.");
    }
    
    //Opitmize
    public void dequeue() {
        if (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll() + " passed first due to priority.");
//        	System.out.println(priorityQueue.poll());
        } else {
            System.out.println("No vehicles in queue.");
        }
    }
}
