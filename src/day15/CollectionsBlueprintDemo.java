package day15;
// Collection (Interface)
//             /      |        \
//            /       |         \
//   List (Interface) Set (Interface) Queue (Interface)
//          |             |               |
//      ArrayList      HashSet        LinkedList
//      LinkedList     TreeSet
//
//   Map (Interface - Independent branch mapping Key -> Value pairs)
//          |
//      HashMap / TreeMap

// Complete Implementation Blueprint
//This comprehensive application maps out the functional characteristics of List (preserves ordering/allows duplicates), Set (enforces uniqueness), Map (manages isolated key-value connections), and Queue (follows strict First-In, First-Out extraction flows).

import java.util.*;


public class CollectionsBlueprintDemo {
    public static void main(String[] args) {

        // ==========================================
        // 1. LIST (Preserves order & allows duplicates)
        // ==========================================
        List<String> taskList = new ArrayList<>();
        taskList.add("Code Review");
        taskList.add("Unit Testing");
        taskList.add("Code Review"); // Duplicate allowed, order preserved

        System.out.println("--- 1. List (ArrayList) ---");
        System.out.println("Tasks List: " + taskList);
        System.out.println("Element at index 1: " + taskList.get(1));


        // ==========================================
        // 2. SET (Enforces strict uniqueness)
        // ==========================================
        Set<String> uniqueTags = new HashSet<>();
        uniqueTags.add("Backend");
        uniqueTags.add("Java");
        uniqueTags.add("Backend"); // Duplicate ignored automatically

        System.out.println("\n--- 2. Set (HashSet) ---");
        System.out.println("Unique Tags Set: " + uniqueTags);
        System.out.println("Contains 'Java'? " + uniqueTags.contains("Java"));


        // ==========================================
        // 3. QUEUE (Strict First-In, First-Out / FIFO)
        // ==========================================
        Queue<String> printQueue = new LinkedList<>();
        printQueue.offer("Job_1.pdf");
        printQueue.offer("Job_2.pdf");

        System.out.println("\n--- 3. Queue (LinkedList / FIFO) ---");
        System.out.println("Next item to extract (poll): " + printQueue.poll());
        // Output: Job_1.pdf (First in, first out)
        System.out.println("Remaining queue state: " + printQueue);


        // ==========================================
        // 4. MAP (Independent branch: Key -> Value mapping)
        // ==========================================
        Map<String, String> userSessions = new HashMap<>();
        userSessions.put("usr_001", "Active");
        userSessions.put("usr_002", "Idle");
        userSessions.put("usr_001", "Away"); // Overwrites the value for key "usr_001"

        System.out.println("\n--- 4. Map (HashMap) ---");
        System.out.println("Session status for usr_001: " + userSessions.get("usr_001"));
        System.out.println("All registered keys: " + userSessions.keySet());
    }
}




