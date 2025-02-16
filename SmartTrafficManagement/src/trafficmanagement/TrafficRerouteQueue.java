package trafficmanagement;
// Implement a Deque (Double-Ended Queue) for dynamic traffic rerouting.

import java.util.Deque;
import java.util.LinkedList;

public class TrafficRerouteQueue {
    private Deque<String> rerouteQueue;

    public TrafficRerouteQueue() {
        this.rerouteQueue = new LinkedList<>();
    }

    public void insertFront(String route) {
        rerouteQueue.addFirst(route);
        System.out.println("New route added at front: " + route);
    }

    public void deleteRear() {
        if (!rerouteQueue.isEmpty()) {
            System.out.println("Old route removed: " + rerouteQueue.removeLast());
        } else {
            System.out.println("No old routes to remove.");
        }
    }
}
