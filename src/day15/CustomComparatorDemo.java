package day15;

//12. Sorting Objects via Custom Comparator
//When sorting custom user-defined objects, your class can define a natural sorting pattern using the Comparable interface. However, if you need alternative sorting strategies (e.g., sorting employees by salary instead of ID), you pass a custom Comparator implementation directly to Collections.sort().

// Understanding Comparable vs. Comparator
//Comparable (Natural Sorting): Implemented directly inside the target class (implements Comparable<T>). It defines a single, default sorting sequence via the compareTo() method (e.g., sorting users strictly by their primary ID).
//
//Comparator (External Sorting Strategies): Created as a separate logic block (implements Comparator<T> or via modern lambda expressions). It lets you define unlimited alternative sorting strategies—such as sorting employees by salary, name, or department seniority dynamically.

//Understanding Comparable vs. Comparator
//Comparable (Natural Sorting): Implemented directly inside the target class (implements Comparable<T>). It defines a single, default sorting sequence via the compareTo() method (e.g., sorting users strictly by their primary ID).
//
//Comparator (External Sorting Strategies): Created as a separate logic block (implements Comparator<T> or via modern lambda expressions). It lets you define unlimited alternative sorting strategies—such as sorting employees by salary, name, or department seniority dynamically.


import java.util.*;

class Worker {
    String name;
    double salary;
    int id;

    public Worker(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Worker{name='%s', salary=%.2f, id=%d}", name, salary, id);
    }
}




public class CustomComparatorDemo {
    public static void main(String[] args) {

        List<Worker> employe = new ArrayList<>();
        employe.add(new Worker("Alice", 75000, 103));
        employe.add(new Worker("Bob", 95000, 101));
        employe.add(new Worker("Charlie", 60000, 102));

        System.out.println("--- Original Unsorted List ---");
        employe.forEach(System.out::println);

        // ==========================================
        // 1. Sorting by Salary (Ascending) using Lambdas
        // ==========================================
        employe.sort(Comparator.comparingDouble(e -> e.salary));

        System.out.println("\n--- Sorted by Salary (Ascending) ---");
        employe.forEach(System.out::println);

        // ==========================================
        // 2. Sorting by Salary (Descending) using reversed()
        // ==========================================
        employe.sort(Comparator.comparingDouble((Worker worker) -> worker.salary).reversed());

        System.out.println("\n--- Sorted by Salary (Descending) ---");
        employe.forEach(System.out::println);

        // ==========================================
        // 3. Multi-Level Sorting (Chained Comparators)
        // ==========================================
        // Sort primarily by Name alphabetically, and if names match, sort by ID.
        employe.sort(Comparator.comparing((Worker worker) -> worker.name)
                .thenComparingInt(worker -> worker.id));

        System.out.println("\n--- Multi-Level Sort (By Name, then ID) ---");
        employe.forEach(System.out::println);
    }
}





