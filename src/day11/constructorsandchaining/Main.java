package day11.constructorsandchaining;

import java.util.UUID;

// ==========================================================
// 1. DATA ENTITY: Demonstrates Constructors & Chaining
// ==========================================================
class BankAccount {
    private final String accountNumber;
    private String accountHolder;
    private double balance;

    // Explicit Default/No-Arg Constructor
    public BankAccount() {
        // Chains to the two-parameter constructor
        this("Unassigned", 0.0);
    }

    // Overloaded Constructor (Single Parameter)
    public BankAccount(String accountHolder) {
        // Chains to the two-parameter constructor with default balance
        this(accountHolder, 0.0);
    }

    // Primary Parameterized Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountNumber = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.accountHolder = accountHolder;
        this.balance = Math.max(initialBalance, 0.0); // Simple invariant validation
    }

    // State Mutation Methods
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
}

// ==========================================================
// 2. IMMUTABLE RECORD: Returned from Methods
// ==========================================================
class TransactionReceipt {
    private final String transactionId;
    private final String sourceAccount;
    private final String targetAccount;
    private final double amount;
    private final boolean successful;

    public TransactionReceipt(String sourceAccount, String targetAccount, double amount, boolean successful) {
        this.transactionId = UUID.randomUUID().toString();
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.successful = successful;
    }

    public void printReceipt() {
        System.out.println("------------- TRANSACTION RECEIPT -------------");
        System.out.println("Receipt ID : " + transactionId);
        System.out.println("Status     : " + (successful ? "SUCCESS" : "FAILED"));
        System.out.println("Source Acc : " + sourceAccount);
        System.out.println("Target Acc : " + targetAccount);
        System.out.println("Amount     : $" + String.format("%.2f", amount));
        System.out.println("-----------------------------------------------");
    }
}

// ==========================================================
// 3. SERVICE CLASS: High Cohesion, Loose Coupling
//    Demonstrates passing objects and returning objects
// ==========================================================
class TransferService {

    /**
     * Accepts object references as parameters (from, to),
     * mutates their underlying states safely,
     * and returns a newly constructed object (day11.constructorsandchaining.TransactionReceipt).
     */
    public TransactionReceipt executeTransfer(BankAccount from, BankAccount to, double amount) {
        // Guard against null objects
        if (from == null || to == null) {
            return new TransactionReceipt("N/A", "N/A", amount, false);
        }

        // Check if withdrawal from source succeeds
        boolean debitSuccess = from.withdraw(amount);

        if (debitSuccess) {
            // Pass-by-value of reference: mutating the 'to' object directly on the Heap
            to.deposit(amount);
            return new TransactionReceipt(from.getAccountNumber(), to.getAccountNumber(), amount, true);
        } else {
            // Transfer failed due to insufficient funds or bad amount
            return new TransactionReceipt(from.getAccountNumber(), to.getAccountNumber(), amount, false);
        }
    }
}

// ==========================================================
// 4. MAIN DRIVER: Instantiation and Execution
// ==========================================================
public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. CONSTRUCTOR & INSTANTIATION DEMO ===");
        // Default constructor invocation
        BankAccount acc1 = new BankAccount();
        System.out.println("acc1 (Default) -> Holder: " + acc1.getAccountHolder() + ", Balance: $" + acc1.getBalance());

        // Single-argument overloaded constructor
        BankAccount acc2 = new BankAccount("Alice Smith");
        System.out.println("acc2 (1-Param) -> Holder: " + acc2.getAccountHolder() + ", Balance: $" + acc2.getBalance());

        // Fully parameterized constructor
        BankAccount acc3 = new BankAccount("Bob Jones", 1000.00);
        System.out.println("acc3 (2-Param) -> Holder: " + acc3.getAccountHolder() + ", Balance: $" + acc3.getBalance());

        System.out.println("\n=== 2. PASSING & RETURNING OBJECTS DEMO ===");
        // High cohesion: day11.constructorsandchaining.TransferService handles only transactions
        TransferService transferService = new TransferService();

        System.out.println("Initial Balances:");
        System.out.println("Bob (Source):   $" + acc3.getBalance());
        System.out.println("Alice (Target): $" + acc2.getBalance());

        // Passing 'acc3' and 'acc2' as arguments; receiving 'receipt1' as returned object
        System.out.println("\nExecuting Transfer: $350.00 from Bob to Alice...");
        TransactionReceipt receipt1 = transferService.executeTransfer(acc3, acc2, 350.00);
        receipt1.printReceipt();

        System.out.println("Balances Post-Transfer:");
        System.out.println("Bob (Source):   $" + acc3.getBalance());
        System.out.println("Alice (Target): $" + acc2.getBalance());

        System.out.println("\nExecuting Transfer: $9000.00 from Bob to Alice (Exceeds Balance)...");
        TransactionReceipt receipt2 = transferService.executeTransfer(acc3, acc2, 9000.00);
        receipt2.printReceipt();

        System.out.println("Final Balances (Unchanged after failed attempt):");
        System.out.println("Bob (Source):   $" + acc3.getBalance());
        System.out.println("Alice (Target): $" + acc2.getBalance());
    }
}