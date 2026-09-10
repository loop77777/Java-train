
//Constructor Declarations

//A constructor is a specialized initialization block within a class that has no explicit return type (not even void) and bears the exact same name as the class. When execution code calls new ClassName(), the JVM allocates memory on the heap for the object state, populates fields with default zeroes/nulls, and immediately invokes the constructor code to properly configure variables before control returns to the app.

package day8.constructors;

// Base class to demonstrate constructor chaining across inheritance
class BaseDeliveryEntity {
    private final String trackingPrefix;

    // Base constructor
    public BaseDeliveryEntity(String trackingPrefix) {
        this.trackingPrefix = trackingPrefix;
        System.out.println("  [BASE CONSTRUCTOR] Initialized superclass hierarchy with prefix: " + this.trackingPrefix);
    }

    public String getTrackingPrefix() {
        return trackingPrefix;
    }
}


public class ShipmentOrder extends BaseDeliveryEntity {

    // =========================================================================
    // INSTANCE STATE VARIABLES
    // =========================================================================
    private String orderId;
    private String recipientName;
    private double packageWeightKg;
    private boolean isExpressDelivery;

    // =========================================================================
    // 1. DEFAULT / NO-ARG CONSTRUCTOR (Constructor Overloading & Chaining via this())
    // =========================================================================
    public ShipmentOrder() {
        // Chaining to the 2-argument constructor with baseline defaults
        this("STANDARD_GUEST", 0.5);
        System.out.println("[CONSTRUCTOR - No-Arg] Default shipment container allocated.");
    }

    // =========================================================================
    // 2. PARAMETERIZED CONSTRUCTOR (Chaining to fully qualified constructor)
    // =========================================================================
    public ShipmentOrder(String recipientName, double packageWeightKg) {
        // Delegates to the 3-arg constructor
        this(recipientName, packageWeightKg, false);
    }

    // =========================================================================
    // 3. MASTER INITIALIZATION CONSTRUCTOR (Chaining to Superclass via super())
    // =========================================================================
    public ShipmentOrder(String recipientName, double packageWeightKg, boolean isExpressDelivery) {
        // Rule: super() or this() MUST be the absolute first statement in a constructor
        super("IN-BLR");

        this.orderId = "SHP-" + (int) (Math.random() * 90000 + 10000);
        this.recipientName = recipientName;
        this.packageWeightKg = packageWeightKg;
        this.isExpressDelivery = isExpressDelivery;

        System.out.println("[CONSTRUCTOR - Master] Fully initialized Shipment #"
                + this.orderId + " for " + this.recipientName + " (" + this.packageWeightKg + " kg)");
    }

    // =========================================================================
    // 4. COPY CONSTRUCTOR (Object cloning/state duplication pattern)
    // =========================================================================
    public ShipmentOrder(ShipmentOrder source) {
        super(source.getTrackingPrefix());
        this.orderId = "SHP-COPY-" + (int) (Math.random() * 90000 + 10000);
        this.recipientName = source.recipientName;
        this.packageWeightKg = source.packageWeightKg;
        this.isExpressDelivery = source.isExpressDelivery;
        System.out.println("[CONSTRUCTOR - Copy] Created duplicate shipment #" + this.orderId);
    }

    public void displayShipmentSummary() {
        System.out.println("  -> Tracking: " + getTrackingPrefix() + "/" + orderId
                + " | Recipient: " + recipientName
                + " | Weight: " + packageWeightKg + "kg | Express: " + isExpressDelivery);
    }

    // =========================================================================
    // MAIN EXECUTION FLOW
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA CONSTRUCTOR ARCHITECTURE DEMO         ");
        System.out.println("==================================================");

        // 1. Instantiating via Default / No-Arg Constructor (Demonstrates this() chaining)
        System.out.println("\n--- Step 1: Default Constructor Call ---");
        ShipmentOrder order1 = new ShipmentOrder();
        order1.displayShipmentSummary();

        // 2. Instantiating via Parameterized Constructor
        System.out.println("\n--- Step 2: Parameterized Constructor Call ---");
        ShipmentOrder order2 = new ShipmentOrder("Jyothi Prasad", 3.2, true);
        order2.displayShipmentSummary();

        // 3. Instantiating via Copy Constructor
        System.out.println("\n--- Step 3: Copy Constructor Call ---");
        ShipmentOrder order3 = new ShipmentOrder(order2);
        order3.displayShipmentSummary();
    }
}
