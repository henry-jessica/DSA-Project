package trafficmanagement;

import java.util.ArrayList;

public class TrafficRerouteList {
    private final ArrayList<String> rerouteList = new ArrayList<>();

    public void insertFront(String route) {
        rerouteList.addFirst(route); // Insert at the front
        System.out.println("New route added at front: " + route);
    }

    public void deleteRear() {
        if (!rerouteList.isEmpty()) {
            System.out.println("Old route removed: " + rerouteList.removeLast());
        } else {
            System.out.println("No old routes to remove.");
        }
    }
}
