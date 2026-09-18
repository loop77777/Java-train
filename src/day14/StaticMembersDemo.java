package day14;//8 & 9. Declaring and Implementing Interfaces
//
//An interface acts as a formal contract that enforces behaviors on implementing classes. In Java 7, interfaces represent absolute structural abstraction. They are prohibited from containing concrete method implementations, constructor code, or operational variables.
//When you define an interface signature without explicit modifiers, the Java compiler automatically prepends public abstraction keywords behind the scenes:
//Java
//interface FunctionalContract { void process(); }
/// / The Java compiler automatically expands this definition to:
//public abstract interface FunctionalContract { public abstract void process(); }


//10. Interface Constants
//
//Interfaces cannot maintain instance variables. Any field variables declared directly inside an interface body are implicitly public static final constants, regardless of whether you include those modifiers in the source code.
//Because these constants are static and immutable, they reside permanently in the JVM's Metaspace area rather than within individual objects on the heap. They are bound globally to the interface class name container itself. Any attempt to modify an interface constant at runtime will throw an explicit compilation error.

//11. Static Variables and Methods
//
//The static modifier detaches class variables and operational routines from individual object instances.
//•	Static Variables: Reside in a single memory slot within the JVM's Metaspace area. This single state variable is shared globally across all generated instances of that class.
//•	Static Methods: Belong to the class container configuration itself rather than individual object contexts. They can be invoked directly using the class name without instantiating any objects. Because static methods execute without an instance reference, they cannot access non-static instance fields or use the this keyword.

// ============================================================================
// 1. CLASS WITH STATIC & INSTANCE MEMBERS
// ============================================================================
class BankAccount {
    // Instance Variables: Stored inside each separate object on the Heap
    private final String accountHolder;
    private double balance;

    // Static Variables: One single copy shared across all instances
    // Stored with the class metadata in Metaspace/Class data
    public static String bankName = "Global Apex Bank";
    private static int totalAccountsCreated = 0;
    private static double totalFundsDeposited = 0.0;

    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;

        // Mutating shared static state across all instances
        totalAccountsCreated++;
        totalFundsDeposited += initialDeposit;
    }

    // Instance Method: Has an implicit 'this' reference to the calling object
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            totalFundsDeposited += amount; // Instance methods can freely access static fields
            System.out.printf("[%s] Deposited $%.2f. New personal balance: $%.2f%n",
                    this.accountHolder, amount, this.balance);
        }
    }

    public void printAccountStatement() {
        System.out.printf("Holder: %-10s | Balance: $%,10.2f | Bank: %s%n",
                this.accountHolder, this.balance, BankAccount.bankName);
    }

    // ========================================================================
    // Static Utility Methods: Belong to the Class, not an object
    // ========================================================================
    public static void displayBankAnalytics() {
        // Can directly read static variables without any instance existing
        System.out.println("\n--- [" + bankName + " Global Audit] ---");
        System.out.println("Total Accounts Managed : " + totalAccountsCreated);
        System.out.printf("Total Vault Reserves   : $%,.2f%n", totalFundsDeposited);

        // --------------------------------------------------------------------
        // STATIC CONSTRAINTS CHECK (What will NOT compile):
        // --------------------------------------------------------------------
        // System.out.println(this.balance);
        // COMPILER ERROR: 'this' cannot be referenced from a static context

        // System.out.println(accountHolder);
        // COMPILER ERROR: non-static variable accountHolder cannot be referenced from a static context
    }

    // Pure static calculation utility (independent of state)
    public static double computeEstimatedInterest(double principal, double annualRate, int years) {
        return principal * Math.pow(1 + (annualRate / 100.0), years) - principal;
    }
}

// ============================================================================
// 2. EXECUTION DRIVER
// ============================================================================
public class StaticMembersDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. CALLING STATIC METHODS WITHOUT INSTANTIATION ===");
        // Static methods and variables can run before any object exists on the heap
        System.out.println("Bank Entity Name: " + BankAccount.bankName);
        BankAccount.displayBankAnalytics();

        double projectedInterest = BankAccount.computeEstimatedInterest(10000, 5.5, 3);
        System.out.printf("Projected 3-Year Interest on $10k @ 5.5%%: $%,.2f%n", projectedInterest);


        System.out.println("\n=== 2. CREATING INSTANCES & OBSERVING SHARED STATIC STATE ===");
        BankAccount acc1 = new BankAccount("Alice", 5000.0);
        BankAccount acc2 = new BankAccount("Bob", 12000.0);
        BankAccount acc3 = new BankAccount("Charlie", 3500.0);

        // Display individual account details
        acc1.printAccountStatement();
        acc2.printAccountStatement();
        acc3.printAccountStatement();

        // Mutate instance state and check global ripple effect
        System.out.println("\nDepositing funds into Alice's account...");
        acc1.deposit(2500.0);


        System.out.println("\n=== 3. GLOBAL AUDIT POST-TRANSACTIONS ===");
        // The static counter accumulated updates from all 3 separate heap objects
        BankAccount.displayBankAnalytics();


        System.out.println("\n=== 4. DEMONSTRATING SHARED VARIABLE MUTABILITY ===");
        // Changing a static field once changes it for every existing and future instance
        BankAccount.bankName = "Apex International Trust";
        System.out.println("Renamed Bank via static reference: " + BankAccount.bankName);

        System.out.println("Verifying name viewed from Bob's statement:");
        acc2.printAccountStatement();
    }
}