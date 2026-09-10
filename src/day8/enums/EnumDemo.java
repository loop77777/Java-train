//14. Declaring Enums

//Introduced in Java 5 and fully leveraged in Java 7, an Enum is a specialized type-safe data construct used to group a strict, unalterable collection of predefined constants. Under the hood, an enum is compiled as an implicit subclass of java.lang.Enum. Enum options are type-safe instances rather than primitive integers or strings, which completely prevents arbitrary invalid values at compile time.

        package day8.enums;

// =========================================================================
// 1. ADVANCED TYPE-SAFE ENUM WITH FIELDS, CONSTRUCTOR, AND METHODS
// Under the hood: Extends java.lang.Enum<OrderStatus>
// Each constant (PLACED, COOKING, etc.) is a public static final instance.
// =========================================================================
enum OrderStatus {
    // Enum constants with customized constructor parameters
    PLACED("Order received by restaurant", 5),
    COOKING("Kitchen is preparing the meal", 20),
    OUT_FOR_DELIVERY("Driver on route to customer", 15),
    DELIVERED("Order handed over successfully", 0),
    CANCELLED("Order terminated", 0);

    // Immutable state variables for each enum instance
    private final String description;
    private final int estimatedDurationMinutes;

    // Enum constructors are implicitly 'private'
    OrderStatus(String description, int estimatedDurationMinutes) {
        this.description = description;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimatedDurationMinutes() {
        return estimatedDurationMinutes;
    }

    // Business logic within enum: Validates legal state transitions
    public boolean canTransitionTo(OrderStatus nextStatus) {
        switch (this) {
            case PLACED:
                return nextStatus == COOKING || nextStatus == CANCELLED;
            case COOKING:
                return nextStatus == OUT_FOR_DELIVERY || nextStatus == CANCELLED;
            case OUT_FOR_DELIVERY:
                return nextStatus == DELIVERED;
            case DELIVERED:
            case CANCELLED:
                return false; // Terminal states
            default:
                return false;
        }
    }
}

// =========================================================================
// 2. ENUM WITH CONSTANT-SPECIFIC CLASS BODIES (Polymorphic Behavior)
// =========================================================================
enum DeliverySurgePricing {
    REGULAR {
        @Override
        public double calculateSurgeCharge(double baseFare) {
            return baseFare; // 1.0x
        }
    },
    RAIN_SURGE {
        @Override
        public double calculateSurgeCharge(double baseFare) {
            return baseFare * 1.35; // 35% additional charge
        }
    },
    PEAK_HOUR {
        @Override
        public double calculateSurgeCharge(double baseFare) {
            return baseFare * 1.50; // 50% additional charge
        }
    };

    // Abstract method implemented individually by each enum constant
    public abstract double calculateSurgeCharge(double baseFare);
}

// =========================================================================
// 3. MAIN RUNNER DEMONSTRATING ENUM PROPERTIES & SWITCH INTEGRATION
// =========================================================================
public class EnumDemo {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("        JAVA TYPE-SAFE ENUMS WORKING DEMO         ");
        System.out.println("==================================================\n");

        // 1. Type Safety & Switch Case Evaluation
        OrderStatus currentStatus = OrderStatus.COOKING;
        System.out.println("Current Status : " + currentStatus);
        System.out.println("Description    : " + currentStatus.getDescription());
        System.out.println("Est. Duration  : " + currentStatus.getEstimatedDurationMinutes() + " mins\n");

        System.out.println("--- Switch Statement Evaluation ---");
        switch (currentStatus) {
            case PLACED:
                System.out.println("Action: Alert kitchen to review ticket.");
                break;
            case COOKING:
                System.out.println("Action: Kitchen stove/grill active.");
                break;
            case OUT_FOR_DELIVERY:
                System.out.println("Action: Enable GPS tracker on driver.");
                break;
            case DELIVERED:
                System.out.println("Action: Request customer rating.");
                break;
            case CANCELLED:
                System.out.println("Action: Process refund queue.");
                break;
        }

        // 2. State Transition Validation
        System.out.println("\n--- State Transition Safety Checks ---");
        System.out.println("Can transition COOKING -> OUT_FOR_DELIVERY? "
                + currentStatus.canTransitionTo(OrderStatus.OUT_FOR_DELIVERY));
        System.out.println("Can transition COOKING -> DELIVERED?        "
                + currentStatus.canTransitionTo(OrderStatus.DELIVERED));

        // 3. Iterating values() and ordinal()
        System.out.println("\n--- Built-in Enum Methods (values & ordinal) ---");
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println("Constant: " + status.name()
                    + " | Ordinal Index: " + status.ordinal());
        }

        // 4. Polymorphic Enum Methods
        System.out.println("\n--- Polymorphic Constant-Specific Behavior ---");
        double baseRate = 80.0;
        for (DeliverySurgePricing surge : DeliverySurgePricing.values()) {
            System.out.println(surge.name() + " -> Total Fare: Rs."
                    + surge.calculateSurgeCharge(baseRate));
        }

        /* =====================================================================
         * COMPILE-TIME TYPE SAFETY CHECKS (DO NOT UNCOMMENT)
         * =====================================================================
         *
         * // 1. Arbitrary strings or integers are rejected:
         * // OrderStatus invalid = "PREPARING"; // COMPILE ERROR: Type mismatch
         * // OrderStatus invalidInt = 2;        // COMPILE ERROR: Type mismatch
         *
         * // 2. Enums cannot be instantiated via new:
         * // OrderStatus direct = new OrderStatus("Custom", 10); // COMPILE ERROR: Cannot instantiate
         */
    }
}