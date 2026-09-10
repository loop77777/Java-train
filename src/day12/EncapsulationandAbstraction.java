package day12;//Encapsulation and Abstraction
//Encapsulation: Bundles instance variables with public accessor/mutator methods while hiding internal data structures behind private visibility to protect class invariants.
//
//Abstraction: Hides execution complexity by defining contracts through abstract classes or interfaces, exposing only high-level operations.

// Abstraction: Defines contract without operational details
abstract class Account {
    private final String accountNumber; // Encapsulated internal state
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    // Abstract method must be implemented by concrete subclasses
    public abstract void withdraw(double amount);
}

// Concrete Implementation
class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        // Encapsulation preserves valid business state
        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", Remaining: " + balance);
        } else {
            System.out.println("Withdrawal rejected: Minimum balance violated.");
        }
    }
}