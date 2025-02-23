package trafficmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrafficSimulation {
    public static void main(String[] args) {
        TrafficManagementQueue queueSystem = new TrafficManagementQueue();
        TrafficManagementList listSystem = new TrafficManagementList();

        String filePath = "src/trafficmanagement/vehicle.txt";

        // Read vehicles from file and enqueue them
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String vehicleType = parts[0];
                int priority = Integer.parseInt(parts[1]);
                queueSystem.enqueue(vehicleType, priority);
                listSystem.enqueue(vehicleType, priority);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test Queue System
        System.out.println("===== Queue System Test =====");
        queueSystem.changePriority("Car2", 4); // Change Car2 priority to 4 just for test 
        while (!queueSystem.isEmpty()) {
            queueSystem.dequeue();
        }

        // Test List System
        System.out.println("\n===== List System Test =====");
        listSystem.changePriority("Car2", 4); // Change Car2 priority to 4 just for test 
        while (!listSystem.isEmpty()) {
            listSystem.dequeue();
        }
    }
}