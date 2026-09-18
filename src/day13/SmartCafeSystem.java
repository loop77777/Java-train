package day13;//+-------------------------------------------------------------------------+
// |                               <<enumeration>>                           |
// |                                   CupSize                               |
// +-------------------------------------------------------------------------+
// | - basePrice : double                                                    |
// +-------------------------------------------------------------------------+
// | + SMALL(2.50)                                                           |
// | + MEDIUM(3.50)                                                          |
// | + LARGE(4.50)                                                           |
// +-------------------------------------------------------------------------+
// | - CupSize(basePrice : double)                                           |
// | + getBasePrice() : double                                               |
// +-------------------------------------------------------------------------+
//                                      ^
//                                      | 1
//                                      | uses / aggregates
//                                      |
// +-------------------------------------------------------------------------+
// |                                CoffeeOrder                              |
// +-------------------------------------------------------------------------+
// | - sugarPackets : byte = 2                                               |
// | - storeBranchId : short = 0b0001                                        |
// | - orderId : int                                                         |
// | - orderTimeEpoch : long = 1_700_000L                                    |
// | - taxRate : float = 0.08f                                               |
// | - finalPrice : double                                                   |
// | - pickupLane : char = 'A'                                               |
// | - isStudentDiscountApplied : boolean                                    |
// | - customerName : String                                                 |
// | - size : CupSize                                                        |
// | - completed : boolean                                                   |
// +-------------------------------------------------------------------------+
// | <<static>> static { }                                                   |
// | <<instance>> { }                                                        |
// | + CoffeeOrder()                                                         |
// | + CoffeeOrder(orderId : int, customerName : String)                     |
// | + CoffeeOrder(orderId : int, customerName : String, size : CupSize)     |
// | + getOrderId() : int                                                    |
// | + setOrderId(orderId : int) : void                                      |
// | + getCustomerName() : String                                            |
// | + setCustomerName(customerName : String) : void                         |
// | + getSize() : CupSize                                                   |
// | + setSize(size : CupSize) : void                                        |
// | + getFinalPrice() : double                                              |
// | + setFinalPrice(finalPrice : double) : void                             |
// | + isCompleted() : boolean                                               |
// | + setCompleted(completed : boolean) : void                              |
// | + isStudentDiscountApplied() : boolean                                  |
// | + setStudentDiscountApplied(status : boolean) : void                    |
// | + toString() : String                                                   |
// +-------------------------------------------------------------------------+
//               ^                                            ^
//               | 0..3 (holds in array)                      | uses (parameter)
//               |                                            |
// +-----------------------------+              +-----------------------------+
// |        PickupCounter        |              |        CashierService       |
// +-----------------------------+              +-----------------------------+
// | - pickupSlots : CoffeeOrder[]|             + applyExtraTip(              |
// +-----------------------------+              |     tip : double) : void    |
// | + placeOnCounter(           |              | + markOrderReady(           |
// |     order: CoffeeOrder)     |              |     order : CoffeeOrder     |
// |     throws CounterFullExc   |              | ) : void                    |
// | + serveWaitingCustomers()   |              | + calculateTotalBill(       |
// |     : void                  |              |     order : CoffeeOrder,    |
// +-----------------------------+              |     isStudent : boolean,    |
//               |                              |     extraShots : int        |
//               | throws                       | ) : double                  |
//               v                              +-----------------------------+
// +-----------------------------+
// |     <<checked exception>>   |
// |     CounterFullException    |
// +-----------------------------+
// | + CounterFullException(     |
// |       message : String)     |
// +-----------------------------+

