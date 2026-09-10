package day5;// 3. The String Class
//A String object represents an unchangeable sequence of characters. It is one of the most optimized objects in the Java language.
//
//Core Concepts
//1. Immutability
//Once a String object is created on the heap, its character sequence cannot be modified. Any operations that appear to change the text actually create a brand-new String object in memory.
//
//2. The String Constant Pool (SCP)
//To save memory, the JVM maintains a special area within the heap called the String Constant Pool. When you initialize a string using a literal, the JVM checks the pool first. If that string value already exists, the JVM reuses the existing reference instead of allocating new space.

public class NestedIfATMDemo {

    public static void main(String[] args) {

        boolean cardValid = true;
        boolean pinCorrect = true;

        if (cardValid) {

            System.out.println("Card Validation Successful");

            if (pinCorrect) {

                System.out.println("PIN Verification Successful");
                System.out.println("Cash Withdrawal Approved");

            }
        }
    }
}