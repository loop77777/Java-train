package day7.declaration;

public class ClassInstantiationDemo {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      CLASS DECLARATION VS RUNTIME INSTANCES      ");
        System.out.println("==================================================\n");

        // Reference Variables (Stack) currently unassigned or pointing to null -> 0 Heap Objects
        RestaurantCustomer firstCustomer;
        RestaurantCustomer secondCustomer;

        System.out.println("1. Class definition loaded by JVM ClassLoader.");
        System.out.println("2. Stack reference declared (no heap allocation yet).\n");

        // 3. Heap Allocation occurs ONLY when 'new' is executed
        firstCustomer = new RestaurantCustomer(101, "Jyothi Prasad", 50.0);
        secondCustomer = new RestaurantCustomer(102, "Karthik Sivakumar", 120.0);

        System.out.println("3. Objects created dynamically on the Heap memory:\n");

        // Distinct heap objects operating on the shared class blueprint
        firstCustomer.displayCustomerProfile();
        secondCustomer.displayCustomerProfile();

        System.out.println("\n--- Behavior Execution on Separate Object States ---");
        firstCustomer.addRewardPoints(1500.0);
        secondCustomer.addRewardPoints(450.0);
    }
}




