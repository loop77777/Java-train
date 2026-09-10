package day7;

public class StringConceptsDemo {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA STRING CONCEPTS: WORKING DEMO         ");
        System.out.println("==================================================\n");

        // =========================================================================
        // 1. IMMUTABILITY DEMONSTRATION
        // =========================================================================
        System.out.println("--- 1. String Immutability Demo ---");

        String original = "Swiggy";
        System.out.println("Original string before concat: " + original);
        System.out.println("Original Memory HashCode     : " + System.identityHashCode(original));

        // Attempting to modify the string using concat()
        original.concat(" Delivery");

        // Notice original remains unchanged because Strings are immutable
        System.out.println("Original string after concat : " + original + " (Unmodified!)");
        System.out.println("Original Memory HashCode     : " + System.identityHashCode(original));

        // To capture the change, a brand-new object must be referenced
        String updated = original.concat(" Delivery");
        System.out.println("New reference holding result : " + updated);
        System.out.println("New Object Memory HashCode   : " + System.identityHashCode(updated));
        System.out.println();

        // =========================================================================
        // 2. STRING CONSTANT POOL (SCP) & OBJECT CREATION DEMO
        // =========================================================================
        System.out.println("--- 2. String Constant Pool (SCP) Demo ---");

        // Created using String Literals -> Managed in the String Constant Pool (SCP)
        String s1 = "Bengaluru";
        String s2 = "Bengaluru";

        // Created using 'new' keyword -> Forces a new object in regular Heap memory
        String s3 = new String("Bengaluru");
        String s4 = new String("Bengaluru");

        // Verification: == compares memory address / reference
        System.out.println("s1 == s2 (Both literals from SCP)          : " + (s1 == s2)); // true
        System.out.println("s1 == s3 (SCP literal vs Heap 'new' object): " + (s1 == s3)); // false
        System.out.println("s3 == s4 (Two separate 'new' Heap objects) : " + (s3 == s4)); // false

        // Verification: .equals() compares character content
        System.out.println("s1.equals(s3) (Content Comparison)        : " + s1.equals(s3)); // true
        System.out.println();

        // Display memory identity hashes to visually inspect pool reuse
        System.out.println("Memory Identity Hash for s1 (SCP) : " + System.identityHashCode(s1));
        System.out.println("Memory Identity Hash for s2 (SCP) : " + System.identityHashCode(s2) + " (Same as s1!)");
        System.out.println("Memory Identity Hash for s3 (Heap): " + System.identityHashCode(s3));
        System.out.println("Memory Identity Hash for s4 (Heap): " + System.identityHashCode(s4));
        System.out.println();

        // =========================================================================
        // 3. MANUAL INTERNING: String.intern()
        // =========================================================================
        System.out.println("--- 3. Manual Interning (intern() method) ---");

        // intern() returns the canonical representation from the SCP
        String s3Interned = s3.intern();

        System.out.println("s1 == s3.intern()                          : " + (s1 == s3Interned)); // true
        System.out.println("Memory Identity Hash for s3.intern()      : " + System.identityHashCode(s3Interned));
    }
}
