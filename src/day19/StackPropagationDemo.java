package day19;

// Call Stack Propagation
//
//If a method encounters an uncaught exception, execution halts immediately and the exception travels up the call stack searching for an enclosing try-catch block. If it reaches main() without being caught, the thread terminates with a stack trace output.

public class StackPropagationDemo {

    void level3() {
        int fault = 10 / 0; // ArithmeticException thrown here
    }

    void level2() {
        level3(); // Delegates call
    }

    void level1() {
        try {
            level2(); // Exception propagates up through level2 into level1
        } catch (ArithmeticException e) {
            System.err.println("Caught propagated exception inside level1(): " + e.toString());
        }
    }

    public static void main(String[] args) {
        new StackPropagationDemo().level1();
        System.out.println("Program resumed execution safely.");
    }
}