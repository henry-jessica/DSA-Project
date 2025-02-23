package trafficmanagement;

import java.util.ArrayList;
import java.util.Collections;

public class TrafficManagementList {
    private ArrayList<Vehicle> vehicleList;

    public TrafficManagementList() {
        this.vehicleList = new ArrayList<>();
    }

    public void enqueue(String vehicleType, int priority) {
        vehicleList.add(new Vehicle(vehicleType, priority));
        Collections.sort(vehicleList);
        System.out.println(vehicleType + " (priority " + priority + ") added to list.");
    }

    public void dequeue() {
        if (!vehicleList.isEmpty()) {
            System.out.println(vehicleList.remove(0) + " passed first due to priority.");
        } else {
            System.out.println("No vehicles in list.");
        }
    }

    public void changePriority(String vehicleType, int newPriority) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.type.equals(vehicleType)) {
                vehicleList.remove(vehicle);
                vehicleList.add(new Vehicle(vehicleType, newPriority));
                Collections.sort(vehicleList);
                System.out.println(vehicleType + " priority changed to " + newPriority);
                break;
            }
        }
    }

    public boolean isEmpty() {
        return vehicleList.isEmpty();
    }
}