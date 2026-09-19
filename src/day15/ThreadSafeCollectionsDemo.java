package day15;

//14. Synchronized Concurrency: Vector vs. Modern Alternatives
//Both ArrayList and Vector manage their underlying elements within a dynamically resized array layout. However, their thread safety configurations differ fundamentally:
//•	Vector: Every structural read, write, and index modification method contains the synchronized keyword modifier. While this prevents element corruption from concurrent modifications, it incurs massive runtime overhead because it forces single-threaded lock acquisitions on the vector instance even when no multi-threaded resource sharing is occurring.
//•	ArrayList: Unsynchronized, lightweight, and very fast under normal single-threaded use. If multi-threaded resource access is required, you wrap it using explicit block locks or modern concurrency utilities.

//Understanding the Concurrency Trade-Offs
//Vector (The Legacy Approach): Every single method (add, get, remove, size) is marked with the synchronized keyword. This forces threads to acquire an intrinsic monitor lock on the object wrapper for every individual operation. While it prevents data corruption, it creates massive thread contention bottlenecks and runtime overhead, even in single-threaded contexts.
//
//Collections.synchronizedList() (The Synchronized Wrapper): Wraps a standard ArrayList in a thread-safe proxy. However, a crucial production gotcha applies here: iterating over a synchronized list still requires manual external locking to avoid a ConcurrentModificationException.
//
//CopyOnWriteArrayList (The Modern Concurrent Alternative): Designed for read-heavy, write-infrequent scenarios (like event listener lists). Every mutation creates a fresh cloned copy of the underlying array under a lock, leaving reads completely unblocked and lock-free.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;



public class ThreadSafeCollectionsDemo {
    public static void main(String[] args) {

        // ==========================================
        // 1. Legacy Vector (Blanket Object-Level Locking)
        // ==========================================
        Vector<String> legacyVector = new Vector<>();
        legacyVector.add("Session_A");
        legacyVector.add("Session_B");

        System.out.println("--- Legacy Vector ---");
        System.out.println("Vector element: " + legacyVector.get(0));
        // Safe, but pays synchronization overhead on every single .get() or .add() call.


        // ==========================================
        // 2. Modern Synchronized Wrapper (Collections.synchronizedList)
        // ==========================================
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        syncList.add("Node_1");
        syncList.add("Node_2");

        System.out.println("\n--- Synchronized Wrapper ---");

        /*
         * CRITICAL PRODUCTION RULE:
         * Iterating over a synchronized list requires manual block synchronization
         * to prevent race conditions during iteration cycles.
         */
        synchronized (syncList) {
            for (String node : syncList) {
                System.out.println("Safely iterated sync node: " + node);
            }
        }


        // ==========================================
        // 3. Modern Concurrent Collection (CopyOnWriteArrayList)
        // ==========================================
        List<String> concurrentList = new CopyOnWriteArrayList<>();
        concurrentList.add("Config_Alpha");
        concurrentList.add("Config_Beta");

        System.out.println("\n--- CopyOnWriteArrayList (Lock-Free Reads) ---");

        // Safe to iterate concurrently without external blocks because 
        // the iterator operates over an immutable snapshot of the array.
        for (String config : concurrentList) {
            System.out.println("Concurrent config read: " + config);
        }
    }
}


