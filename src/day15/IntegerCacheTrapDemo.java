package day15;

//9. The Inner Mechanics of the Wrapper Cache Trap
//We noted earlier that Integer.valueOf() caches objects between -128 and 127. Let's look at exactly how this creates silent runtime bugs if you accidentally use reference equality (==) instead of structural equality (.equals()) in your business logic.

//Understanding the Cache TrapWhen you assign a primitive value to an Integer object (or use Integer.valueOf()), Java automatically caches wrapper instances for values between -128 and 127 (inclusive) to optimize memory and performance.

//Inside the Cache Range ([-128, 127]): Integer a = 50; and Integer b = 50; point to the exact same cached object instance on the heap. Consequently, a == b evaluates to true.Outside the Cache Range (> 127or < -128): Java instantiates completely separate heap objects for each assignment. Even though c and d hold the exact same numerical value, c == d evaluates to false because they occupy different memory addresses.


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IntegerCacheTrapDemo {
    public static void main(String[] args) {

        // ==========================================
        // 1. Within the Integer Cache Range [-128 to 127]
        // ==========================================
        Integer a = 127;
        Integer b = 127;

        System.out.println("--- Within Cache Range (127) ---");
        System.out.println("a == b (Reference Check): " + (a == b));
        // Output: true -> Points to the cached heap instance.
        System.out.println("a.equals(b) (Value Check): " + a.equals(b));
        // Output: true


        // ==========================================
        // 2. Outside the Integer Cache Range (> 127)
        // ==========================================
        Integer c = 128;
        Integer d = 128;

        System.out.println("\n--- Outside Cache Range (128) ---");
        System.out.println("c == d (Reference Check): " + (c == d));
        // Output: FALSE -> Created as distinct heap objects!
        System.out.println("c.equals(d) (Value Check): " + c.equals(d));
        // Output: true -> Structural values match correctly.


        // ==========================================
        // 3. Simulating a Silent Production Bug
        // ==========================================
        System.out.println("\n--- Business Logic Validation Bug ---");

        Integer userIdFromDb1 = 100; // Inside cache range
        Integer userIdFromDb2 = 500; // Outside cache range
        Integer expectedId = 500;

        // Buggy implementation using '=='
        System.out.println("Comparing userIdFromDb2 (500) with expectedId (500) using '==':");
        if (userIdFromDb2 == expectedId) {
            System.out.println("Access Granted!");
        } else {
            System.out.println("Access Denied! (Silent Bug: '==' failed because they are separate heap objects)");
        }

        // Correct implementation using '.equals()'
        System.out.println("\nComparing using '.equals()':");
        if (userIdFromDb2.equals(expectedId)) {
            System.out.println("Access Granted! (.equals() correctly checked structural value)");
        }
    }
}




