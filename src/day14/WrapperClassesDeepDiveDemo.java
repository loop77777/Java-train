package day14;//1. Why Wrapper Classes Exist & Creating Objects
//Java splits its data types into two distinct worlds: Primitives (allocated directly on the stack for speed) and Objects (allocated on the heap). Because Java's Collections framework only manages object references on the heap, primitives must be "wrapped" inside object jackets.
//While Java 5 introduced automatic boxing, using explicit factory methods like Integer.valueOf() is highly preferred over the deprecated constructors (new Integer()). valueOf() leverages an internal cache for values between -128 and 127, saving significant memory by avoiding object duplication

import java.util.ArrayList;
import java.util.List;

public class WrapperClassesDeepDiveDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. WHY WRAPPERS EXIST: COLLECTIONS REQUIRE HEAP OBJECTS ===");

        // Primitives live directly on the thread stack frame for raw computational performance:
        int primitiveScore = 95;

        // Generics & Collections work ONLY with Heap object references, not raw stack bits:
        // List<int> illegalList = new ArrayList<>(); // COMPILATION ERROR: Type argument cannot be of primitive type

        // We wrap the primitive in an Integer object reference to bridge the worlds:
        List<Integer> scoreBoard = new ArrayList<>();
        scoreBoard.add(Integer.valueOf(primitiveScore)); // Explicit boxing
        scoreBoard.add(100);                             // Compiler autoboxing (calls Integer.valueOf(100))

        System.out.println("Collection storing boxed heap objects: " + scoreBoard);


        System.out.println("\n=== 2. CREATING OBJECTS: new Integer() vs. Integer.valueOf() ===");

        // Constructor approach: DEPRECATED since Java 9 (marked for removal)
        // Forces a redundant, new heap object allocation every single time:
        @SuppressWarnings("deprecation")
        Integer objFromCtor1 = new Integer(42);
        @SuppressWarnings("deprecation")
        Integer objFromCtor2 = new Integer(42);

        System.out.println("Constructor instances equality (== check): " + (objFromCtor1 == objFromCtor2)); // false


        // Factory method approach: Integer.valueOf()
        // Checks the internal static cache before allocating memory:
        Integer objFromFactory1 = Integer.valueOf(42);
        Integer objFromFactory2 = Integer.valueOf(42);

        System.out.println("Factory instances equality (== check): " + (objFromFactory1 == objFromFactory2)); // true
        System.out.println("Both references point to the exact same cached heap address: "
                + System.identityHashCode(objFromFactory1) + " == " + System.identityHashCode(objFromFactory2));


        System.out.println("\n=== 3. UNDERSTANDING THE INTEGER CACHE BOUNDARIES (-128 to 127) ===");

        // CASE A: Inside the Cache Range [-128 to 127]
        Integer cachedA = Integer.valueOf(127);
        Integer cachedB = Integer.valueOf(127);
        System.out.println("Inside cache (value 127) -> Reference identical? " + (cachedA == cachedB)); // true

        // CASE B: Outside the Cache Range (e.g., 128 or higher)
        // Cache misses; JVM is forced to carve out separate heap objects
        Integer outsideA = Integer.valueOf(128);
        Integer outsideB = Integer.valueOf(128);
        System.out.println("Outside cache (value 128) -> Reference identical? " + (outsideA == outsideB)); // false
        System.out.println("Outside cache (value 128) -> Value equals()?       " + outsideA.equals(outsideB)); // true


        System.out.println("\n=== 4. CACHE IMPLEMENTATION IN OTHER WRAPPERS ===");

        // Byte, Short, and Long also cache values from -128 to 127:
        Byte b1 = Byte.valueOf((byte) 10);
        Byte b2 = Byte.valueOf((byte) 10);
        System.out.println("Byte cache match: " + (b1 == b2)); // true

        // Character caches ASCII range [0 to 127]:
        Character c1 = Character.valueOf('A');
        Character c2 = Character.valueOf('A');
        System.out.println("Character cache match: " + (c1 == c2)); // true

        // Boolean caches TRUE and FALSE singletons:
        Boolean bool1 = Boolean.valueOf(true);
        Boolean bool2 = Boolean.valueOf("true");
        System.out.println("Boolean cache match: " + (bool1 == bool2)); // true (uses Boolean.TRUE)

        // Float and Double DO NOT cache values due to infinite fractional representations:
        Double d1 = Double.valueOf(1.0);
        Double d2 = Double.valueOf(1.0);
        System.out.println("Double cache match: " + (d1 == d2)); // false
    }
}

//2. Wrapper Conversion Utilities
//Wrapper classes act as the structural bridge between raw data types. They contain essential static parsing utilities to transform unstructured text input (like UI data or network strings) into primitive numbers or wrapper objects.

//Wrapper classes provide three primary conversion patterns to transform data between strings, primitives, and heap-allocated wrapper objects.The Three Conversion Bridges                   parseXxx(String)
//     String ----------------------------> Primitive
//        |                                    ^
//        |                                    |
//        | valueOf(String)     xxxValue()     |
//        v                  (or Auto-unboxing)|
//     Wrapper Object -------------------------+
//parseXxx(String s): Static utility. Parses text and produces a raw, stack-allocated primitive (e.g., Integer.parseInt("42") $\rightarrow$ int).valueOf(String s): Static factory. Parses text and produces a wrapper object reference on the heap, utilizing the internal cache where applicable (e.g., Integer.valueOf("42") $\rightarrow$ Integer).xxxValue(): Instance methods on the abstract base class java.lang.Number. Extracts the wrapped numerical value as a different target primitive type, performing widening or narrowing numeric casting.

