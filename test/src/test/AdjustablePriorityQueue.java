package test;

//SmartTrafficManagement.java
//Enhanced smart traffic management system using PriorityQueue with custom priority adjustment functionality

import java.util.*;

//Custom PriorityQueue with priority adjustment capability
class AdjustablePriorityQueue<T> {
 private PriorityQueue<Vehicle> queue;

 static class Vehicle implements Comparable<Vehicle> {
     String id;
     int priority;

     public Vehicle(String id, int priority) {
         this.id = id;
         this.priority = priority;
     }

     @Override
     public int compareTo(Vehicle other) {
         return Integer.compare(other.priority, this.priority); // Higher priority first
     }

     @Override
     public String toString() {
         return id + "(Priority: " + priority + ")";
     }
 }

 public AdjustablePriorityQueue() {
     queue = new PriorityQueue<>();
 }

 public void enqueue(String id, int priority) {
     queue.offer(new Vehicle(id, priority));
 }

 public Vehicle dequeue() {
     return queue.poll();
 }

 public void changePriority(String id, int newPriority) {
     List<Vehicle> tempList = new ArrayList<>();
     Vehicle target = null;
     while (!queue.isEmpty()) {
         Vehicle v = queue.poll();
         if (v.id.equals(id)) {
             v.priority = newPriority;
             target = v;
         }
         tempList.add(v);
     }
     if (target == null) {
         System.out.println("Vehicle " + id + " not found in queue.");
     }
     queue.addAll(tempList);
 }

 public boolean isEmpty() {
     return queue.isEmpty();
 }

 @Override
 public String toString() {
     return queue.toString();
 }
}


