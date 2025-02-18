package trafficmanagement;

public class Vehicle implements Comparable<Vehicle> {
    String type;
    int priority; // Higher priority for emergency vehicles

    public Vehicle(String type) {
        this(type, 0);
    }

    public Vehicle(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    @Override
    public int compareTo(Vehicle other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }

    @Override
    public String toString() {
        return type;
    }
}