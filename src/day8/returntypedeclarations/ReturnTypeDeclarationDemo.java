package day8.returntypedeclarations;
// Return Type Declarations

// The declared return type in a method signature acts as a compiler contract. The syntax forces the developer to provide a type descriptor right before the method name. The compiler evaluates every exit path inside the method block to guarantee a return statement fires with a value that is fully compatible with the declared type.

import day8.modifiers.DeliveryVehicle;

public class ReturnTypeDeclarationDemo {

    // =========================================================================
    // 1. PRIMITIVE WIDENING / IMPLICIT CONVERSION IN RETURN
    // =========================================================================
    // Contract declares 'double', but returns an 'int' (500) -> Compiler auto-widens
    public double calculateBaseFare() {
        int flatRate = 500;
        return flatRate; // Legal: int implicitly converts to double (500.0)
    }

    // =========================================================================
    // 2. EXHAUSTIVE CONTROL PATH ANALYSIS (Compiler Contract Enforcement)
    // =========================================================================
    // Every possible logical branch MUST terminate in a valid return of declared type
    public String evaluateOrderTier(double totalBill) {
        if (totalBill >= 2000.0) {
            return "PLATINUM_TIER";
        } else if (totalBill >= 1000.0) {
            return "GOLD_TIER";
        } else if (totalBill >= 500.0) {
            return "SILVER_TIER";
        } else {
            return "STANDARD_TIER"; // Eliminating this causes: "This method must return a result of type String"
        }
    }

    // =========================================================================
    // 3. EARLY EXIT PATTERNS (Guard Clauses)
    // =========================================================================
    public String processRefund(boolean isCancelled, boolean isPaid) {
        // Guard Clause 1: Early exit
        if (!isCancelled) {
            return "NO_REFUND_REQUIRED";
        }

        // Guard Clause 2: Early exit
        if (!isPaid) {
            return "ORDER_UNPAID_NO_REFUND";
        }

        // day11.constructorsandchaining.Main execution path
        return "REFUND_PROCESSED_SUCCESSFULLY";
    }

    // =========================================================================
    // 4. VOID METHOD RETURN RESTRICTIONS
    // =========================================================================
    public void logAuditTrail(String eventName, boolean shouldLog) {
        if (!shouldLog) {
            return; // Legal: Exits early without pushing data to operand stack
        }

        System.out.println("[AUDIT LOG] Event logged: " + eventName);
        // return "COMPLETED"; // Compile Error: Void methods cannot return a value
    }

    // =========================================================================
    // 5. UNREACHABLE CODE COMPILER ENFORCEMENT
    // =========================================================================
    public int computeLoyaltyPoints(int points) {
        return points * 2;
        // int bonus = 50; // Compile Error: Unreachable code detected by compiler
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("    RETURN TYPE DECLARATION CONTRACTS: DEMO       ");
        System.out.println("==================================================\n");

        ReturnTypeDeclarationDemo demo = new ReturnTypeDeclarationDemo();

        // 1. Implicit widening test
        double baseFare = demo.calculateBaseFare();
        System.out.println("1. Auto-widened Return (int -> double) : Rs." + baseFare);

        // 2. Exhaustive branch execution
        String tier = demo.evaluateOrderTier(1450.00);
        System.out.println("2. Exhaustive Branch Evaluation        : " + tier);

        // 3. Early return / Guard clause test
        String refundStatus = demo.processRefund(true, true);
        System.out.println("3. Early Guard Clause Result           : " + refundStatus);

        // 4. Void method early exit execution
        System.out.print("4. Void Execution With Early Exit      : ");
        demo.logAuditTrail("USER_LOGIN_EVENT", true);
        demo.logAuditTrail("IGNORED_EVENT", false); // Exits immediately at 'return;'
    }
}



