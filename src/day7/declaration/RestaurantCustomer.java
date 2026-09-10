//4. Declare Classes
//Declaring a class means creating a brand-new user-defined blueprint. When you write class Customer, you are instructing the compiler on the exact structure, state variables, and execution methods that instances of this type will contain. At compile time, this statement parses into a .class file byte structure. Declaring a class allocates zero runtime heap memory until the new keyword instantiates it.


package day7.declaration;

/**
 * 1. CLASS DECLARATION (The Blueprint)
 *
 * When javac compiles this source file:
 * - It creates 'RestaurantCustomer.class' containing bytecode metadata.
 * - ZERO runtime heap memory is allocated for objects at this stage.
 * - It defines the structural template: fields, constructors, and behaviors.
 */
public class RestaurantCustomer {

    // Blueprint State Attributes (Instance Variables)
    private final int customerId;
    private final String fullName;
    private double loyaltyPoints;

    // Blueprint Initialization Contract (Constructor)
    public RestaurantCustomer(int customerId, String fullName, double initialPoints) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.loyaltyPoints = initialPoints;
    }

    // Blueprint Behaviors (Instance Methods)
    public void addRewardPoints(double orderValue) {
        double earnedPoints = orderValue * 0.10; // 10% cash back in points
        this.loyaltyPoints += earnedPoints;
        System.out.println(fullName + " earned " + earnedPoints + " pts. Total: " + this.loyaltyPoints);
    }

    public void displayCustomerProfile() {
        System.out.println("ID: " + customerId + " | Name: " + fullName + " | Points: " + loyaltyPoints);
    }
}



