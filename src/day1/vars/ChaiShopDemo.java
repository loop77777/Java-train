package day1.vars;
//Variables: The Memory Container
    //A variable in Java is an abstraction over raw physical memory. When you declare a variable, the JVM's bytecode instructions (iload, istore, aload, astore) tell the execution engine how many bytes to reserve on the day2.stack frame or heap, and how to interpret those raw bits.

    //Primitive Variables: Store the raw binary value directly inside the thread day2.stack frame (e.g., int stores 32 bits, double stores 64 bits).
    //
    //Reference Variables: Store a memory address (typically a 32-bit or 64-bit pointer, depending on JVM compressed oops) that points to an object residing in the Garbage-Collected Heap.

    // Variable Container (LHS)                  Assignment Operator (=)             Literal Constant (RHS)
    //+------------------------------------+                                        +------------------------------------+
    //|            int myVar               |  <===================================  |                100                 |
    //| (Named 32-bit day2.stack memory slot)   |      Evaluates RHS and copies bits     |  (Hardcoded integer value in code) |
    //+------------------------------------+                                        +------------------------------------+

    //Labeled Jar/Cup (Variable)        Drop Inside (=)          Actual Item (Literal)
    //      +-----------------------------+                           +-----------------------+
    //      |      int chaiCups           |  <======================  |           3           | (Count of cups)
    //      +-----------------------------+                           +-----------------------+
    //      |      double pricePerCup     |  <======================  |         15.50         | (Exact rupee value)
    //      +-----------------------------+                           +-----------------------+
    //      |      char teaSize           |  <======================  |          'M'          | (Single letter for Medium)
    //      +-----------------------------+                           +-----------------------+
    //      |      boolean isGingerAdded  |  <======================  |         true          | (Yes/No answer)
    //      +-----------------------------+                           +-----------------------+
    //      |      String shopName        |  <======================  |   "Namma Chai Point"  | (Text word)
    //      +-----------------------------+                           +-----------------------+

    public class ChaiShopDemo {

        public static void main(String[] args) {
            // 1. Whole Number (int literal) -> Counting items
            int cupsOrdered = 3;

            // 2. Fractional/Decimal Number (double literal) -> Exact money
            double pricePerCup = 15.50;

            // 3. Single Character (char literal) -> Cup size (enclosed in single quotes)
            char cupSize = 'M';

            // 4. True/False Flag (boolean literal) -> Yes/No condition
            boolean wantsGinger = true;

            // 5. Text / Words (String literal) -> Names and messages (enclosed in double quotes)
            String customerName = "Suresh";

            // Calculation: (3 * 15.50)
            double totalBill = cupsOrdered * pricePerCup;

            // Printing the Bill Receipt
            System.out.println("====== NAMMA CHAI STALL RECEIPT ======");
            System.out.println("Customer Name    : " + customerName);
            System.out.println("Cup Size         : " + cupSize);
            System.out.println("Ginger Added?    : " + wantsGinger);
            System.out.println("Number of Teas   : " + cupsOrdered);
            System.out.println("Price per Tea    : Rs." + pricePerCup);
            System.out.println("--------------------------------------");
            System.out.println("Total Amount Due : Rs." + totalBill);
            System.out.println("======================================");
        }
    }
