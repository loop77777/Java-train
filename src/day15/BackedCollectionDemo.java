package day15;

//8. Backed Collections
//A Backed Collection acts as a live visual window over a segment of a primary underlying collection. It is completely synchronized—any updates made inside the window instantly modify the parent data source, and vice versa.

//Understanding Backed Collections
//Methods like subMap(), headMap(), tailMap() (on SortedMap), or subList() (on List) do not create independent copies of the data. Instead, they return a live proxy view.
//
//Synchronized Changes: Any insertion, deletion, or modification inside the backed view instantly updates the parent collection, and vice versa.
//
//Range Restrictions: If you attempt to add an element to a backed view that falls outside its defined threshold range, the operation will throw an IllegalArgumentException.

//8. Backed Collections
//A Backed Collection acts as a live visual window over a segment of a primary underlying collection. It is completely synchronized—any updates made inside the window instantly modify the parent data source, and vice versa.

import java.util.SortedMap;
import java.util.TreeMap;



public class BackedCollectionDemo {
    public static void main(String[] args) {

        // 1. Initialize the primary parent collection
        TreeMap<Integer, String> parentMap = new TreeMap<>();
        parentMap.put(10, "Ten");
        parentMap.put(20, "Twenty");
        parentMap.put(30, "Thirty");
        parentMap.put(40, "Forty");

        System.out.println("Original Parent Map: " + parentMap);

        // 2. Create a backed view using subMap (inclusive 20 up to exclusive 40)
        SortedMap<Integer, String> backedView = parentMap.subMap(20, 40);
        System.out.println("Backed View [20, 40): " + backedView);

        // ==========================================
        // 3. Modifying the Backed View affects the Parent
        // ==========================================
        backedView.put(25, "Twenty-Five");

        System.out.println("\n--- After adding key 25 via Backed View ---");
        System.out.println("Backed View: " + backedView);
        System.out.println("Parent Map: " + parentMap);
        // Notice key 25 automatically appears in the parent map!

        // ==========================================
        // 4. Modifying the Parent affects the Backed View
        // ==========================================
        parentMap.put(35, "Thirty-Five");

        System.out.println("\n--- After adding key 35 directly to Parent ---");
        System.out.println("Backed View: " + backedView);
        // Notice key 35 instantly reflects inside the backed view window!
    }
}

