package day2;//4. Array Declaration, Construction, and Initialization
//Technical Deep Dive
//Arrays in Java are handled strictly as dynamically allocated objects on the heap, regardless of whether they hold primitive types or object references. Creating and deploying an day2.array requires three explicit structural phases managed by the JVM memory subsystem:
//
//Declaring an Array: This step defines the day2.array's reference variable name and specifies the element type it will hold. No memory is allocated on the heap for the elements yet. The reference variable sits on the day2.stack frame containing a value of null.
//
//
//int[] ageArray; // Preferred clean syntax
//int ageArray[]; // Legal, legacy C-style syntax
//Note: You can never specify an explicit size inside the brackets during declaration (e.g., int[5] badArray; is an immediate compilation failure).
//
//Constructing an Array: This phase uses the explicit new keyword to allocate a fixed-size block of memory out on the heap. This is where the size constraint is locked permanently into the day2.array object's header.
//
//
//ageArray = new int[4];
//The moment this line runs, the JVM populates every slot inside the newly created heap day2.array object with its type-specific default values (e.g., all slots become 0 for an int day2.array).

// Top-level Standalone Class 1: The Object residing in Heap Memory
class BankAccount {
    String accountHolder;
    double balance;

    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
}

// Top-level Standalone Class 2: The Driver / Execution Engine
public class PassByValueSimpleDemo {

    // 1. Primitive Example: Manipulates only the day2.stack copy
    public static void updateCash(int cash) {
        cash = cash + 500;
        System.out.println("Inside updateCash()     -> Local copy = Rs." + cash);
    }

    // 2. Reference Example: Mutates shared heap object vs Reassigns local pointer
    public static void manageAccount(BankAccount accountCard) {
        // Mutating the object on the heap via the copied pointer address
        accountCard.balance += 1000.0;
        System.out.println("Inside manageAccount()  -> Deposited Rs.1000 into shared account");

        // Reassigning local pointer to a brand-new heap object
        accountCard = new BankAccount("Suresh", 50000.0);
        System.out.println("Inside manageAccount()  -> Reassigned local pointer to " + accountCard.accountHolder);
    }

    public static void main(String[] args) {
        // --- 1. PRIMITIVE TEST ---
        System.out.println("=== 1. PRIMITIVE PASS-BY-VALUE (Cash in Pocket) ===");
        int myCash = 200;
        System.out.println("Before method call      : myCash = Rs." + myCash);
        updateCash(myCash);
        System.out.println("After method call       : myCash = Rs." + myCash + " (Unchanged)\n");

        // --- 2. REFERENCE TEST ---
        System.out.println("=== 2. REFERENCE PASS-BY-VALUE (Bank Account Card) ===");
        BankAccount myAccount = new BankAccount("Ramesh", 5000.0);
        System.out.println("Before method call      : " + myAccount.accountHolder + "'s Balance = Rs." + myAccount.balance);

        manageAccount(myAccount);

        System.out.println("After method call       : " + myAccount.accountHolder + "'s Balance = Rs." + myAccount.balance);
        System.out.println("-> Balance increased to Rs.6000 because both pointers referenced the same heap object.");
        System.out.println("-> Account still belongs to Ramesh because 'accountCard = new...' only redirected the local pointer.");
    }
}

