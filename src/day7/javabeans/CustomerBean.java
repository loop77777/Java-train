//3. JavaBeans Standards
//A JavaBean is a specific structural design pattern for creating reusable, predictable domain objects. To comply with the enterprise JavaBean standard, a class must rigidly satisfy three conditions:
//
//Public No-Argument Constructor: Must explicitly provide or naturally inherit a public constructor with zero parameters to allow frameworks (like Spring or Hibernate) to instantiate it dynamically via reflection.
//
//Encapsulation: All instance variables (properties) must be declared private.
//
//Accessor and Mutator Methods: Public getX() / setX() methods (or isX() for booleans) must expose the private state to the outside world.

package day7.javabeans;

import java.io.Serializable;

/**
 * Enterprise JavaBean Standard Implementation
 *
 * Complies with:
 * 1. Implements java.io.Serializable (for network transfer / persistence)
 * 2. Public no-argument constructor (for reflection instantiation)
 * 3. Complete encapsulation (private properties)
 * 4. Standard public getters/setters (or isX() for boolean flags)
 */
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // 1. ENCAPSULATION: All instance properties declared strictly PRIVATE
    // =========================================================================
    private int customerId;
    private String customerName;
    private double walletBalance;
    private boolean activeStatus;

    // =========================================================================
    // 2. PUBLIC NO-ARGUMENT CONSTRUCTOR (Zero parameters for framework reflection)
    // =========================================================================
    public CustomerBean() {
        // Default constructor required by Spring, Hibernate, Jackson, etc.
    }

    // Optional parameterized constructor for manual convenience
    public CustomerBean(int customerId, String customerName, double walletBalance, boolean activeStatus) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.walletBalance = walletBalance;
        this.activeStatus = activeStatus;
    }

    // =========================================================================
    // 3. ACCESSORS (Getters) & MUTATORS (Setters)
    // =========================================================================

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    // Special Boolean Naming Standard: uses 'is' instead of 'get'
    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "CustomerBean [id=" + customerId + ", name=" + customerName
                + ", balance=" + walletBalance + ", active=" + activeStatus + "]";
    }
}