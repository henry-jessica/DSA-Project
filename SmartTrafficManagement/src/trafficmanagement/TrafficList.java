package trafficmanagement;

import java.util.ArrayList;

public class TrafficList {
    private final ArrayList<Vehicle> trafficList = new ArrayList<>();

    public void enqueue(String vehicleName) {
        enqueue(new Vehicle(vehicleName, 0));
    }

    public void enqueue(Vehicle vehicle) {
        trafficList.add(vehicle);
        System.out.println(vehicle + " added to the queue.");
    }

    public Vehicle dequeue() {
        if (trafficList.isEmpty()) {
            System.out.println("No vehicles in queue.");
            return null;
        }
        System.out.println(trafficList.getFirst() + " passed the intersection.");
        return trafficList.removeFirst();
    }

    public boolean isEmpty() {
        return trafficList.isEmpty();
    }
}
