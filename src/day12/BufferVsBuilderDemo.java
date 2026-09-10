//1. StringBuilder and StringBuffer
//In Java, strings created via the String class are immutable—once allocated in the String Constant Pool (SCP), their characters cannot be modified. Any operations that appear to change a string (e.g., using the + operator) actually allocate a completely new String object in memory, leaving the old object for garbage collection. This causes significant performance bottlenecks and memory churn when executing large loops or complex string concatenations.
//To address this, Java provides StringBuffer and StringBuilder, which manage a resizable internal character array (char[] or byte[] in newer versions) that can grow dynamically without allocating new object containers.
//Mutating a String:
//"Java" ──(+ " Programming")──> [ Creates a whole new Object: "Java Programming" ]
//
//Mutating a StringBuilder:
//[ Internal Buffer: "Java" ] ──(.append())──> [ Same Buffer Expanded: "Java Programming" ]
//The difference between StringBuffer and StringBuilder comes down to thread safety:
//•	StringBuffer (Java 4.0): Designed for multi-threaded safety. Every major manipulation method is marked with the synchronized modifier. This thread synchronization introduces an execution performance penalty because threads must acquire internal object locks.
//•	StringBuilder (Java 5): Designed for single-threaded applications. It drops the synchronized locks, making it significantly faster than StringBuffer. Always default to StringBuilder unless you explicitly require thread-safe concatenation across shared execution threads.


public class BufferVsBuilderDemo {
    public static void main(String[] args) {
        // --- 1. StringBuilder Common Operations ---
        StringBuilder sb = new StringBuilder("Java");
        System.out.println("Initial: " + sb);
        System.out.println("Initial capacity: " + sb.capacity()); // 16 (default) + 4 (length) = 20

        // append() adds to the end
        sb.append(" SE");
        System.out.println("After append: " + sb);

        // insert(offset, data) injects at a specific index
        sb.insert(4, " Standard");
        System.out.println("After insert: " + sb);

        // replace(start, end, str) replaces a range [start, end)
        sb.replace(0, 4, "OpenJDK");
        System.out.println("After replace: " + sb);

        // delete(start, end) removes characters in range [start, end)
        sb.delete(7, 16);
        System.out.println("After delete: " + sb);

        // reverse() modifies the sequence in place
        sb.reverse();
        System.out.println("After reverse: " + sb);

        // --- 2. Capacity & Pre-allocation ---
        // Pre-allocating capacity prevents internal array resizing overhead
        StringBuilder bufferWithCapacity = new StringBuilder(100);
        bufferWithCapacity.append("Pre-allocated capacity: ").append(bufferWithCapacity.capacity());
        System.out.println(bufferWithCapacity);

        // --- 3. StringBuffer Equivalent Operations ---
        // StringBuffer shares the same API as StringBuilder
        StringBuffer sbf = new StringBuffer("ThreadSafe");
        sbf.append("-Buffer").insert(10, "_Worker");
        System.out.println("StringBuffer: " + sbf);
    }
}