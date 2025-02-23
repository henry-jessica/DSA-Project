package trafficmanagement;

public class Vehicle implements Comparable<Vehicle> {
    String type;
    int priority; // Higher priority for emergency vehicles
    long timestamp; // To track insertion order

    public Vehicle(String type) {
        this(type, 0);
    }

    public Vehicle(String type, int priority) {
        this.type = type;
        this.priority = priority;
        this.timestamp = System.nanoTime(); // Use nanoTime to ensure unique timestamps
    }

    @Override
    public int compareTo(Vehicle other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority); // Higher priority first
        }
        return Long.compare(this.timestamp, other.timestamp); // Earlier insertion first
    }

    @Override
    public String toString() {
        return type;
    }
}