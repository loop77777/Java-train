package day15;

// 11. Performance Profiles: ArrayList vs. LinkedList
//Choosing the right collection layout has significant impacts on CPU and memory performance.
//	ArrayList uses a contiguous array backing structure. Reading data at an index is incredibly fast (O(1)), but inserting or removing items in the middle requires shifting elements in memory (O(n)).
//	LinkedList uses nodes linked to their neighbors. Reading requires traversing from node to node (O(n)), but adding or removing elements once positioned is nearly instant (O(1)).

//Understanding the Architecture Trade-Off
//ArrayList (Contiguous Memory): Elements are stored sequentially in a single block of memory. This provides incredible CPU cache locality, making random lookups (get(index)) lightning-fast at O(1). However, inserting or deleting items in the middle forces Java to shift all subsequent elements in memory, costing O(n) time.
//
//LinkedList (Pointer Chains): Elements are wrapped in discrete Node objects scattered across the heap, each holding pointers to the next and previous neighbors. Traversing to a specific index requires walking the chain one node at a time (O(n)). However, once you are at a specific position, inserting or removing elements requires only rewiring a couple of pointers (O(1)).



import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListDemo {
    public static void main(String[] args) {

        int size = 100_000;

        // ==========================================
        // 1. Random Access Performance Profile (O(1) vs O(n))
        // ==========================================
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // Populate both collections
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // Benchmark ArrayList random access
        long startTime = System.nanoTime();
        int item1 = arrayList.get(size / 2);
        long arrayListDuration = System.nanoTime() - startTime;

        // Benchmark LinkedList random access
        startTime = System.nanoTime();
        int item2 = linkedList.get(size / 2);
        long linkedListDuration = System.nanoTime() - startTime;

        System.out.println("--- Random Access Lookup (Index " + (size/2) + ") ---");
        System.out.println("ArrayList time:  " + arrayListDuration + " ns (Direct memory offset)");
        System.out.println("LinkedList time: " + linkedListDuration + " ns (Requires traversal step-by-step)");


        // ==========================================
        // 2. Middle Insertion Performance Profile
        // ==========================================
        // Inserting at index 0 requires shifting 100,000 elements in ArrayList, 
        // but only pointer rewiring for LinkedList.

        startTime = System.nanoTime();
        arrayList.add(0, 999); // Shifts all elements right
        long arrayListInsert = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        linkedList.add(0, 999); // Rewires head pointer
        long linkedListInsert = System.nanoTime() - startTime;

        System.out.println("\n--- Head Insertion (Index 0) ---");
        System.out.println("ArrayList insert time:  " + arrayListInsert + " ns");
        System.out.println("LinkedList insert time: " + linkedListInsert + " ns");
    }
}


