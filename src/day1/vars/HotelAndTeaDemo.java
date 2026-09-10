package day1.vars;//1. Uninitialized and Unassigned Variables
//Java divides variables into two core categories that dictate how memory safety is enforced:
//Instance & Class Fields (Heap Allocated): When an object is created on the heap via new, the JVM automatically clears that entire block of memory by zeroing out the bits. This means every field is automatically given a predictable default value (0 for numbers, false for booleans, \u0000 for characters, and null for any object reference or day2.array reference).
//Local Variables (Stack Allocated): Local variables reside inside a thread's active day2.stack frame. The JVM does not perform any default zeroing of day2.stack memory for performance reasons. Reading an uninitialized local variable means you would read whatever "garbage data" was left behind by previous method executions. To prevent this security and logic risk, the Java compiler uses a mechanism called Definite Assignment analysis. If there is any code execution path where a local variable could be read before a value is explicitly written to it, the compiler treats it as a fatal error and blocks compilation.

public class HotelAndTeaDemo {

  // 1. HOTEL ROOM AMENITIES (Class Fields on Heap)
  // The JVM automatically cleans these with safe default values (0, 0.0, false, null)
  int roomNumber;          // Defaults to 0
  double roomRent;         // Defaults to 0.0
  boolean isAcTurnedOn;    // Defaults to false
  String guestName;        // Defaults to null

  public void checkHotelRoom() {
    System.out.println("=== 1. Hotel Room (Automatic Safe Defaults) ===");
    System.out.println("Room Number : " + roomNumber);    // Prints 0
    System.out.println("Room Rent   : Rs." + roomRent);   // Prints 0.0
    System.out.println("AC On?      : " + isAcTurnedOn);  // Prints false
    System.out.println("Guest Name  : " + guestName);     // Prints null
    System.out.println();
  }

  // 2. ORDERING TEA (Method Local Variable on Stack)
  // Java does NOT give default values to local variables.
  // You MUST pour tea (assign a value) before serving (printing).
  public void orderTea(boolean wantsSugar) {
    System.out.println("=== 2. Tea Stall (Must Assign Before Use) ===");

    int teaPrice; // Empty cup sitting on the counter

    // Both paths guarantee a value is poured into teaPrice
    if (wantsSugar) {
      teaPrice = 15;
    } else {
      teaPrice = 12;
    }

    // COMPILER IS HAPPY: A price was guaranteed on all paths!
    System.out.println("Tea Price: Rs." + teaPrice);
  }

  public static void main(String[] args) {
    HotelAndTeaDemo demo = new HotelAndTeaDemo();

    // 1. View default values given by JVM for free
    demo.checkHotelRoom();

    // 2. View local variable with guaranteed assignment
    demo.orderTea(true);
    demo.orderTea(false);
  }
}