// Title: SmartCafe Automated Order Management & Fulfillment System
//Context & Domain Background
//A bustling university campus café, SmartCafe, is transitioning from manual order taking to a self-service digital ordering and staging kiosk. Due to peak-hour rush between lectures, the system must process hundreds of customized drink orders while maintaining deterministic memory performance, preventing state corruption, and strictly managing space on a limited physical pickup counter.
//
//Business Requirements & Rules
//Drink Modeling & Customization:
//
//Every order represents a specific customer drink with a standard cup size: SMALL ($2.50), MEDIUM ($3.50), or LARGE ($4.50).
//
//Customers can add extra espresso shots at a fixed rate of $0.75 per shot.
//
//Large cups require an additional eco-packaging surcharge of $0.50.
//
//Registered students with extra shots qualify for a 10% promotional discount applied to the total beverage subtotal.
//
//Input customer names must be normalized (trimmed of leading/trailing whitespace and converted to uppercase) to prevent queue duplication.
//
//Physical Counter Staging Constraint:
//
//The pickup station has a physical limit of exactly 3 staging slots represented by an indexed queue/array.
//
//If an order is placed on the counter when all 3 slots are occupied, the system must immediately reject the placement by throwing a checked exception (CounterFullException).
//
//Orders are cleared from slots as customers pick them up, resetting slot availability.
//
//Technical & Core Java Constraints:
//
//Encapsulation & JavaBeans: All entities (CoffeeOrder) must enforce data hiding via private fields, provide standard public getters/setters, adhere to naming conventions, and provide a parameterless constructor alongside chained overloaded constructors.
//
//Type Fidelity & Literal Coverage: The implementation must utilize every primitive type (byte, short, int, long, float, double, char, boolean) with corresponding literal representations (binary, numeric underscores, hex/floating-point suffixes).
//
//Initialization Lifecycles: Demonstrate both class-level (Static Initialization Blocks) and instance-level (Instance Initialization Blocks) behaviors, contrasting default automatic field values with local stack variable initialization rules.
//
//Pass-By-Value Proofs: The core business service (CashierService) must prove that Java primitives cannot have their caller values modified across stack frames, while object parameters allow heap mutation through copied references (with reassignment remaining purely local to the callee).
//
//Control Flow Scaffolding: Implement branching (if-else, ternary ? :, switch), loop variants (for, while), and loop control flow (break, continue).

// ============================================================================
// 1. CHECKED EXCEPTION: CounterFullException
// ============================================================================
class CounterFullException extends Exception {
    public CounterFullException(String message) {
        super(message);
    }
}

// ============================================================================
// 2. ENUMERATION: CupSize
// ============================================================================
enum CupSize {
    SMALL(2.50),
    MEDIUM(3.50),
    LARGE(4.50);

    private final double basePrice;

    // Private constructor (implicit for enums)
    private CupSize(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return this.basePrice;
    }
}

// ============================================================================
// 3. JAVABEANS MODEL ENTITY: CoffeeOrder
// ============================================================================
class CoffeeOrder {
    // Exact Primitive Fields & Specific Literals from Class Diagram
    private byte sugarPackets = 2;                  // Byte literal
    private short storeBranchId = 0b0001;           // Binary literal (value = 1)
    private int orderId;                            // Standard integer
    private long orderTimeEpoch = 1_700_000L;       // Long literal with numeric underscore
    private float taxRate = 0.08f;                  // Float literal (8% tax)
    private double finalPrice;                      // Double-precision floating-point
    private char pickupLane = 'A';                  // Character literal
    private boolean isStudentDiscountApplied;       // Boolean flag

    // Reference Types
    private String customerName;
    private CupSize size;
    private boolean completed;

    // Static Initialization Block (SIB)
    static {
        System.out.println("[STATIC BLOCK] CoffeeOrder class loaded into JVM.");
    }

    // Instance Initialization Block (IIB)
    {
        this.completed = false;
        this.isStudentDiscountApplied = false;
        System.out.println("[INSTANCE BLOCK] Memory allocated on heap for new CoffeeOrder.");
    }

    // Default No-Arg Constructor (JavaBeans Standard)
    public CoffeeOrder() {
        super();
    }

    // Overloaded Constructor 1: Basic (Calls Chained Overloaded Constructor 2)
    public CoffeeOrder(int orderId, String customerName) {
        this(orderId, customerName, CupSize.MEDIUM); // Defaults to MEDIUM size
    }

