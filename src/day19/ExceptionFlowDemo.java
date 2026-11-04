package day19;

// // 7. Exception Flow and try-catch-finally
//
//Exceptions interrupt the normal execution path of a application.
//
//Checked Exceptions (IOException, SQLException): Direct subclasses of Exception (excluding RuntimeException). The Java compiler forces you to handle them explicitly (try-catch or throws).
//
//Unchecked Exceptions (ArithmeticException, NullPointerException): Subclasses of RuntimeException. They represent logic flaws or bugs that can happen anywhere.
//
//The finally Block: Guarantees execution regardless of whether an exception was thrown or caught. Useful for legacy resource cleanup.

public class ExceptionFlowDemo {

    public static void main(String[] args) {
        System.out.println("--- Starting Program Execution ---");

        int dividend = 100;
        int divisor = 0;

        try {
            System.out.println("Entering try block...");
            int result = dividend / divisor; // Triggers ArithmeticException
            System.out.println("This line will NEVER execute due to exception above: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Caught Exception: Cannot divide by zero! Message: " + e.getMessage());
        } finally {
            System.out.println("FINALLY BLOCK: Runs unconditionally (ideal for resource cleanup).");
        }

        System.out.println("--- Program continued normally past catch block ---");
    }
}

