package day10.PassingAndReturning;//Passing and Returning Objects to/from Methods
//In Java, everything is passed by value. However, when an object is passed as an argument, the value being passed is the copy of the reference (memory address) pointing to that object.
//
//Passing Objects as Arguments
//The method receives a separate copy of the memory pointer.
//
//Mutating State: Modifying internal fields of the object inside the method affects the original object on the Heap because both references point to the same memory address.
//
//Reassigning the Reference: Reassigning the parameter variable inside the method (obj = new MyObject()) only changes the local copy of the pointer; it does not change the caller's reference.
//
//Returning Objects from Methods
//Methods can return references to existing objects, newly constructed objects (factory methods), or defensive copies.
//
//Returning references to mutable internal state can inadvertently expose internal state to outside mutation, compromising encapsulation.

class CustomerBalance {
    private double balance;

    public CustomerBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void deduct(double amount) { this.balance -= amount; }
}

class Voucher {
    private final String voucherCode;
    private final double discountValue;

    public Voucher(String voucherCode, double discountValue) {
        this.voucherCode = voucherCode;
        this.discountValue = discountValue;
    }

    public String getVoucherCode() { return voucherCode; }
    public double getDiscountValue() { return discountValue; }
}

class LoyaltyService {

    // 1. PASSING AN OBJECT: Modifies the original object on the Heap
    public void applyPenaltyFee(CustomerBalance customer, double fee) {
        // Because the memory reference is passed by value, this mutates the caller's instance
        customer.deduct(fee);
    }

    // 2. RETURNING AN OBJECT: Factory pattern creating and returning a new object
    public Voucher generateVoucher(String code, double value) {
        return new Voucher(code, value);
    }
}

public class PassAndReturnDemo {
    public static void main(String[] args) {
        LoyaltyService service = new LoyaltyService();

        // --- Demo 1: Passing Object ---
        CustomerBalance client = new CustomerBalance(250.0);
        System.out.println("Initial Balance: $" + client.getBalance());

        // Pass 'client' object reference into method
        service.applyPenaltyFee(client, 50.0);
        System.out.println("Balance after fee: $" + client.getBalance());

        // --- Demo 2: Returning Object ---
        // Method builds and returns a new day10.PassingAndReturning.Voucher instance
        Voucher bonus = service.generateVoucher("SUMMER2026", 25.0);
        System.out.println("Returned day10.PassingAndReturning.Voucher -> Code: " + bonus.getVoucherCode() +
                ", Value: $" + bonus.getDiscountValue());
    }
}

