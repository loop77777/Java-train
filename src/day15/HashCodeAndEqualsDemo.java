package day15;//5. Overriding equals() & hashCode() Correctly
//If you plan to store custom objects inside hash-based collections (HashSet, HashMap), you must override equals() and hashCode() together.
//
//The Hash Contract: If two objects evaluate as equal via equals(), they must yield the exact same integer value for hashCode(). If they don't, your object will end up in the wrong hash bucket, causing structural duplicates in your database tracking models.

import java.util.HashSet;
import java.util.Objects;

class Employee {
    String id;
    String department;

    public Employee(String id, String department) {
        this.id = id;
        this.department = department;
    }

    // 1. equals() checks structural equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(department, employee.department);
    }

    // 2. hashCode() must align with equals() to satisfy the contract
    @Override
    public int hashCode() {
        return Objects.hash(id, department);
    }

    @Override
    public String toString() {
        return "Employee{id='" + id + "', dept='" + department + "'}";
    }
}

public class HashCodeAndEqualsDemo {
    public static void main(String[] args) {

        HashSet<Employee> employees = new HashSet<>();

        Employee emp1 = new Employee("E001", "Engineering");
        // emp2 has a different memory address, but identical data state to emp1
        Employee emp2 = new Employee("E001", "Engineering");

        employees.add(emp1);

        // Attempting to add a structural duplicate
        employees.add(emp2);

        System.out.println("--- HashSet Size Check ---");
        System.out.println("Total unique employees in set: " + employees.size());
        // Output: 1 (Because both equals() and hashCode() agree they are the exact same logical entity)

        System.out.println("Contains emp2? " + employees.contains(emp2));
        // Output: true -> Fast bucket lookup succeeds!
    }
}