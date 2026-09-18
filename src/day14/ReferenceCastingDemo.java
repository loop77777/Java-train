package day14;//7. Reference Variable Casting (Upcasting vs. Downcasting)
//
//Reference casting shifts an object reference up or down along its inheritance hierarchy:
//•	Upcasting: Assigns a child object instance to a parent reference variable (Parent p = new Child();). This operation is implicitly safe and handled automatically by Java because a child object inherently contains all properties of its parent.
//•	Downcasting: Casts a parent reference back to a target child subclass type. This requires an explicit manual cast notation because it is inherently risky. If the underlying object instance in memory is not actually an instance of that specific child subclass, the JVM will throw a ClassCastException at runtime. To prevent system crashes, always check object compatibility using the instanceof keyword before performing a downcast.

// Base Superclass
class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public void printRole() {
        System.out.println("Role: General Employee | Name: " + name);
    }
}

// Subclass 1
class SoftwareEngineer extends Employee {
    private String programmingLanguage;

    public SoftwareEngineer(String name, double baseSalary, String programmingLanguage) {
        super(name, baseSalary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void printRole() {
        System.out.println("Role: Software Engineer | Name: " + name);
    }

    // Subclass-specific method
    public void writeCode() {
        System.out.println(name + " is writing production code in " + programmingLanguage + ".");
    }
}

// Subclass 2
class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public void printRole() {
        System.out.println("Role: Engineering Manager | Name: " + name);
    }

    // Subclass-specific method
    public void conductMeeting() {
        System.out.println(name + " is conducting a sprint planning meeting with " + teamSize + " engineers.");
    }
}

public class ReferenceCastingDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. UPCASTING DEMO (Implicit & Safe) ===");
        // Child instance assigned to a Parent reference
        Employee empRef1 = new SoftwareEngineer("Alex", 95000.0, "Java");
        Employee empRef2 = new Manager("Sarah", 120000.0, 8);

        // Through parent references, dynamic method dispatch calls overridden versions:
        empRef1.printRole();
        empRef2.printRole();

        // empRef1.writeCode(); // COMPILATION ERROR: Parent reference cannot see subclass-only methods


        System.out.println("\n=== 2. DOWNCASTING DEMO (Explicit & Safe using instanceof) ===");
        // Safe Downcasting via traditional instanceof check
        if (empRef1 instanceof SoftwareEngineer) {
            SoftwareEngineer dev = (SoftwareEngineer) empRef1; // Explicit cast
            dev.writeCode(); // Now accessible
        }

        // Modern Java (16+) Pattern Matching for instanceof (eliminates manual cast line)
        if (empRef2 instanceof Manager mgr) {
            mgr.conductMeeting();
        }


        System.out.println("\n=== 3. DOWNCASTING FAILURE (ClassCastException Handling) ===");
        // empRef1 actually points to a SoftwareEngineer on the Heap.
        // Attempting to forcefully cast it to a Manager will fail at runtime.
        try {
            System.out.println("Attempting illegal cast: SoftwareEngineer -> Manager...");
            Manager badCast = (Manager) empRef1;
            badCast.conductMeeting(); // Will never be reached
        } catch (ClassCastException ex) {
            System.err.println("CAUGHT RUNTIME EXCEPTION: " + ex.getMessage());
            System.out.println("Explanation: An object of type SoftwareEngineer cannot be cast to Manager.");
        }
    }
}