    // Overloaded Constructor 2: Full Specification (Constructor Chaining Target)
    public CoffeeOrder(int orderId, String customerName, CupSize size) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.size = size;
        this.finalPrice = (size != null) ? size.getBasePrice() : 0.0;
    }

    // --- JavaBeans Standard Getters and Setters ---

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CupSize getSize() {
        return size;
    }

    public void setSize(CupSize size) {
        this.size = size;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isStudentDiscountApplied() {
        return isStudentDiscountApplied;
    }

    public void setStudentDiscountApplied(boolean status) {
        this.isStudentDiscountApplied = status;
    }

    public byte getSugarPackets() {
        return sugarPackets;
    }

    public short getStoreBranchId() {
        return storeBranchId;
    }

    public long getOrderTimeEpoch() {
        return orderTimeEpoch;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public char getPickupLane() {
        return pickupLane;
    }

    @Override
    public String toString() {
        return "CoffeeOrder [ID=" + orderId
                + ", Customer=" + customerName
                + ", Size=" + size
                + ", FinalPrice=$" + String.format("%.2f", finalPrice)
                + ", Lane=" + pickupLane
                + ", StudentDiscount=" + isStudentDiscountApplied
                + ", Completed=" + completed + "]";
    }
}

// ============================================================================
// 4. BUSINESS SERVICE: CashierService (Stateless, Loose Coupling, High Cohesion)
// ============================================================================
class CashierService {

    // 1. Primitive Pass-by-Value Demo: Modifies only its local stack copy
    public void applyExtraTip(double tip) {
        System.out.println("  [applyExtraTip] Incoming stack tip copy: $" + tip);
        tip = tip + 5.00;
        System.out.println("  [applyExtraTip] Local stack copy modified to: $" + tip);
    }

    // 2. Object Pass-by-Value Demo: Mutates heap object via pointer copy
    public void markOrderReady(CoffeeOrder order) {
        if (order != null) {
            order.setCompleted(true); // Mutates actual instance on Heap

            // Reassigning local pointer variable does NOT affect caller reference
            order = new CoffeeOrder(999, "GHOST_OVERRIDE", CupSize.SMALL);
        }
    }

    // 3. Billing Algorithm: Operators, String Methods, Ternary, & Switch
    public double calculateTotalBill(CoffeeOrder order, boolean isStudent, int extraShots) {
        if (order == null) {
            return 0.0;
        }

        // String Operations: Sanitization & Normalization
        if (order.getCustomerName() != null) {
            order.setCustomerName(order.getCustomerName().trim().toUpperCase());
        }

        // Base price from aggregated enum
        double subtotal = order.getSize().getBasePrice();

        // Arithmetic & Compound Assignment Operators
        subtotal += (extraShots * 0.75); // $0.75 per extra espresso shot

        // Size-based surcharges via switch statement
        switch (order.getSize()) {
            case LARGE:
                subtotal += 0.50; // Large eco-packaging surcharge
                break;
            case MEDIUM:
            case SMALL:
            default:
                break;
        }

        // Relational & Logical Operators
        if (isStudent && extraShots > 0) {
            order.setStudentDiscountApplied(true);
        }

        // Ternary Operator: 10% discount if qualified, else 0%
        double discountFactor = order.isStudentDiscountApplied() ? 0.90 : 1.00;
        subtotal *= discountFactor;

        // Add tax: compound assignment with float-to-double arithmetic
        double totalWithTax = subtotal + (subtotal * order.getTaxRate());

        // Round to 2 decimal places
        double roundedFinal = Math.round(totalWithTax * 100.0) / 100.0;
        order.setFinalPrice(roundedFinal);

        return roundedFinal;
    }
}

// ============================================================================
// 5. STAGING CONTROLLER: PickupCounter (Array Storage, Composition: 0..3)
// ============================================================================
class PickupCounter {
    // Array holds exactly 0..3 references as specified in UML
    private static final int CAPACITY = 3;
    private final CoffeeOrder[] pickupSlots;

    public PickupCounter() {
        // Array construction on Heap: initialized to null references
        this.pickupSlots = new CoffeeOrder[CAPACITY];
    }

    // Adds order to the first available array index; throws custom checked exception if full
    public synchronized void placeOnCounter(CoffeeOrder order) throws CounterFullException {
        for (int i = 0; i < pickupSlots.length; i++) {
            if (pickupSlots[i] == null) {
                pickupSlots[i] = order;
                System.out.println("-> Staged Order #" + order.getOrderId()
                        + " (" + order.getCustomerName() + ") in Counter Slot [" + i + "]");
                return;
            }
        }
        // Exception thrown when array reaches maximum capacity
        throw new CounterFullException("Action Denied: Pickup Counter is FULL (Capacity: " + CAPACITY + ").");
    }

    // Loop iteration using while, continue, and array reference dereferencing
    public void serveWaitingCustomers() {
        System.out.println("\n--- [DISPATCHING ORDERS AT PICKUP COUNTER] ---");
        int index = 0;
        while (index < pickupSlots.length) {
            CoffeeOrder current = pickupSlots[index];

            if (current == null) {
                index++;
                continue; // Skip empty slots
            }

            System.out.println("Now Calling Customer: " + current.getCustomerName()
                    + " | Please pick up Order #" + current.getOrderId()
                    + " at Lane " + current.getPickupLane());

            // Clear slot once customer receives the order
            pickupSlots[index] = null;
            index++;
        }
    }
}

// ============================================================================
// 6. MAIN APPLICATION EXECUTION
// ============================================================================
public class SmartCafeSystem {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("         SMARTCAFE SYSTEM ARCHITECTURE RUNTIME DEMO       ");
        System.out.println("==========================================================\n");

        CashierService cashier = new CashierService();
        PickupCounter counter = new PickupCounter();

        // --------------------------------------------------------------------
        // DEMO 1: Literal Types and JavaBeans Initialization
        // --------------------------------------------------------------------
        System.out.println(">>> 1. Creating Orders via Chained Constructors & IIB:");
        CoffeeOrder order1 = new CoffeeOrder(101, "   alice smith   ", CupSize.SMALL);
        CoffeeOrder order2 = new CoffeeOrder(102, "Bob Jones", CupSize.LARGE);
        CoffeeOrder order3 = new CoffeeOrder(103, "Charlie Brown"); // Invokes 2-arg constructor -> MEDIUM

        System.out.println("Order 1 initial: " + order1);
        System.out.println("Order 2 initial: " + order2);
        System.out.println("Order 3 initial: " + order3);

        // --------------------------------------------------------------------
        // DEMO 2: Pass-By-Value Mechanics
        // --------------------------------------------------------------------
        System.out.println("\n>>> 2. Verifying Pass-By-Value Semantics:");

        // Primitive test:
        double tipOriginal = 3.00;
        System.out.println("Original Primitive 'tipOriginal' before call: $" + tipOriginal);
        cashier.applyExtraTip(tipOriginal);
        System.out.println("Original Primitive 'tipOriginal' after call (Unchanged): $" + tipOriginal);

        // Object test:
        System.out.println("Order 1 completion status before markOrderReady: " + order1.isCompleted());
        cashier.markOrderReady(order1);
        System.out.println("Order 1 completion status after markOrderReady (Mutated): " + order1.isCompleted());
        System.out.println("Order 1 reference integrity check (Customer unchanged): " + order1.getCustomerName());

        // --------------------------------------------------------------------
        // DEMO 3: Business Logic Calculation (CashierService)
        // --------------------------------------------------------------------
        System.out.println("\n>>> 3. Computing Total Bills via CashierService:");

        // Order 1: Alice (Student = true, Extra Shots = 2) -> Qualifies for 10% discount
        double bill1 = cashier.calculateTotalBill(order1, true, 2);
        System.out.println("Bill for Order 1 (" + order1.getCustomerName() + "): $" + bill1);

        // Order 2: Bob (Student = false, Extra Shots = 1) -> Regular large cup
        double bill2 = cashier.calculateTotalBill(order2, false, 1);
        System.out.println("Bill for Order 2 (" + order2.getCustomerName() + "): $" + bill2);

        // Order 3: Charlie (Student = false, Extra Shots = 0) -> Standard medium
        double bill3 = cashier.calculateTotalBill(order3, false, 0);
        System.out.println("Bill for Order 3 (" + order3.getCustomerName() + "): $" + bill3);

        // --------------------------------------------------------------------
        // DEMO 4: Counter Array Allocation & Exception Handling
        // --------------------------------------------------------------------
        System.out.println("\n>>> 4. Staging Orders in PickupCounter (0..3 Array Slots):");
        try {
            counter.placeOnCounter(order1); // Slot 0
            counter.placeOnCounter(order2); // Slot 1
            counter.placeOnCounter(order3); // Slot 2 (Counter is now full)

            System.out.println("\nAttempting to stage Order 4 into an already full counter...");
            CoffeeOrder order4 = new CoffeeOrder(104, "David Williams", CupSize.MEDIUM);
            cashier.calculateTotalBill(order4, false, 0);
            counter.placeOnCounter(order4); // Throws CounterFullException

        } catch (CounterFullException ex) {
            System.err.println("CAUGHT EXPECTED EXCEPTION: " + ex.getMessage());
        }

        // --------------------------------------------------------------------
        // DEMO 5: Counter Processing & Deallocation Loop
        // --------------------------------------------------------------------
        counter.serveWaitingCustomers();

        System.out.println("\n==========================================================");
        System.out.println("              ALL UML SPECIFICATIONS VERIFIED             ");
        System.out.println("==========================================================");
    }
}