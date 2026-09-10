//13. Variable Declarations
//
//Java defines three distinct classification scopes for variables, each mapping to completely different lifetimes and memory areas within the runtime environment:

package day8.variables;

public class VariableScopesDemo {

    // =========================================================================
    // 1. STATIC / CLASS VARIABLES
    // Stored in: Metaspace / Method Area (Class metadata)
    // Lifetime : Entire JVM runtime (from class loading until class unloading)
    // Default  : Automatically initialized to default values (0, null, false)
    // =========================================================================
    public static final String REGION_CODE = "IN-KA-BLR"; // Static constant
    private static int activeDriverCount = 0;              // Shared state across all instances

    // =========================================================================
    // 2. INSTANCE / OBJECT VARIABLES (Non-Static Fields)
    // Stored in: Heap Memory (inside each specific object header/payload)
    // Lifetime : Lives as long as the object reference is reachable (GC-managed)
    // Default  : Automatically initialized during 'new' allocation
    // =========================================================================
    private int driverId;            // Defaults to 0
    private String driverName;       // Defaults to null
    private boolean isAvailable;     // Defaults to false
    private double currentLatitude;  // Defaults to 0.0

    // Constructor modifying instance and static state
    public VariableScopesDemo(int driverId, String driverName) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.isAvailable = true;
        this.currentLatitude = 12.9716;

        // Mutating static class variable
        activeDriverCount++;
    }

    // =========================================================================
    // 3. LOCAL VARIABLES & PARAMETERS
    // Stored in: Thread Execution Stack (Local Variable Table of the Stack Frame)
    // Lifetime : Pushed when method enters; popped/destroyed when frame exits
    // Default  : NO DEFAULT INITIALIZATION (Must be explicitly initialized before use)
    // =========================================================================
    public double calculateFareEstimate(double distanceKm, double surgeRate) {
        // 'distanceKm' and 'surgeRate' are local method parameters (Stack-allocated)

        // Local variable declared inside block
        double baseBookingFee = 30.0;
        double distanceCharge; // Declared but uninitialized

        if (distanceKm > 0) {
            distanceCharge = distanceKm * 12.5; // Explicit assignment
        } else {
            distanceCharge = 0.0;
        }

        // 'totalEstimatedFare' exists strictly until the method returns
        double totalEstimatedFare = (baseBookingFee + distanceCharge) * surgeRate;

        // Block-scoped local variable (Loop/Control block)
        for (int step = 1; step <= 2; step++) {
            // 'step' is allocated on the stack and destroyed when loop terminates
            int auditCheckpoint = step * 10;
            // System.out.println("Step checkpoint: " + auditCheckpoint);
        }

        // auditCheckpoint is completely out of scope here (triggers compile error if accessed)

        return totalEstimatedFare;
    }

    public void displayDriverProfile() {
        System.out.println("Driver ID   : " + this.driverId);
        System.out.println("Name        : " + this.driverName);
        System.out.println("Available   : " + this.isAvailable);
        System.out.println("Latitude    : " + this.currentLatitude);
        System.out.println("Region Code : " + REGION_CODE);
    }

    public static int getActiveDriverCount() {
        return activeDriverCount;
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA VARIABLE SCOPES & MEMORY DEMO         ");
        System.out.println("==================================================\n");

        // 1. Instantiating Object 1
        System.out.println("--- Instantiating Driver 1 ---");
        VariableScopesDemo driver1 = new VariableScopesDemo(101, "Suresh Kumar");
        driver1.displayDriverProfile();

        // 2. Local variable allocation on main thread stack
        double tripDistance = 14.5;
        double surgeFactor = 1.2;
        double estimatedCost = driver1.calculateFareEstimate(tripDistance, surgeFactor);
        System.out.println("Fare Estimate (Computed on Stack) : Rs." + estimatedCost);

        // 3. Instantiating Object 2
        System.out.println("\n--- Instantiating Driver 2 ---");
        VariableScopesDemo driver2 = new VariableScopesDemo(102, "Mahesh Gowda");
        driver2.displayDriverProfile();

        // 4. Inspecting static variable shared across instances
        System.out.println("\n--- Static Class Variable Audit ---");
        System.out.println("Total Active Drivers (Metaspace / Class Area): "
                + VariableScopesDemo.getActiveDriverCount());

        /* =====================================================================
         * COMPILER ENFORCEMENT ON UNINITIALIZED LOCAL VARIABLES (DO NOT UNCOMMENT)
         * =====================================================================
         *
         * public void uninitializedLocalCheck() {
         *     int unassignedValue;
         *     // System.out.println(unassignedValue);
         *     // COMPILE ERROR: The local variable unassignedValue may not have been initialized
         * }
         */
    }
}