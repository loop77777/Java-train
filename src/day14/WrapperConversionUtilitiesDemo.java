package day14;

public class WrapperConversionUtilitiesDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. PARSING UNSTRUCTURED STRINGS TO PRIMITIVES ===");
        String portInput = "8080";
        String rateInput = "14.95";
        String flagInput = "True"; // Case-insensitive: "true", "TRUE", "TrUe" all return true

        // Static parseXxx methods return raw primitives
        int port = Integer.parseInt(portInput);
        double rate = Double.parseDouble(rateInput);
        boolean isEnabled = Boolean.parseBoolean(flagInput);

        System.out.printf("Parsed Primitives -> Port: %d, Rate: %.2f, Enabled: %b%n",
                port, rate, isEnabled);


        System.out.println("\n=== 2. PARSING STRINGS DIRECTLY TO WRAPPER OBJECTS ===");
        // valueOf(String) returns a boxed Heap object reference
        Integer boxedPort = Integer.valueOf(portInput);
        Double boxedRate = Double.valueOf(rateInput);
        Boolean boxedFlag = Boolean.valueOf(flagInput);

        System.out.println("Boxed Types: " + boxedPort.getClass().getSimpleName()
                + ", " + boxedRate.getClass().getSimpleName());


        System.out.println("\n=== 3. RADIX / BASE CONVERSIONS ===");
        // Parsing non-decimal numerical strings
        String binaryBits = "10110";        // Binary (base 2)
        String hexCode = "7F";              // Hexadecimal (base 16)
        String octalStr = "77";             // Octal (base 8)

        int fromBinary = Integer.parseInt(binaryBits, 2);
        int fromHex = Integer.parseInt(hexCode, 16);
        int fromOctal = Integer.parseInt(octalStr, 8);

        System.out.println("Binary '10110' (base 2)   -> " + fromBinary); // 22
        System.out.println("Hex '7F' (base 16)        -> " + fromHex);    // 127
        System.out.println("Octal '77' (base 8)       -> " + fromOctal);  // 63

        // Formatting back to radix representations
        System.out.println("255 to Binary String: " + Integer.toBinaryString(255));
        System.out.println("255 to Hex String:    " + Integer.toHexString(255));


        System.out.println("\n=== 4. CROSS-TYPE NARROWING / WIDENING (xxxValue) ===");
        Double preciseMetric = Double.valueOf(12345.6789);

        // Extracting various primitive representations from the wrapper
        byte asByte = preciseMetric.byteValue();     // Narrowing truncation with data loss
        int asInt = preciseMetric.intValue();       // Truncates fractional bits
        long asLong = preciseMetric.longValue();
        float asFloat = preciseMetric.floatValue();

        System.out.println("Original Double: " + preciseMetric);
        System.out.println("Extracted as int:  " + asInt);
        System.out.println("Extracted as byte (narrowed/overflowed): " + asByte);


        System.out.println("\n=== 5. INPUT SANITIZATION & EXCEPTION SAFETY ===");
        String badNumber = "450px"; // Trailing unit breaks standard parsing

        try {
            int result = Integer.parseInt(badNumber);
        } catch (NumberFormatException ex) {
            System.err.println("Caught Expected NumberFormatException: " + ex.getMessage());
            System.out.println("Rule: Input must be strictly numeric characters (optional leading +/-).");
        }
    }
}

