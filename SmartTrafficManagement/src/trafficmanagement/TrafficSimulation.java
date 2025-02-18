package trafficmanagement;

public class TrafficSimulation {
    public static void main(String[] args) {
    	
        System.out.println("===== Normal Traffic Queue Test =====");
        // Normal Traffic Queue Simulation
        TrafficQueue trafficQueue = new TrafficQueue();
        trafficQueue.enqueue("Car1");
        trafficQueue.enqueue("Car2");
        trafficQueue.dequeue(); // Car1 passes

        System.out.println("\n===== Emergency Traffic Queue Test (Ambulance in Middle) =====");
        // Emergency Vehicle Priority Queue Simulation
        EmergencyTrafficQueue emergencyQueue = new EmergencyTrafficQueue();

        // ADD VEHICLES IN RANDOM ORDER TO TEST PRIORITY HANDLING
        emergencyQueue.enqueue("Car A", 1);  // Normal Car (Lowest Priority)
        emergencyQueue.enqueue("Ambulance", 3);  // Ambulance (Highest Priority)
        emergencyQueue.enqueue("Car B", 1);  // Normal Car
        emergencyQueue.enqueue("Car C", 1);  // Normal Car
        emergencyQueue.enqueue("Fire Truck", 2);  // Fire Truck (Medium Priority)

        // Process vehicles - should follow priority order
        emergencyQueue.dequeue(); 
        emergencyQueue.dequeue(); 
        emergencyQueue.dequeue();
        emergencyQueue.dequeue(); 
        emergencyQueue.dequeue();

        System.out.println("\n===== Traffic Rerouting Test =====");
        // Rerouting Example
        TrafficRerouteQueue rerouteSystem = new TrafficRerouteQueue();
        rerouteSystem.insertFront("Route A");
        rerouteSystem.insertFront("Route B");
        rerouteSystem.deleteRear(); // Remove old route (Route A)
        
        
        
        //////////////////////////
        // Normal Traffic Queue Simulation
//      TrafficQueue trafficQueue = new TrafficQueue();
//      trafficQueue.enqueue("Car1");
//      trafficQueue.enqueue("Car2");
//      trafficQueue.dequeue(); // Car1 passes
//
//      // Emergency Vehicle Priority Queue Simulation
//      EmergencyTrafficQueue emergencyQueue = new EmergencyTrafficQueue();
//      emergencyQueue.enqueue("Ambulance", 3); // Highest priority
//      emergencyQueue.enqueue("Fire Truck", 2);
//      emergencyQueue.enqueue("Car", 1);
//      emergencyQueue.dequeue(); // Ambulance passes first
//
//      // Rerouting Example
//      TrafficRerouteQueue rerouteSystem = new TrafficRerouteQueue();
//      rerouteSystem.insertFront("Route A");
//      rerouteSystem.insertFront("Route B");
//      rerouteSystem.deleteRear(); // Remove old route
        
    }
}
