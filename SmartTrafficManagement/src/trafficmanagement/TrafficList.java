package trafficmanagement;

import java.util.ArrayList;

public class TrafficList {
    private final ArrayList<Vehicle> trafficList = new ArrayList<>();

    public void Enqueue(Vehicle vehicle) {
        trafficList.add(vehicle);
    }

    public Vehicle Dequeue() {
        if (trafficList.isEmpty())
            return null;
        return trafficList.removeFirst();
    }

    public boolean isEmpty() {
        return trafficList.isEmpty();
    }
}
