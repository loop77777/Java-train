package day10.ConstructorsAndInstantiation;//Constructors and Instantiation
//A constructor is a specialized block of code invoked when an object is instantiated. It shares the exact name of its enclosing class and has no return type (not even void). Its primary role is to initialize the state of an object so it starts its lifecycle in a valid configuration.
//
//Instantiation is the physical creation of an instance of a class in memory. This is performed using the new keyword in languages like Java:
//
//Memory Allocation: Space is allocated on the Heap for the object's instance variables.
//
//Zero-Initialization: Fields are populated with default type values (0, false, null).
//
//Constructor Execution: The constructor is called, binding parameters to fields and establishing the initial state.
//
//Reference Assignment: The memory address on the Heap is returned and stored in a reference variable located on the Stack.

// Class relying solely on the compiler-generated default constructor
class ImplicitDefaultExample {
    int id; // Initialized to default value: 0
    String name; // Initialized to default value: null
}

// Class with an explicit no-argument constructor
class ExplicitNoArgExample {
    int id;
    String name;

    // Explicitly written parameterless constructor
    public ExplicitNoArgExample() {
        this.id = 100;
        this.name = "Default Guest";
    }
}

public class ConstructorDemo1 {
    public static void main(String[] args) {
        // Invoking implicit default constructor
        ImplicitDefaultExample obj1 = new ImplicitDefaultExample();
        System.out.println("Implicit Default: ID=" + obj1.id + ", Name=" + obj1.name);

        // Invoking explicit no-arg constructor
        ExplicitNoArgExample obj2 = new ExplicitNoArgExample();
        System.out.println("Explicit No-Arg: ID=" + obj2.id + ", Name=" + obj2.name);
    }
}

//STACK                               HEAP
//+-------------------+             +-----------------------+
//|  Reference Var    | ----------> |      Object Data      |
//|  (e.g., account)  |  (Address)  |  - id: 101            |
//+-------------------+             |  - balance: 500.0     |
//                                  +-----------------------+


//Default vs. No-Argument Constructor
//A key distinction exists between an auto-generated default constructor and an explicit no-argument constructor:
//
//Default Constructor: Provided automatically by the compiler only if no constructors are declared anywhere in the class. It takes zero arguments and invokes the superclass constructor (super()).
//
//Explicit No-Arg Constructor: A constructor written manually that accepts zero arguments.
//
//Compiler Rule: The moment you define any constructor (such as a parameterized one), the compiler ceases generating the default zero-argument constructor. If you still require a parameterless constructor, you must declare it explicitly.




class HotelRoom {
    private final int roomNumber;
    private final String roomType;
    private final double basePrice;

    // 1. Primary Constructor (Takes all arguments)
    public HotelRoom(int roomNumber, String roomType, double basePrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    // 2. Overloaded Constructor (Default price to $100.0)
    public HotelRoom(int roomNumber, String roomType) {
        this(roomNumber, roomType, 100.0); // Chains to constructor #1
    }

    // 3. Overloaded Constructor (Default to "Standard" type and $100.0)
    public HotelRoom(int roomNumber) {
        this(roomNumber, "Standard"); // Chains to constructor #2
    }

    public void printRoomInfo() {
        System.out.println("Room #" + roomNumber + " [" + roomType + "] - Rate: $" + basePrice);
    }
}

