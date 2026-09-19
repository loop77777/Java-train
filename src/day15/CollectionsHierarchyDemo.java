package day15;//6. The Collections Framework Hierarchy
//The Java Collections Framework organizes groups of objects into specific behavioral architectures.

//The Java Collections Framework (JCF) provides a unified architecture for storing and manipulating groups of objects. It is organized into core interfaces and concrete implementations designed for specific performance and behavioral characteristics.
//
//The Core Hierarchy
//The framework splits into two primary roots: the Collection interface (for single elements) and the Map interface (for key-value pairs).

//Iterable
// └── Collection
//      ├── List (Ordered, allows duplicates)
//      │    ├── ArrayList
//      │    ├── LinkedList
//      │    └── Vector
//      ├── Set (Unique elements, no duplicates)
//      │    ├── HashSet
//      │    ├── LinkedHashSet
//      │    └── TreeSet
//      └── Queue (FIFO / Processing order)
//           ├── PriorityQueue
//           └── ArrayDeque
//
//Map (Key-Value pairs, separate from Collection)
// ├── HashMap
// ├── LinkedHashMap
// └── TreeMap

//The Java Collections Framework (JCF) is a unified architecture designed to store and manipulate groups of objects efficiently. At its root, it separates collections into two distinct trees: the Collection interface and the Map interface (which operates outside the direct Collection hierarchy due to its key-value pairing design).
//
//The Core Behavioral Interfaces
//The framework organizes data structures based on how items are accessed, ordered, and stored:
//
//List: An ordered collection (sequence) that allows duplicate elements and provides precise control over where items are inserted (ArrayList, LinkedList).
//
//Set: A collection that contains no duplicate elements, modeling the mathematical set abstraction (HashSet, LinkedHashSet, TreeSet).
//
//Queue / Deque: Designed for holding elements prior to processing, typically following First-In-First-Out (FIFO) or Last-In-First-Out (LIFO) rules (PriorityQueue, ArrayDeque).
//
//Map: An object that maps keys to unique values; keys cannot have duplicates, though values can (HashMap, TreeMap, LinkedHashMap).

import java.util.*;

public class CollectionsHierarchyDemo {
    public static void main(String[] args) {

        // 1. LIST: Ordered and allows duplicates
        List<String> namesList = new ArrayList<>();
        namesList.add("Alice");
        namesList.add("Bob");
        namesList.add("Alice"); // Duplicate allowed
        System.out.println("List (Ordered/Duplicates): " + namesList);

        // 2. SET: Unordered and enforces uniqueness
        Set<String> uniqueNames = new HashSet<>(namesList);
        System.out.println("Set (Unique only): " + uniqueNames);
        // Output automatically strips the duplicate "Alice"

        // 3. MAP: Key-Value association
        Map<Integer, String> userMap = new HashMap<>();
        userMap.put(101, "Alice");
        userMap.put(102, "Bob");
        System.out.println("Map (Key-Value): ID 101 belongs to " + userMap.get(101));
    }
}



