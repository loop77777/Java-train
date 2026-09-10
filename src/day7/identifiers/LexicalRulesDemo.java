package day7.identifiers;

public class LexicalRulesDemo {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("     LEXICAL RULES FOR IDENTIFIERS DEMO           ");
        System.out.println("==================================================\n");

        // =========================================================================
        // 1. ALLOWED CHARACTERS & VALID STARTS (Letters, '_', '$')
        // =========================================================================
        int totalOrders = 120;           // Standard start with lowercase letter
        int _privateCounter = 5;         // Valid start with underscore (_)
        double $taxRate = 0.18;          // Valid start with dollar sign ($)
        String order123Ref = "TXN-901";  // Digits (0-9) allowed after the first character

        System.out.println("--- 1. Allowed Characters ---");
        System.out.println("totalOrders      : " + totalOrders);
        System.out.println("_privateCounter  : " + _privateCounter);
        System.out.println("$taxRate         : " + $taxRate);
        System.out.println("order123Ref      : " + order123Ref);

        // =========================================================================
        // 2. CASE SENSITIVITY DEMONSTRATION
        // =========================================================================
        // Both point to distinct slots in the symbol table / memory
        double totalSalary = 75000.00;
        double totalsalary = 50000.00;
        double TOTALSALARY = 95000.00;

        System.out.println("\n--- 2. Strict Case Sensitivity ---");
        System.out.println("totalSalary (camelCase) : Rs." + totalSalary);
        System.out.println("totalsalary (lowercase) : Rs." + totalsalary);
        System.out.println("TOTALSALARY (uppercase) : Rs." + TOTALSALARY);

        // =========================================================================
        // 3. UNLIMITED LENGTH DEMONSTRATION
        // =========================================================================
        int maximumCalculatedAllowedThresholdForDriverDispatchRadiusInKilometers = 25;

        System.out.println("\n--- 3. Technically Unlimited Length ---");
        System.out.println("Long Identifier Value   : "
                + maximumCalculatedAllowedThresholdForDriverDispatchRadiusInKilometers + " KM");

        /* =========================================================================
         * 4. COMPILER-ENFORCED LEXICAL RESTRICTIONS (INVALID - CAUSES COMPILE ERROR)
         * =========================================================================
         *
         * [Forbidden Start with Digits]
         * int 1stDriverId = 101;
         * // Compile Error: Syntax error on token "1stDriverId", delete this token
         *
         * [Keyword & Reserved Literal Restrictions]
         * int class = 404;
         * // Compile Error: 'class' is a keyword; invalid variable name
         * int goto = 10;
         * // Compile Error: 'goto' is a reserved keyword in Java
         * int true = 1;
         * // Compile Error: 'true' is a boolean literal token
         *
         * [Illegal Special Characters & Operators]
         * int total-amount = 500;
         * // Compile Error: '-' parsed as the subtraction arithmetic operator
         * int user@email = 99;
         * // Compile Error: '@' is an illegal character token
         */
    }
}


