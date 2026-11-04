package day18;

//Exception Hierarchy & Handling Rules
//Java exceptions trace up to java.lang.Throwable.

//java.lang.Object
//                                     │
//                                     ▼
//                            java.lang.Throwable
//                                     │
//                     ┌───────────────┴───────────────┐
//                     ▼                               ▼
//             java.lang.Error                java.lang.Exception
//          (Unrecoverable JVM Failures)     (Application-Level Issues)
//          - OutOfMemoryError                         │
//          - StackOverflowError           ┌───────────┴───────────┐
//                                         ▼                       ▼
//                              Checked Exceptions       java.lang.RuntimeException
//                              (Compiler Enforced)     (Unchecked Logic Errors)
//                              - IOException           - NullPointerException
//                              - SQLException          - ArithmeticException
//                              - ClassNotFoundException- ArrayIndexOutOfBoundsException

// Key Rules:
//Multi-Catch Ordering: When catching polymorphic exceptions, catch subclasses (specific exceptions like FileNotFoundException) before superclasses (general exceptions like IOException or Exception). Reversing the order produces an unreachable code compilation error.
//
//finally Execution Guarantee: Code inside a finally block executes regardless of whether an exception was thrown, caught, or left unhandled.
//
//Try-With-Resources (AutoCloseable): Eliminates explicit finally cleanup blocks by auto-closing stream handles upon scope exit.

public class Demo2 {

    public static void main(String[] args) {

        try {

            int a = 10;
            int b = 0;

            System.out.println(a / b);

        } catch (ArithmeticException e) {

            System.out.println("Cannot divide by zero");
        }

        System.out.println("Program End");
    }
}

