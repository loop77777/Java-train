package day8.classmembers;

//Declare Class Members

//A class definition acts as an organizational container for five fundamental types of internal code elements, collectively referred to as Class Members:



//Fields / Variables: Capture the explicit state attributes of instances.



//        Methods: Implement the operations and behaviors.
//      Constructors: Initialize instance variables during allocation.
//    Blocks: Code segments executed during class loading (static {}) or instantiation ({}).



//Nested Classes: Inner classes grouped inside for context-specific operations.



import java.util.UUID;

public class OrderAccount {

    // =========================================================================
    // 1. FIELDS / VARIABLES (Instance & Static State)
    // =========================================================================
    public static final String PLATFORM_NAME = "QuickServe Engine"; // Static field (Class-level)
    private static int totalOrdersCreated = 0;                     // Static counter

    private final String orderId;    // Instance field (Immutable)
    private String customerName;     // Instance field (Mutable)
    private double orderAmount;      // Instance field (Mutable)

    // =========================================================================
    // 2. BLOCKS (Static Initialization & Instance Initialization)
    // =========================================================================
    // A. Static Block: Executes ONCE when the class is first loaded into the JVM by the ClassLoader
    static {
        System.out.println("[STATIC BLOCK] Class 'OrderAccount' loaded into JVM Method Area.");
        System.out.println("[STATIC BLOCK] Initialized platform: " + PLATFORM_NAME);
    }

    // B. Instance Initialization Block (IIB): Executes EVERY TIME a new object is created, BEFORE the constructor
    {
        totalOrdersCreated++;
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        System.out.println("\n[INSTANCE BLOCK] Preparing memory for Order #" + totalOrdersCreated + " (ID: " + this.orderId + ")");
    }

    // =========================================================================
    // 3. CONSTRUCTORS (State Initialization during heap allocation)
    // =========================================================================
    public OrderAccount(String customerName, double orderAmount) {
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        System.out.println("[CONSTRUCTOR] Initialized instance for Customer: " + this.customerName + " | Amount: Rs." + this.orderAmount);
    }

    // =========================================================================
    // 4. METHODS (Behavior & State Mutation)
    // =========================================================================
    public void applyDiscount(double discountPercentage) {
        double discount = (this.orderAmount * discountPercentage) / 100.0;
        this.orderAmount -= discount;
        System.out.println("[METHOD] Applied " + discountPercentage + "% discount. New Total: Rs." + this.orderAmount);
    }

    public static int getTotalOrdersCount() {
        return totalOrdersCreated;
    }

    // =========================================================================
    // 5. NESTED CLASSES (Static Nested & Inner Member Classes)
    // =========================================================================
    // A. Inner (Non-Static) Member Class: Bound to the enclosing instance's state
    public class OrderAuditLog {
        public void printAuditSummary() {
            // Direct access to outer instance variables (orderId, customerName)
            System.out.println("[INNER CLASS] Audit Record -> Order: " + orderId + " | Customer: " + customerName);
        }
    }

    // B. Static Nested Class: Logically grouped; does NOT hold an implicit reference to outer instances
    public static class OrderValidator {
        public static boolean isValidOrderAmount(double amount) {
            return amount > 0;
        }
    }

    // =========================================================================
    // MAIN RUNNER
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA CLASS MEMBERS ARCHITECTURE DEMO       ");
        System.out.println("==================================================");

        // 1. Static Nested Class Method execution (No outer instance needed)
        boolean isValid = OrderAccount.OrderValidator.isValidOrderAmount(450.0);
        System.out.println("\n[STATIC NESTED CLASS] Is Rs.450 valid? " + isValid);

        // 2. Instantiating Object 1 (Triggers Instance Block -> Constructor)
        OrderAccount order1 = new OrderAccount("Jyothi Prasad", 750.0);
        order1.applyDiscount(10.0); // Method call

        // 3. Instantiating Inner Class (Requires an active outer instance)
        OrderAccount.OrderAuditLog audit1 = order1.new OrderAuditLog();
        audit1.printAuditSummary();

        // 4. Instantiating Object 2
        OrderAccount order2 = new OrderAccount("Sunil Kumar", 1200.0);

        System.out.println("\n==================================================");
        System.out.println("Total Orders Managed: " + OrderAccount.getTotalOrdersCount());
        System.out.println("==================================================");
    }
}

