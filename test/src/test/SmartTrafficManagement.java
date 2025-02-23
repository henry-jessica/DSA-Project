package test;

public class SmartTrafficManagement {
	 private AdjustablePriorityQueue trafficQueue;

	 public SmartTrafficManagement() {
	     trafficQueue = new AdjustablePriorityQueue<>();
	 }

	 public void addVehicle(String id, int priority) {
	     trafficQueue.enqueue(id, priority);
	     System.out.println("Queue after adding " + id + ": " + trafficQueue);
	 }

	 public void processVehicles() {
	     while (!trafficQueue.isEmpty()) {
	         System.out.println("Processing: " + trafficQueue.dequeue());
	         System.out.println("Current Queue: " + trafficQueue);
	     }
	 }

	 public void updateVehiclePriority(String id, int newPriority) {
	     trafficQueue.changePriority(id, newPriority);
	     System.out.println("Queue after priority update for " + id + ": " + trafficQueue);
	 }

	 public static void main(String[] args) {
	     SmartTrafficManagement manager = new SmartTrafficManagement();

	     manager.addVehicle("Ambulance", 5);
	     manager.addVehicle("Bus", 2);
	     manager.addVehicle("Taxi", 3);
	     manager.addVehicle("Fire Truck", 4);

	     manager.updateVehiclePriority("Bus", 6);
	     manager.updateVehiclePriority("Taxi", 1);

	     System.out.println("\n--- Processing Vehicles by Priority ---");
	     manager.processVehicles();
	 }
	}