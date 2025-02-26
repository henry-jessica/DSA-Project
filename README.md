# DSA-Project

## Traffic Management System - 2025

## Overview

This project presents a **Smart Traffic Management System** aimed at addressing urban traffic congestion using efficient data structures. The system utilises **queue** and **list** data structures to optimise vehicle flow, prioritise emergency vehicles, and adapt dynamically to real-time traffic conditions. 

The system compares two priority queue implementations:

- **MyPriorityQueue**: A min-heap-based priority queue offering efficient insertion and polling.
- **NaivePriorityQueue**: A simpler implementation using linear search to find the smallest element on polling.

## Features

- Dynamic traffic flow control using FIFO queues.
- Emergency vehicle prioritisation through custom priority queues.
- Performance evaluation comparing insertion and polling times.
- Scalability testing with exponentially growing input sizes.

## Project Structure

```
├── ComplexityTest.java                # Performance comparison test
├── SteadyStateComplexityTest.java     # Steady-state performance benchmark
├── MyPriorityQueue.java               # Heap-based efficient priority queue
├── NaivePriorityQueue.java            # Linear search-based priority queue
└── Vehicle.java                       # Vehicle class with priority attribute
```

## Data Structures Used

### Queue
- **Types Implemented:**
  - Simple Queue
  - Priority Queue (min-heap and naive implementations)
- **Applications:**
  - Managing vehicle processing at intersections
  - Prioritising emergency vehicles

### List
- **Types Discussed:**
  - ArrayList
  - LinkedList
- **Applications:**
  - Storing and updating vehicle records
  - Managing dynamic traffic routes

## How to Run the Project

### Prerequisites
- **Java Development Kit (JDK):** Version 8 or higher
- **IDE:** Eclipse IDE

### Steps

1. Clone the repository:
   ```bash
   git clone <GitHub_Repository_Link>
   ```

2. Navigate to the project directory:
   ```bash
   cd TrafficManagementSystem2025
   ```

3. Import the project into Eclipse.
4. Run `ComplexityTest.java` for performance comparisons.
5. Run `SteadyStateComplexityTest.java` for steady-state performance measurements.

### Output Format

```
Size, MyPQ_Insert(ns), NaivePQ_Insert(ns), MyPQ_Poll(ns), NaivePQ_Poll(ns)
```

## Performance Analysis

### MyPriorityQueue
- Insertion (offer): O(log n)
- Poll (dequeue): O(log n)

### NaivePriorityQueue
- Insertion: O(1)
- Poll: O(n)

### Observations
- `MyPriorityQueue` shows superior performance during polling operations.
- `NaivePriorityQueue` performs well only for small datasets due to its linear polling time.

## Experiment Results

- `MyPriorityQueue` scales efficiently with large datasets, making it ideal for real-time traffic management.
- The steady-state tests show consistent performance after JVM optimisations.

## Future Enhancements

- Adding functionality to update vehicle priorities within the queue.
- Integrating real-time traffic sensors for adaptive signal control.
- Implementing multi-threading for parallel processing at multiple intersections.

## References

1. [Economic and Environmental Impact of Traffic Congestion](https://mej.researchcommons.org/home/vol49/iss2/18/)
2. [Health Risks Associated with Traffic-Related Air Pollution](https://ehjournal.biomedcentral.com/articles/10.1186/1476-069X-9-65)
3. [Coping with Large Traffic Volumes in Schedule-Driven Traffic Signal Control](https://arxiv.org/abs/1903.04278)
4. [Understanding Queue Applications in Data Structures](https://www.fynd.academy/blog/application-of-queue-in-data-structure)

## Acknowledgements

Developed as part of the MSc Computer Science (Negotiated Learning) programme at **University College Dublin**, 2025.

---

*For detailed methodology, UML diagrams, and experimental analysis, refer to the main report: "Smart Traffic Management Using Queue and List Data Structures: Efficient Solutions for Traffic Congestion and Flow Control".*




Report Link: [https://docs.google.com/document/d/1FHdM_Ersfjf8ydAhMiCeetbo8N7zDycu/edit?usp=sharing&ouid=108236669906689076910&rtpof=true&sd=true](https://ucd-my.sharepoint.com/:w:/g/personal/jessica_henry_ucdconnect_ie/ESZnhpCppWJJj24G3mCO6GEBaVaZHGes1YMZmA1zlLacsQ)

