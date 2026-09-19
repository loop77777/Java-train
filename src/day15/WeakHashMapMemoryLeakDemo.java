package day15;

//Understanding WeakHashMap Architecture
//Strong References (Standard HashMap): As long as a HashMap entry exists, it maintains a strong reference to its key and value. Even if your application loses all external stack pointers to the key object, the map keeps holding onto it, preventing garbage collection and causing memory leaks.
//
//Weak References (WeakHashMap): The internal entry wrapper wraps the key in a WeakReference. If the key object has no remaining strong references anywhere else in the application, the JVM treats it as eligible for garbage collection. Once the key is reclaimed, the WeakHashMap automatically clears the corresponding key-value entry during subsequent operations.


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


class SessionContext {
    String sessionId;

    public SessionContext(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "SessionContext{" + sessionId + "}";
    }
}

public class WeakHashMapMemoryLeakDemo {
    public static void main(String[] args) throws InterruptedException {

        // Using WeakHashMap for dynamic cache management
        Map<SessionContext, String> weakCache = new WeakHashMap<>();

        // 1. Create a key and store it in the cache
        SessionContext activeSession = new SessionContext("USR_999");
        weakCache.put(activeSession, "User Dashboard Metadata State");

        System.out.println("--- Before Dropping Strong Reference ---");
        System.out.println("Cache size: " + weakCache.size());
        System.out.println("Cache contains activeSession? " + weakCache.containsKey(activeSession));

        // 2. Drop the last remaining stack reference to the key object
        activeSession = null;

        // 3. Trigger Garbage Collection
        // (Note: System.gc() is a hint to the JVM, but typically clears weak references in a demo)
        System.gc();

        // Pause briefly to let the background GC thread clean up unreachable objects
        Thread.sleep(500);

        System.out.println("\n--- After Garbage Collection Cycle ---");
        System.out.println("Cache size: " + weakCache.size());
        // Output: 0 -> The entry has been automatically purged because the key lost its strong reference!
        System.out.println("Cache contents: " + weakCache);
    }
}

