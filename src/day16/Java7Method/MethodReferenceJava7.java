package day16.Java7Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Customer {
    private String name;

    public Customer() {
        this.name = "Guest User";
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static double calculateTax(double salary) {
        return salary * 0.10; // 10% TDS Tax
    }
}

class ConsolePrinter {
    public void printInfo(String message) {
        System.out.println("[CONSOLE LOG] " + message);
    }
}

// Custom Interface Factory for Java 7
interface CustomerFactory {
    Customer create(String name);
}

public class MethodReferenceJava7 {
    public static void main(String[] args) {
        // 1. Static Method Wrapper (Anonymous Class)
        double tax = Customer.calculateTax(60000.0);
        System.out.println("Java 7 Static Call Tax for Karthik: ₹" + tax);

        // 2. Instance Method Wrapper
        final ConsolePrinter printer = new ConsolePrinter();
        printer.printInfo("Processing record for Ananya");

        // 3. Arbitrary Instance Method (Sorting Names)
        List<String> customerNames = new ArrayList<String>();
        customerNames.add("Suresh");
        customerNames.add("Ananya");
        customerNames.add("Karthik");

        Collections.sort(customerNames, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        System.out.print("Java 7 Sorted List: ");
        for (String name : customerNames) {
            System.out.print(name + " ");
        }
        System.out.println();

        // 4. Constructor Wrapper (Factory)
        CustomerFactory factory = new CustomerFactory() {
            @Override
            public Customer create(String name) {
                return new Customer(name);
            }
        };
        Customer customer = factory.create("Meenakshi");
        System.out.println("Java 7 Factory Created: " + customer.getName());
    }
}