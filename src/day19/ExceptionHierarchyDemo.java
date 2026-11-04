package day19;

//13–14. Catch Block Hierarchies and Matching
//
//When specifying multiple catch blocks, Java tests exceptions from top to bottom.
//
//Rule: Subclass (specific) exceptions must be listed before Superclass (generic) exceptions.
//
//Unreachable Code Error: Listing a generic exception like IOException before FileNotFoundException causes a compiler error because the subclass block can never be reached.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionHierarchyDemo {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("missing.txt");
            fr.read();
        }
        // 1. SPECIFIC SUBCLASS FIRST
        catch (FileNotFoundException e) {
            System.err.println("Handled Specific Error: File not found on disk.");
        }
        // 2. GENERIC PARENT CLASS SECOND
        catch (IOException e) {
            System.err.println("Handled Generic Error: General I/O issue.");
        }
        // 3. TOP-LEVEL CATCH-ALL LAST
        catch (Exception e) {
            System.err.println("Handled Catch-All: " + e.getMessage());
        }
    }
}
