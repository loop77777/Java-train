package day7.declaration;

//5. Source File Declaration Rules
//Java enforces rigid, deterministic compilation rules governing source file structures:
//
//A source file can contain multiple classes, but it can have at most one public class.
//
//If a source file contains a public class, the filename must exactly match the name of that public class (case-sensitive).
//
//If a file has no public class, it can be named anything.
//
//The package statement must be the absolute first line of non-commented code, followed sequentially by import statements, and then followed by class definitions.


// =========================================================================
// RULE 4: 1st Statement -> Package statement (must precede imports & code)
// =========================================================================


// =========================================================================
// RULE 4: 2nd Statement -> Import statements (follow package declarations)
// =========================================================================
import java.time.LocalDateTime;
import java.util.UUID;
// =========================================================================
// RULE 1 & 2: At most ONE public class per file. Filename MUST match it.
// File Name: OrderProcessEngine.java == public class OrderProcessEngine
// =========================================================================
public class OrderProcessEngine {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("     JAVA SOURCE FILE DECLARATION RULES DEMO      ");
        System.out.println("==================================================");

        // Instantiating the public class
        OrderProcessEngine engine = new OrderProcessEngine();
        engine.bootstrapEngine();

        // Instantiating non-public package-private classes located in this SAME file
        InvoiceGenerator invoiceGen = new InvoiceGenerator();
        invoiceGen.generateReceipt("ORD-8921", 850.0);

        DeliveryNotifier notifier = new DeliveryNotifier();
        notifier.dispatchAlert("Jyothi Prasad", "Out for delivery");
    }

    public void bootstrapEngine() {
        System.out.println("\n[OrderProcessEngine] Core Engine Started successfully.");
    }
}
// =========================================================================
// RULE 1: Multiple non-public classes ARE permitted in the same source file
// =========================================================================
class InvoiceGenerator {

    void generateReceipt(String orderId, double amount) {
        String receiptId = "INV-" + UUID.randomUUID().toString().substring(0, 6);
        System.out.println("[InvoiceGenerator] Generated " + receiptId + " for Order #"
                + orderId + " | Total: Rs." + amount + " at " + LocalDateTime.now());
    }
}

class DeliveryNotifier {

    void dispatchAlert(String recipient, String message) {
        System.out.println("[DeliveryNotifier] SMS dispatched to: "
                + recipient + " -> Message: " + message);
    }
}

