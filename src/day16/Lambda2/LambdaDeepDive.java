package day16.Lambda2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (₹" + (long) salary + ")";
    }
}

public class LambdaDeepDive {
    public static void main(String[] args) {

        System.out.println("=== 1. RUNNABLE COMPARISON (Thread Execution) ===");

        // --- Java 7 Style (Anonymous Inner Class) ---
        // Mechanics: Creates an extra 'LambdaDeepDive$1.class' on disk + explicit heap allocation.
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("[Java 7] Thread running payroll for Karthik");
            }
        };

        // --- Java 8 Style (Lambda Expression) ---
        // Mechanics: Uses 'invokedynamic' instruction -> LambdaMetafactory -> Direct MethodHandle call.
        Runnable r2 = () -> System.out.println("[Java 8] Thread running payroll for Ananya");

        new Thread(r1).start();
        new Thread(r2).start();

        // Pause briefly to let threads finish printing
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}


        System.out.println("\n=== 2. COMPARATOR COMPARISON (Collection Sorting) ===");

        List<Employee> team = new ArrayList<>(List.of(
                new Employee("Suresh", 75000),
                new Employee("Meenakshi", 52000),
                new Employee("Lakshmi", 68000),
                new Employee("Venkat", 45000)
        ));

        System.out.println("Original Roster: " + team);

        // --- Java 7 Style (Anonymous Inner Class Comparator) ---
        Collections.sort(team, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        });
        System.out.println("\n[Java 7] Sorted Ascending by Salary:  " + team);

        // --- Java 8 Style (Lambda Comparator) ---
        // Concise syntax with type inference; no anonymous class generated.
        team.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        System.out.println("[Java 8] Sorted Descending by Salary: " + team);
    }
}
