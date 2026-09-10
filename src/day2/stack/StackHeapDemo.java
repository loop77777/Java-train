package day2.stack;//2. Local (Stack, Automatic) Primitives and Objects
//
//Every time a thread invokes a method, the JVM allocates a private block of memory called a Stack Frame and pushes it onto that thread's execution day2.stack. This space handles "automatic" variables because it is managed entirely by the lifecycle of the method call.
//
//Primitives on the Stack: If a local variable is declared as a primitive data type (e.g., int x = 5;), the actual value bits representing that number are contained entirely within a slot inside that physical day2.stack frame.
//
//Objects on the Stack: If a local variable is an object or an day2.array (e.g., Car myCar = new Car();), the variable inside the day2.stack frame does not contain the object's data. Instead, it holds a reference value—a 32-bit or 64-bit memory address that points directly to an allocated block out on the Heap.
//
//Method Exit: The moment the method hits a return statement or finishes executing, its entire Stack Frame is popped off the day2.stack and destroyed. Primitives vanish instantly. The local reference variables also vanish, leaving the actual object sitting alone on the Heap. If no other active reference pointers on the day2.stack point to that heap object, it becomes orphan data and is automatically cleaned up by the Garbage Collector (GC).

public class StackHeapDemo {

    public static void main(String[] args) {

        System.out.println("===== MAIN METHOD STARTED =====");

        bookRide();

        System.out.println("===== MAIN METHOD ENDED =====");
    }

    public static void bookRide() {

        System.out.println("\n===== bookRide() STARTED =====");

        // Primitive Variable
        int fare = 500;

        // Object Variable
        Driver driver = new Driver("Kishore");

        System.out.println("Fare = " + fare);
        System.out.println("day2.stack.Driver = " + driver.getName());

        System.out.println("\nCalling updateFare()...");
        updateFare(fare);

        System.out.println("After updateFare(), fare = " + fare);

        System.out.println("\nCalling updateDriver()...");
        updateDriver(driver);

        System.out.println("After updateDriver(), driver = "
                + driver.getName());

        System.out.println("\n===== bookRide() ENDED =====");
    }

    public static void updateFare(int fare) {

        System.out.println("\nInside updateFare()");

        fare = fare + 100;

        System.out.println("Modified Fare = " + fare);
    }

    public static void updateDriver(Driver driver) {

        System.out.println("\nInside updateDriver()");

        driver.setName("Sunil");

        System.out.println("Modified day2.stack.Driver = "
                + driver.getName());
    }
}

class Driver {

    private String name;

    public Driver(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}

//int[] ages;   // Preferred idiomatic Java syntax
//int ages[];   // Legal, legacy C/C++ style syntax
//
// COMPILATION ERROR:
// / int[4] badArray;
// / Why? Size is a property of the heap instance, not the type signature!