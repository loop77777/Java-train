package day15;

//7. Sorting & Advanced Navigation Collections
//While standard collections provide flat indexing structures, Sorted Set and Sorted Map components (TreeSet, TreeMap) automatically maintain elements in an ascending order sorting structure using tree layouts. They expose navigation methods (higher(), floor()) to pull comparative threshold values.

//Understanding Navigation Methods
//Unlike standard collections, NavigableSet and NavigableMap implementations (TreeSet, TreeMap) allow you to search for items relative to specific thresholds rather than exact matches:
//
//floor(E e): Returns the greatest element less than or equal to e, or null if none exists.
//
//lower(E e): Returns the greatest element strictly less than e.
//
//ceiling(E e): Returns the least element greater than or equal to e.
//
//higher(E e): Returns the least element strictly greater than e.

import java.util.*;


public class SortedNavigationDemo {
    public static void main(String[] args) {

        // ==========================================
        // 1. TreeSet Navigation (Ascending Order Sorting)
        // ==========================================
        NavigableSet<Integer> scores = new TreeSet<>();
        scores.add(45);
        scores.add(72);
        scores.add(88);
        scores.add(95);

        System.out.println("--- TreeSet Sorted Scores ---");
        System.out.println("All scores: " + scores);
        // Output: [45, 72, 88, 95] (Automatically sorted)

        // Threshold Navigation Examples
        System.out.println("Floor of 80 (<= 80): " + scores.floor(80));       // Output: 72
        System.out.println("Lower of 88 (< 88): " + scores.lower(88));         // Output: 72
        System.out.println("Ceiling of 80 (>= 80): " + scores.ceiling(80));   // Output: 88
        System.out.println("Higher of 88 (> 88): " + scores.higher(88));       // Output: 95


        // ==========================================
        // 2. TreeMap Range Views & Navigation
        // ==========================================
        NavigableMap<Integer, String> rankMap = new TreeMap<>();
        rankMap.put(45, "Bronze");
        rankMap.put(72, "Silver");
        rankMap.put(88, "Gold");
        rankMap.put(95, "Platinum");

        System.out.println("\n--- TreeMap Navigation ---");
        System.out.println("Highest rank entry: " + rankMap.lastEntry());

        // Extracting a sub-map range (inclusive/exclusive bounds)
        System.out.println("Scores from 70 up to 90: " + rankMap.subMap(70, true, 90, true));
    }
}


