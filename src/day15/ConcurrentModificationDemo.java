package day15;

// 10. Safe Collection Mutation (Avoiding ConcurrentModificationException)
//A classic trap when working with Collections occurs when you try to modify a collection (like adding or removing elements) while iterating over it using a standard for-each loop.
//Java collections track a internal mutation counter (modCount). If the collection structure shifts while an active loop is running, it throws a ConcurrentModificationException. To handle this safely, you must use an explicit Iterator.

//Understanding the Fail-Fast Mechanism
//Java collections maintain an internal mutation tracker called modCount. When you create an enhanced for-each loop (which uses an underlying iterator behind the scenes), the iterator stores an expected mutation count (expectedModCount).

//The Trap: If you call collection.remove() or collection.add() directly on the collection while the loop is running, the collection's modCount increments, but the iterator doesn't know about it.
//
//The Fast-Fail Result: On the next iteration pass, the iterator detects a mismatch between modCount and expectedModCount and immediately throws a ConcurrentModificationException to protect memory integrity.


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConcurrentModificationDemo {
    public static void main(String[] args) {

        List<String> sessions = new ArrayList<>();
        sessions.add("Active_Alpha");
        sessions.add("Expired_Beta");
        sessions.add("Active_Gamma");
        sessions.add("Expired_Delta");

        System.out.println("Initial Sessions: " + sessions);

        /*
         * ==========================================
         * THE TRAP: Uncommenting this block will crash your app!
         * ==========================================
         *
         * for (String session : sessions) {
         *     if (session.startsWith("Expired")) {
         *         sessions.remove(session); // Throws ConcurrentModificationException!
         *     }
         * }
         */

        // ==========================================
        // THE SAFE FIX: Using an explicit Iterator
        // ==========================================
        Iterator<String> iterator = sessions.iterator();

        while (iterator.hasNext()) {
            String session = iterator.next();

            if (session.startsWith("Expired")) {
                // Safely remove the element via the iterator
                iterator.remove();
                System.out.println("Successfully removed: " + session);
            }
        }

        System.out.println("Sessions after safe removal: " + sessions);
        // Output: [Active_Alpha, Active_Gamma]
    }
}


