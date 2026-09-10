//2. Sun's Java Code Conventions
//
//Code conventions are non-compiler-enforced design standards established originally by Sun Microsystems. Violating them will not cause a syntax error, but it will systematically fail corporate static analysis tools (like SonarQube). They guarantee readability and seamless onboarding across large engineering teams.

package day7.conventions; // 1. Package: All lowercase, reverse domain structure

import java.util.List;

/**
 * 2. Classes & Interfaces: PascalCase (UpperCamelCase), noun-oriented.
 * Demonstrates Sun's Java Code Conventions across all architectural layers.
 */
public class FoodOrderService {

    // 3. Constants (static final): UPPER_SNAKE_CASE (ALL_CAPS separated by underscores)
    public static final double DEFAULT_TAX_RATE = 0.18;
    public static final int MAX_RETRY_ATTEMPTS = 3;

    // 4. Instance / Field Variables: camelCase (mixedCase), noun/state descriptive
    private String customerName;
    private double orderTotal;
    private boolean isPriorityDelivery; // Boolean flags typically prefixed with 'is', 'has', or 'can'

    // Constructor: Matches class name in PascalCase
    public FoodOrderService(String customerName, double orderTotal, boolean isPriorityDelivery) {
        this.customerName = customerName;
        this.orderTotal = orderTotal;
        this.isPriorityDelivery = isPriorityDelivery;
    }

    /**
     * 5. Methods: camelCase (mixedCase), verb-noun action pairs.
     * 6. Parameters: camelCase, contextual and meaningful.
     */
    public double calculateFinalPayableAmount(double discountCouponPercentage) {
        // 7. Local Variables: camelCase, descriptive (avoid single-letter names like 'x', 'd')
        double discountAmount = this.orderTotal * (discountCouponPercentage / 100.0);
        double netBill = this.orderTotal - discountAmount;
        double taxAmount = netBill * DEFAULT_TAX_RATE;

        return netBill + taxAmount;
    }

    public void processDispatchedItems(List<String> cartItems) {
        // 8. Loop Counters: Single-letter variables ('i', 'j', 'k') permitted exclusively in short loops
        for (int i = 0; i < cartItems.size(); i++) {
            String currentItem = cartItems.get(i);
            System.out.println("Processing item [" + i + "]: " + currentItem);
        }
    }

    // Getters and Setters: Standard JavaBean camelCase conventions
    public String getCustomerName() {
        return customerName;
    }

    public boolean isPriorityDelivery() {
        return isPriorityDelivery;
    }

    public static void main(String[] args) {
        FoodOrderService service = new FoodOrderService("Jyothi Prasad", 1200.00, true);
        double finalPayable = service.calculateFinalPayableAmount(10.0);

        System.out.println("Customer       : " + service.getCustomerName());
        System.out.println("Final Payable  : Rs." + String.format("%.2f", finalPayable));
        System.out.println("Default Tax    : " + (DEFAULT_TAX_RATE * 100) + "%");
    }
}