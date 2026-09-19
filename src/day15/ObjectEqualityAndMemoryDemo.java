package day15;//4. Object Equality (equals() vs ==) & Memory Management
//== checks Reference Identity (Are these two references pointing to the exact same memory coordinate on the heap?).
//
//equals() checks Structural Value Equality (Do these objects contain the same data state?).
//
//When an object loses its last remaining stack pointer reference, it instantly transitions to an "unreachable" state, qualifying it as garbage for the JVM memory reclaimer.

class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    // Overriding equals() to check for structural value equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return name != null && name.equals(user.name);
    }
}

public class ObjectEqualityAndMemoryDemo {
    public static void main(String[] args) {

        //System.## 1. Reference Identity (`==`) vs. Value Equality (`equals()`)

        User user1 = new User("Alice");
        User user2 = new User("Alice"); // Different heap address, same data state
        User user3 = user1;             // Points to the exact same memory address as user1

        System.out.println("user1 == user2 (Reference Check): " + (user1 == user2));
        // Output: false -> They occupy distinct memory coordinates on the heap.

        System.out.println("user1.equals(user2) (Value Check): " + user1.equals(user2));
        // Output: true -> Their internal data state ("Alice") matches.

        System.out.println("user1 == user3 (Reference Check): " + (user1 == user3));
        // Output: true -> Both stack references point to the exact same heap coordinate.


        System.out.println("\n## 2. Memory Management & Garbage Collection Lifecycle");

        // Creating an object on the heap, pointed to by stack variable 'tempUser'
        User tempUser = new User("Bob");
        System.out.println("Object created for: " + tempUser.name);

        // Stripping away the last remaining stack pointer reference
        tempUser = null;

        /*
         * At this exact moment:
         * 1. The User("Bob") object on the heap has lost its last stack pointer.
         * 2. It instantly transitions into an "unreachable" state.
         * 3. It now officially qualifies as garbage for the JVM memory reclaimer.
         */
        System.out.println("Stack reference severed. The User('Bob') object is now unreachable and eligible for GC.");

        // Suggesting JVM to run garbage collection (Note: execution timing is managed by the JVM)
        System.gc();
        System.out.println("Garbage collection cycle requested.");
    }
}