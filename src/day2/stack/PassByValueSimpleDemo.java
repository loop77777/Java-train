package day2.stack;//3. Passing Variables into Methods & Pass-by-Value Semantics
//
//Java is strictly 100% Pass-by-Value. There is no such thing as pass-by-reference in the Java language.
//
//When you pass an argument to a method, the JVM takes the bit-pattern stored inside the caller's variable, makes an exact duplicate copy of those bits, and places that copy into a brand-new variable slot inside the called method's day2.stack frame. The method never receives access to the caller's original variable slot.
//
//Passing Primitive Variables
//Because a primitive variable holds raw values (like the number 50), passing it means copying the number 50. The receiving method manipulates its own local day2.stack copy. The caller's original variable remains untouched.
//
//Passing Reference Variables
//When you pass an object or an day2.array, the bit-pattern inside the variable is the heap memory address (the pointer token). Java photocopies that address token.
//
//Now, both the caller's variable and the method's parameter hold distinct copies of the exact same address.
//
//Because both point to the same object on the shared heap, changes made to the internal data of the object inside the method will alter the original object.
//
//However, if you reassign the parameter inside the method to a new object (param = new Object()), you are simply replacing the copied address token inside that method's temporary day2.stack slot. The caller's original address token remains fixed to the original object.

// Top-level Standalone Class 1: The Object residing in Heap Memory
class BankAccount {
    String accountHolder;
    double balance;

    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
}

// Top-level Standalone Class 2: The day2.stack.Driver / Execution Engine
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