package day14;// ============================================================================
// 1. INTERFACE DECLARATIONS
// ============================================================================

// Demonstrates compiler keyword expansion:
// The compiler automatically treats this as: public abstract interface TransactionProcessor
interface TransactionProcessor {
    // 1. Interface Constant:
    // The compiler automatically expands this to: public static final double TRANSACTION_FEE_RATE = 0.02;
    double TRANSACTION_FEE_RATE = 0.02;

    // 2. Abstract Contract Methods:
    // The compiler automatically expands this to: public abstract boolean process(double amount);
    boolean process(double amount);

    // The compiler automatically expands this to: public abstract String getProcessorName();
    String getProcessorName();
}

// A second interface demonstrating multiple interface inheritance
interface Auditable {
    // Automatically: public abstract void generateAuditReport(String transactionId);
    void generateAuditReport(String transactionId);
}

// ============================================================================
// 2. CONCRETE IMPLEMENTATIONS (Signing the Contract)
// ============================================================================

// Implementation 1: CreditCardService implements both interfaces
class CreditCardService implements TransactionProcessor, Auditable {
    private final String cardNumber;

    public CreditCardService(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Implementing the contract methods (MUST be declared 'public')
    @Override
    public boolean process(double amount) {
        double fee = amount * TRANSACTION_FEE_RATE; // Accessing interface constant directly
        double total = amount + fee;
        System.out.printf("[%s] Charged $%.2f (Amount: $%.2f + Fee: $%.2f) to card ending in %s%n",
                getProcessorName(), total, amount, fee, cardNumber.substring(cardNumber.length() - 4));
        return true;
    }

    @Override
    public String getProcessorName() {
        return "CreditCardGateway";
    }

    @Override
    public void generateAuditReport(String transactionId) {
        System.out.println("[Audit Log] Transaction ID: " + transactionId + " recorded for CC Gateway.");
    }
}

// Implementation 2: CryptoService implements TransactionProcessor with custom rules
class CryptoService implements TransactionProcessor {
    private final String walletAddress;

    public CryptoService(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public boolean process(double amount) {
        System.out.printf("[%s] Broadcasted $%.2f transfer from wallet: %s%n",
                getProcessorName(), amount, walletAddress);
        return true;
    }

    @Override
    public String getProcessorName() {
        return "CryptoLedgerGateway";
    }
}

// ============================================================================
// 3. EXECUTION DRIVER
// ============================================================================
public class InterfaceContractDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. INTERFACE CONSTANTS DEMO ===");
        // Accessed via interface name because it is implicitly static final
        System.out.println("Standard Interface Fee Rate: " + (TransactionProcessor.TRANSACTION_FEE_RATE * 100) + "%");

        // TransactionProcessor.TRANSACTION_FEE_RATE = 0.05; // COMPILATION ERROR: cannot assign a value to final variable


        System.out.println("\n=== 2. POLYMORPHISM THROUGH INTERFACE CONTRACTS ===");
        // Polymorphic reference: Program to an interface, not an implementation
        TransactionProcessor processor1 = new CreditCardService("4111-2222-3333-9876");
        TransactionProcessor processor2 = new CryptoService("0x9FA8...33E2");

        // Dynamic dispatch routes to the concrete class's implementation
        processor1.process(250.00);
        processor2.process(1200.00);


        System.out.println("\n=== 3. MULTIPLE INTERFACE IMPLEMENTATION & CASTING ===");
        // processor1 is typed as TransactionProcessor. To invoke Auditable methods:
        if (processor1 instanceof Auditable auditableRef) {
            auditableRef.generateAuditReport("TXN-90210");
        }

        // processor2 does not implement Auditable:
        if (processor2 instanceof Auditable) {
            System.out.println("CryptoService is auditable.");
        } else {
            System.out.println("CryptoService does not implement Auditable interface.");
        }
    }
}