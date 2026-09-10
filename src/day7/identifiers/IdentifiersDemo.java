//1. Identifiers
//An Identifier is a token in a Java program that acts as a unique developer-defined name for programmable elements: classes, interfaces, methods, variables, packages, and enums. At the compiler level, the lexer identifies these strings to build the Symbol Table.

package day7.identifiers;

// 1. Package Identifier: 'com', 'day11.demo', 'identifiers'
// 2. Interface Identifier: 'Deliverable'
interface Deliverable {
    // 3. Method Identifier: 'trackOrder'
    // 4. Parameter Identifier: 'orderReferenceId'
    void trackOrder(String orderReferenceId);
}

// 5. Enum Identifier: 'DeliveryStatus'
enum DeliveryStatus {
    // 6. Enum Constant Identifiers: 'PENDING', 'OUT_FOR_DELIVERY', 'DELIVERED'
    PENDING,
    OUT_FOR_DELIVERY,
    DELIVERED
}

// 7. Class Identifier: 'IdentifiersDemo'
public class IdentifiersDemo implements Deliverable {

    // =========================================================================
    // VALID IDENTIFIER RULES DEMONSTRATION
    // =========================================================================

    // Rule 1: Letters (A-Z, a-z), Currency Characters ($), and Underscores (_)
    int totalCount = 10;
    int $price = 499;
    int _retryAttempts = 3;
    int total_order_amount = 1500;

    // Rule 2: Digits allowed BUT NOT at the start
    String customer1Name = "Jyothi Prasad";
    String order2Id = "ORD-8821";

    // Rule 3: Case-sensitive uniqueness (stored as distinct symbol table entries)
    int deliveryTime = 30; // in minutes
    int DeliveryTime = 45;
    int DELIVERYTIME = 60;

    // Rule 4: Unlimited length (Java has no compile-time character length limit)
    int maximumThresholdLimitForOrderCancellationRefundInSeconds = 300;

    // =========================================================================
    // METHOD & PARAMETER IDENTIFIERS
    // =========================================================================

    @Override
    public void trackOrder(String orderReferenceId) {
        System.out.println("Tracking Order via Identifier: " + orderReferenceId);
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA IDENTIFIERS: WORKING DEMO             ");
        System.out.println("==================================================\n");

        IdentifiersDemo demo = new IdentifiersDemo();

        // Accessing valid identifiers
        System.out.println("Total Count ($ _ standard)       : " + demo.totalCount);
        System.out.println("Price with '$' prefix             : Rs." + demo.$price);
        System.out.println("Retry with '_' prefix             : " + demo._retryAttempts);
        System.out.println("Alphanumeric identifier (digit)   : " + demo.customer1Name);

        // Case-sensitivity demonstration
        System.out.println("\n--- Case Sensitivity Check ---");
        System.out.println("deliveryTime (lowercase 'd')      : " + demo.deliveryTime + " mins");
        System.out.println("DeliveryTime (PascalCase 'D')     : " + demo.DeliveryTime + " mins");
        System.out.println("DELIVERYTIME (ALL_CAPS)           : " + demo.DELIVERYTIME + " mins");

        // Interface method execution
        demo.trackOrder(demo.order2Id);

        // Enum identifier access
        DeliveryStatus currentStatus = DeliveryStatus.OUT_FOR_DELIVERY;
        System.out.println("Enum Status Identifier            : " + currentStatus);

        /* =====================================================================
         * COMPILER LEXICAL ERRORS (INVALID IDENTIFIERS - DO NOT UNCOMMENT)
         * =====================================================================
         *
         * 1. Cannot start with digits:
         *    int 1customer = 100;          // ERROR: Invalid token / Syntax error
         *
         * 2. Cannot contain reserved keywords:
         *    int class = 50;               // ERROR: 'class' is a reserved keyword
         *    int static = 20;              // ERROR: 'static' is a reserved keyword
         *
         * 3. Cannot contain special symbols other than '$' and '_':
         *    int total#count = 10;         // ERROR: Illegal character '#'
         *    int total-bill = 200;         // ERROR: Interpreted as minus operator (-)
         *    int user@name = 5;            // ERROR: Illegal character '@'
         *
         * 4. Cannot contain literal spaces:
         *    int total bill = 500;         // ERROR: Syntax error on token 'bill'
         */
    }
}

//Lexical Rules for Identifiers
//Allowed Characters: Must begin with an uppercase or lowercase letter (A-Z, a-z), an underscore (_), or a dollar sign ($). Subsequent characters can also include digits (0-9).
//
//Forbidden Starts: Cannot begin with a numeric digit.
//
//Keyword Restrictions: Reserved keywords (like class, public, int, goto) cannot be used as identifiers.
//
//Case Sensitivity: Java is strictly case-sensitive. totalSalary and totalsalary map to two completely different memory references or structural slots.
//
//Length: Technically unlimited, though convention dictates maintainable boundaries.

//package day7.day11.demo.lexical;

