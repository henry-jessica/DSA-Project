package trafficmanagement;

public class EmergencyTrafficQueue {
    private final MyPriorityQueue<Vehicle> priorityQueue = new MyPriorityQueue<>();

    public void enqueue(String vehicleType, int priority) {
        priorityQueue.offer(new Vehicle(vehicleType, priority));
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
