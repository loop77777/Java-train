package day2.array;

public class MetroParkingSystem {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   NAMMA METRO: AUTOMATED PARKING LOT SYSTEM     ");
        System.out.println("==================================================\n");

        // -----------------------------------------------------------------
        // 1. DECLARATION
        // The parking supervisor sets up the software reference.
        // Stack slot created; No physical slots allocated in memory yet.
        // -----------------------------------------------------------------
        int[] parkingSlots;
        System.out.println("[Step 1] System Initialized: Parking reference declared (0 heap memory used).\n");


        // -----------------------------------------------------------------
        // 2. CONSTRUCTION
        // Metro authorities construct a 4-bay parking lot on the ground (Heap).
        // JVM auto-zeroes every slot: 0 means 'VACANT/EMPTY'.
        // -----------------------------------------------------------------
        parkingSlots = new int[4];
        System.out.println("[Step 2] 4 Parking Bays Constructed on Heap (Capacity: " + parkingSlots.length + ")");
        System.out.println("Checking Default Slot Status (0 = Empty Slot):");
        for (int i = 0; i < parkingSlots.length; i++) {
            System.out.println("   Bay [" + i + "] -> Status: " + (parkingSlots[i] == 0 ? "VACANT (0)" : "OCCUPIED"));
        }
        System.out.println();


        // -----------------------------------------------------------------
        // 3. INITIALIZATION
        // Real commuters arrive and tap their smart cards to park vehicles.
        // We write the commuter Vehicle Token IDs into specific slot indices.
        // -----------------------------------------------------------------
        System.out.println("[Step 3] Vehicles entering and occupying slots...");
        parkingSlots[0] = 5501; // KA-01 Scooter parked in Bay 0
        parkingSlots[1] = 8820; // KA-04 Car parked in Bay 1
        parkingSlots[3] = 9912; // KA-51 Bike parked in Bay 3 (Bay 2 left empty)

        System.out.println("\nLive Parking Bay Status Display:");
        for (int i = 0; i < parkingSlots.length; i++) {
            if (parkingSlots[i] != 0) {
                System.out.println("   Bay [" + i + "] -> OCCUPIED by Vehicle Token: #" + parkingSlots[i]);
            } else {
                System.out.println("   Bay [" + i + "] -> VACANT (Available for booking)");
            }
        }
        System.out.println("==================================================");
    }
}

//STEP 1: CLASS LOADING (Once per JVM run)
//        +-------------------------------------------------------------+
//        | 1. Static Variables allocated in Method Area / Metaspace    |
//        | 2. Static Initialization Blocks execute top-to-bottom       |
//        +-------------------------------------------------------------+
//                                       │
//                                       ▼
//                   STEP 2: INSTANTIATION (Every 'new' call)
//        +-------------------------------------------------------------+
//        | 1. Heap memory allocated & zeroed out                       |
//        | 2. super() constructor executes                             |
//        | 3. Instance Variables & Instance Init Blocks (injected)     |
//        | 4. Constructor body custom code executes                    |
//        +-------------------------------------------------------------+

//Initialization Blocks
//Technical Deep Dive
//Initialization blocks provide a structured way to run initialization logic outside of constructors. They are blocks of code defined directly inside a class body.
//
//Static Initialization Blocks:
//
//Marked with the explicit static keyword.
//
//Executed exactly once when the class loader loads the class bytecode into the JVM's memory architecture.
//
//They run before any object instances of the class are created and before any static methods are invoked.
//
//Used for configuring complex static variables or initializing external native drivers.
//
//Instance Initialization Blocks:
//
//Defined as a naked block of code { ... } inside a class body, without any method or static markings.
//
//Executed every single time a new object instance is created.
//
//The compiler copies the code inside this block and injects it into the very beginning of every constructor right after the super-constructor call (super()). It executes before the custom code inside the constructor body runs.
