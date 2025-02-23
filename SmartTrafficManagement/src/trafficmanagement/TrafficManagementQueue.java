package trafficmanagement;

import java.util.PriorityQueue;

public class TrafficManagementQueue {
    private PriorityQueue<Vehicle> vehicleQueue;

    public TrafficManagementQueue() {
        this.vehicleQueue = new PriorityQueue<>();
    }

    public void enqueue(String vehicleType, int priority) {
        vehicleQueue.add(new Vehicle(vehicleType, priority));
        System.out.println(vehicleType + " (priority " + priority + ") added to queue.");
    }

    public void dequeue() {
        if (!vehicleQueue.isEmpty()) {
            System.out.println(vehicleQueue.poll() + " passed first due to priority.");
        } else {
            System.out.println("No vehicles in queue.");
        }
    }

    public void changePriority(String vehicleType, int newPriority) {
        for (Vehicle vehicle : vehicleQueue) {
            if (vehicle.type.equals(vehicleType)) {
                vehicleQueue.remove(vehicle);
                vehicleQueue.add(new Vehicle(vehicleType, newPriority));
                System.out.println(vehicleType + " priority changed to " + newPriority);
                break;
            }
        }
    }

    public boolean isEmpty() {
        return vehicleQueue.isEmpty();
    }
}