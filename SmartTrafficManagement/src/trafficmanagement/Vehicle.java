package trafficmanagement;

/**
 * Represents a vehicle with a type and priority level.
 * 
 * Vehicles can be compared based on priority, with higher-priority vehicles
 * (such as emergency vehicles) taking precedence over lower-priority ones.
 * Sorting and comparisons are done in descending order of priority
 * (higher-priority vehicles come first).
 * 
 */
public class Vehicle implements Comparable<Vehicle> {
    /* The type of vehicle e.g "Car", "Ambulance", "Fire Truck" */
    String type;

    /* Identifier used for sorting. Higher priorities come first. */
    int priority;

    /**
     * Constructs a vehicle with the given type and default priority level (0)
     * 
     * @param type represents the type of vehicle.
     */
    public Vehicle(String type) {
        this(type, 0);
    }

    /**
     * Constructs a vehicle with the given type and priority level
     * 
     * @param type     represents the type of vehicle.
     * @param priority the priority level of the vehicle.
     */
    public Vehicle(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    /**
     * Compares this vehicle with another based on priority. Higher values are
     * considered "less than" lower priority vehicles so they come first when
     * sorting, in priority queues etc.
     */
    @Override
    public int compareTo(Vehicle other) {
        return Integer.compare(other.priority, this.priority);
    }

    /**
     * Returns a string representation of the vehicle
     * 
     * @return the vehicle type as a string.
     */
    @Override
    public String toString() {
        return type;
    }
}