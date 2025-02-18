package trafficmanagement;

import java.util.ArrayList;
import java.util.Collections;

public class EmergencyTrafficList {
    private final ArrayList<Vehicle> vehicleList;

    public EmergencyTrafficList() {
        this.vehicleList = new ArrayList<>();
    }

    public void enqueue(String vehicleType, int priority) {
        vehicleList.add(new Vehicle(vehicleType, priority));
        Collections.sort(vehicleList);
        System.out.println(vehicleType + " (priority " + priority + ") added to queue.");
    }

    public void dequeue() {
        if (!vehicleList.isEmpty()) {
            System.out.println(vehicleList.remove(0) + " passed first due to priority.");
        } else {
            System.out.println("No vehicles in queue.");
        }
    }
}