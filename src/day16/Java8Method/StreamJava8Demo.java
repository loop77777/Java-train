package day16.Java8Method;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Dev {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Dev(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + department + ", ₹" + (long)salary + ")";
    }
}

public class StreamJava8Demo {
    public static void main(String[] args) {
        List<Dev> devs = Arrays.asList(
                new Dev("Karthik", "IT", 85000, 30),
                new Dev("Ananya", "HR", 55000, 45),
                new Dev("Suresh", "IT", 95000, 28),
                new Dev("Meenakshi", "HR", 60000, 35),
                new Dev("Lakshmi", "IT", 72000, 26)
        );

        System.out.println("=== 1. PIPELINE EXECUTION & LAZY EVALUATION (Java 8) ===");
        List<String> highEarnerNames = devs.stream()
                .filter(d -> {
                    System.out.println("Filter evaluating: " + d.getName());
                    return d.getSalary() >= 60000;
                })
                .map(d -> {
                    System.out.println("Map converting: " + d.getName());
                    return d.getName().toUpperCase();
                })
                .limit(2) // Short-circuits loop when 2 matches are collected!
                .collect(Collectors.toList());

        System.out.println("Filtered Names (Max 2): " + highEarnerNames);


        System.out.println("\n=== 2. GROUP BY DEPARTMENT (Java 8) ===");
        Map<String, List<Dev>> byDept = devs.stream()
                .collect(Collectors.groupingBy(Dev::getDepartment));

        System.out.println("IT Department Count: " + byDept.get("IT").size());


        System.out.println("\n=== 3. PARTITION BY SALARY THRESHOLD (>60000) (Java 8) ===");
        Map<Boolean, List<Dev>> partitioned = devs.stream()
                .collect(Collectors.partitioningBy(d -> d.getSalary() > 60000));

        System.out.println("High Earners Count (>60k): " + partitioned.get(true).size());
        System.out.println("Others Count (<=60k):     " + partitioned.get(false).size());


        System.out.println("\n=== 4. COLLECTORS.SUMMARIZINGINT (Java 8) ===");
        IntSummaryStatistics ageStats = devs.stream()
                .collect(Collectors.summarizingInt(Dev::getAge));

        System.out.println("Average Age : " + ageStats.getAverage());
        System.out.println("Max Age     : " + ageStats.getMax());
        System.out.println("Min Age     : " + ageStats.getMin());
        System.out.println("Total Count : " + ageStats.getCount());
        System.out.println("Sum of Ages : " + ageStats.getSum());
    }
}