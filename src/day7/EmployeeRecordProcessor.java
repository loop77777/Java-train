package day7;//3. The String Class
//A String object represents an unchangeable sequence of characters. It is one of the most optimized objects in the Java language.
//
//Core Concepts
//1. Immutability
//Once a String object is created on the heap, its character sequence cannot be modified. Any operations that appear to change the text actually create a brand-new String object in memory.
//
//2. The String Constant Pool (SCP)
//To save memory, the JVM maintains a special area within the heap called the String Constant Pool. When you initialize a string using a literal, the JVM checks the pool first. If that string value already exists, the JVM reuses the existing reference instead of allocating new space.

//STACK MEMORY                             HEAP MEMORY (String Constant Pool)
//┌──────────────────────────┐             ┌───────────────────────────────────────────┐
//│ s1 Reference             ├────────────►│ "Alpha" (Pool Location ID #01)            │
//│ s2 Reference             ├────────────┤                                           │
//└──────────────────────────┘             └───────────────────────────────────────────┘
//                                         ┌───────────────────────────────────────────┐
//┌──────────────────────────┐             │ Standard Heap Allocations                 │
//│ s3 Reference             ├────────────►│ "Alpha" (New Object Instance ID #02)       │
//└──────────────────────────┘             └───────────────────────────────────────────┘

//String s1 = "Alpha"; // Allocated directly into the String Constant Pool
//String s2 = "Alpha"; // Reuses the reference from the pool; no new object is created
//String s3 = new String("Alpha"); // Forces creation of a new object on the standard heap
//
//System.out.println(s1 == s2); // true (Points to the exact same memory address)
//System.out.println(s1 == s3); // false (Points to different memory locations)
//System.out.println(s1.equals(s3)); // true (Compares the actual character contents)

public class EmployeeRecordProcessor {

    public static void main(String[] args) {

        String employeeRecord =
                "EMP101|Jyothi Prasad|Software Engineer|85000|Bangalore";

        System.out.println("Original Record:");
        System.out.println(employeeRecord);

        // Split record
        String[] data = employeeRecord.split("\\|");

        String employeeId = data[0];
        String employeeName = data[1];
        String designation = data[2];
        String salary = data[3];
        String city = data[4];

        System.out.println("\n===== EMPLOYEE DETAILS =====");

        System.out.println("Employee ID   : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Designation   : " + designation);
        System.out.println("Salary        : " + salary);
        System.out.println("City          : " + city);

        // startsWith()
        System.out.println("\nID Validation:");
        if(employeeId.startsWith("EMP"))
        {
            System.out.println("Valid Employee ID");
        }

        // toUpperCase()
        System.out.println("\nDesignation In Uppercase:");
        System.out.println(designation.toUpperCase());

        // contains()
        System.out.println("\nChecking Designation:");

        if(designation.contains("Engineer"))
        {
            System.out.println("Technical Employee");
        }

        // replace()
        System.out.println("\nMasked Salary:");

        String maskedSalary =
                salary.replace(salary.substring(1, 4), "***");

        System.out.println(maskedSalary);

        // substring()
        System.out.println("\nEmployee Code:");
        System.out.println(employeeId.substring(3));

        // trim()
        String nameWithSpaces =
                "   Jyothi Prasad   ";

        System.out.println("\nTrimmed Name:");
        System.out.println(nameWithSpaces.trim());

        // Generate Email
        String email =
                employeeName.toLowerCase()
                        .replace(" ", ".")
                        .concat("@company.com");

        System.out.println("\nGenerated Email:");
        System.out.println(email);

        // length()
        System.out.println("\nEmployee Name Length:");
        System.out.println(employeeName.length());

        // charAt()
        System.out.println("\nFirst Character Of Name:");
        System.out.println(employeeName.charAt(0));
    }
}



