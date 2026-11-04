package day18;

// Object
//  |
//Throwable
//  |
//  +---- Error
//  |
//  +---- Exception
//          |
//          +---- RuntimeException

//java.lang.Object
//                                │
//                                ▼
//                       java.lang.Throwable
//                                │
//               ┌────────────────┴────────────────┐
//               ▼                                 ▼
//        java.lang.Error                 java.lang.Exception
//   (Unrecoverable JVM Failures)      (Application-Level Issues)
//     - OutOfMemoryError                          │
//     - StackOverflowError            ┌───────────┴───────────┐
//     - VirtualMachineError           ▼                       ▼
//                            Checked Exceptions    java.lang.RuntimeException
//                            (Compiler Enforced)     (Unchecked Logic Bugs)
//                            - IOException           - ArithmeticException
//                            - SQLException          - NullPointerException
//                            - ClassNotFoundException- ArrayIndexOutOfBoundsException
//                                                    - NumberFormatException


public class Demo3 {

    public static void main(String[] args) {

        try {

            String s = null;

            System.out.println(s.length());

        } catch (ArithmeticException e) {

            System.out.println("Arithmetic Error");

        } catch (NullPointerException e) {

            System.out.println("Null Error");
        }
    }
}

