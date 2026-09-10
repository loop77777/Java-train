
//6. Modularity
//Modularity is the system architectural practice of breaking a vast monolithic software application into completely isolated, independent, loosely coupled subsystems or classes. Each class focuses squarely on a unique, granular slice of operations. This completely isolates modifications, enables unit testing, and ensures code reuse.

package day7.modularity;

import java.util.UUID;

// =========================================================================
// MODULE 1: Data Contract / Entity (Single Responsibility: State Storage)
// =========================================================================
class Order {
    private final String orderId;
    private final String itemName;
    private final double basePrice;
    private boolean isPaid;

    public Order(String itemName, double basePrice) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.isPaid = false;
    }

    public String getOrderId() { return orderId; }
    public String getItemName() { return itemName; }
    public double getBasePrice() { return basePrice; }
    public boolean isPaid() { return isPaid; }
    public void markPaid() { this.isPaid = true; }
}

// =========================================================================
// MODULE 2: Taxation & Pricing Subsystem (Single Responsibility: Financial Calculation)
// =========================================================================
class TaxCalculatorService {
    private static final double GST_RATE = 0.05; // 5% GST for Food Delivery

    public double computeTotalPayable(double basePrice) {
        return basePrice + (basePrice * GST_RATE);
    }
}

// =========================================================================
// MODULE 3: Payment Subsystem (Single Responsibility: Fund Transaction)
// =========================================================================
class PaymentGatewayService {
    public boolean processPayment(Order order, double finalAmount) {
        System.out.println("[PaymentGateway] Processing charge of Rs." + String.format("%.2f", finalAmount)
                + " for " + order.getOrderId() + "...");
        order.markPaid();
        return true;
    }
}

// =========================================================================
// MODULE 4: Notification Subsystem (Single Responsibility: Communication Channel)
// =========================================================================
class NotificationService {
    public void sendOrderConfirmation(Order order) {
        System.out.println("[NotificationService] SMS Alert: Order " + order.getOrderId()
                + " (" + order.getItemName() + ") confirmed! Cooking starts now.");
    }
}

// =========================================================================
// MODULE 5: Delivery Dispatch Subsystem (Single Responsibility: Driver Assignment)
// =========================================================================
class DispatchService {
    public void assignDeliveryPartner(Order order) {
        System.out.println("[DispatchService] Delivery Partner Sunil (DRV-2) assigned to pickup "
                + order.getOrderId() + ".");
    }
}


// =========================================================================
// ORCHESTRATION LAYER: Decoupled Controller binding independent modules
// =========================================================================
public class ModularityDemo {

    private final TaxCalculatorService taxService;
    private final PaymentGatewayService paymentService;
    private final NotificationService notificationService;
    private final DispatchService dispatchService;

    // Modular Dependency Injection via Constructor
    public ModularityDemo(TaxCalculatorService taxService,
                          PaymentGatewayService paymentService,
                          NotificationService notificationService,
                          DispatchService dispatchService) {
        this.taxService = taxService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.dispatchService = dispatchService;
    }

    public void checkout(String item, double price) {
        Order order = new Order(item, price);
        System.out.println("Initiating Checkout for: " + order.getItemName() + " [Base: Rs." + order.getBasePrice() + "]");

        // Step 1: Calculate Total
        double finalBill = taxService.computeTotalPayable(order.getBasePrice());

        // Step 2: Charge Payment
        boolean paymentSuccess = paymentService.processPayment(order, finalBill);

        // Step 3: Trigger Dependent Modular Subsystems
        if (paymentSuccess) {
            notificationService.sendOrderConfirmation(order);
            dispatchService.assignDeliveryPartner(order);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        SYSTEM ARCHITECTURAL MODULARITY DEMO      ");
        System.out.println("==================================================\n");

        // Independent instantiation allows isolated unit testing and swapping
        TaxCalculatorService taxService = new TaxCalculatorService();
        PaymentGatewayService paymentService = new PaymentGatewayService();
        NotificationService notificationService = new NotificationService();
        DispatchService dispatchService = new DispatchService();

        ModularityDemo foodApp = new ModularityDemo(
                taxService, paymentService, notificationService, dispatchService
        );

        foodApp.checkout("Special Chicken Biryani", 350.00);
    }
}
