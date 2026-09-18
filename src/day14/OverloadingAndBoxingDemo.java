package day14;
// 3. Autoboxing, Unboxing, & Method Overloading Mechanics
//Autoboxing is the syntactic sugar where the Java compiler automatically injects Integer.valueOf(x) into your byte-code. Unboxing does the reverse by injecting .intValue().
//
//However, when a class provides overloaded methods (one accepting a primitive, another accepting a wrapper object), Java's resolution rules prioritize widening patterns over boxing patterns.

//The compiler resolves overloaded methods according to a strict priority hierarchy established to preserve backward compatibility with legacy Java code written before Java 5.
//
//The Compiler's Overload Resolution Hierarchy
//When an argument is evaluated against overloaded methods, the compiler attempts to resolve the method in three sequential phases:
//
// Java Overload Resolution Phases
//
// Phase 1: Primitive Widening
//(No Boxing, No Varargs)
//
//byte → short → int → long → float → double
//
//Example:
//byte → int
//int → long
//long → float
//
//----------------------------------
//
// Phase 2: Autoboxing / Unboxing
//(Followed by Reference Widening)
//
//int → Integer → Number → Object
//
//Example:
//int
// ↓ Boxing
//Integer
// ↓ Upcasting
//Number
// ↓ Upcasting
//Object
//
//----------------------------------
//
// Phase 3: Variable Arguments (Varargs)
//
//int...
//Integer...
//
//Example:
//m(int... x)
//m(Integer... x)
//
//Varargs are considered only if no suitable method is found in Phase 1 or Phase 2.
//
//----------------------------------
//
//
// Exact Match
// Primitive Widening
// Autoboxing / Unboxing
// Boxing + Reference Widening
// Varargs
//
//----------------------------------
//
// Easy Mantra
//
//Exact Match
//    ↓
//Widening
//    ↓
//Boxing
//    ↓
//Boxing + Upcasting
//    ↓
//Varargs
//
//----------------------------------

//Rule: Widening beats Boxing. The compiler will always choose to widen a primitive before it attempts to box it into an object wrapper. Furthermore, Java does not allow Widening then Boxing (e.g., an int cannot become a Long), though it does allow Boxing followed by Widening to a superclass (e.g., an int boxes to Integer, which widens to Number or Object).


public class OverloadingAndBoxingDemo {

    // Overload 1: Primitive Widening Target (int -> long)
    public static void test(long val) {
        System.out.println("Invoked: test(long) -> Primitive Widening won!");
    }

    // Overload 2: Autoboxing Target (int -> Integer)
    public static void test(Integer val) {
        System.out.println("Invoked: test(Integer) -> Autoboxing won!");
    }

    // Overload 3: Varargs (Lowest priority)
    public static void test(int... val) {
        System.out.println("Invoked: test(int...) -> Varargs won!");
    }

    // Overload 4: Reference Widening Target for Boxed Types
    public static void checkHierarchy(Number num) {
        System.out.println("Invoked: checkHierarchy(Number) -> Boxed then Widened!");
    }

    public static void checkHierarchy(Object obj) {
        System.out.println("Invoked: checkHierarchy(Object) -> Boxed then Widened to Object!");
    }

    // Method to demonstrate unboxing pitfalls
    public static void processUnboxing(int value) {
        System.out.println("Processed primitive: " + value);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. WIDENING VS. BOXING VS. VARARGS ===");
        int primitiveInt = 42;

        // An 'int' can widen to 'long', box to 'Integer', or match 'int...'
        // Resolution: Widening takes precedence over Boxing
        test(primitiveInt);

        // Explicitly passing a boxed type bypasses primitive widening
        test(Integer.valueOf(primitiveInt));

        // When exact and widening are unavailable, varargs is evaluated
        test(primitiveInt, primitiveInt);


        System.out.println("\n=== 2. BOXING FOLLOWED BY REFERENCE WIDENING ===");
        // Primitive int -> boxes to Integer -> widens up the class hierarchy to Number
        checkHierarchy(primitiveInt);


        System.out.println("\n=== 3. ILLEGAL COMBINATION: WIDENING THEN BOXING ===");
        // An 'int' CANNOT be autoboxed to a 'Long'
        // Long badRef = 42; // COMPILATION ERROR: incompatible types: int cannot be converted to Long
        Long validLongRef = 42L; // Must box from literal long (42L -> Long.valueOf(42L))
        System.out.println("Explicit long literal boxed correctly: " + validLongRef);


        System.out.println("\n=== 4. THE AUTO-UNBOXING NULLPOINTEREXCEPTION TRAP ===");
        Integer nullWrapper = null;

        try {
            // Compiler injects: processUnboxing(nullWrapper.intValue());
            // Calling .intValue() on a null pointer triggers an immediate NPE at runtime
            processUnboxing(nullWrapper);
        } catch (NullPointerException ex) {
            System.err.println("CAUGHT RUNTIME EXCEPTION: NullPointerException during auto-unboxing!");
            System.out.println("Explanation: Unboxing calls .intValue() without checking for null first.");
        }
    }
}

