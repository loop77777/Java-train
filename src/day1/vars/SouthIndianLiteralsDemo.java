package day1.vars;

public class  SouthIndianLiteralsDemo {

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   SOUTH INDIAN TIFFIN & DARSHINI PRIMITIVE LITERALS DEMO  ");
        System.out.println("==========================================================\n");

        // 1. BOOLEAN: The Darshini "Meals Available" board
        boolean isFilterCoffeeReady = true;
        boolean isVadaSoldOut = false;

        System.out.println("--- 1. Darshini Board (Boolean: true/false) ---");
        System.out.println("Filter Coffee Ready : " + isFilterCoffeeReady);
        System.out.println("Medu Vada Sold Out  : " + isVadaSoldOut);
        System.out.println();


        // 2. BYTE & SHORT: Steel Tiffin Dabba Capacities
        // byte: Small Chutney Katori [-128 to 127 grams]
        byte chutneyGrams = 100;

        // short: 2-Tier Lunch Carrier [-32,768 to 32,767 grams]
        short sambarRiceGrams = 1500;

        System.out.println("--- 2. Steel Tiffin Dabba (Byte & Short Narrowing) ---");
        System.out.println("Chutney in small Katori (byte) : " + chutneyGrams + "g");
        System.out.println("Sambar in 2-tier Dabba (short) : " + sambarRiceGrams + "g");
        System.out.println();


        // 3. CHAR: Regional Letters & BMTC Ticket Line-Breaks
        char gradeStamp = 'A';                    // Grade 'A' Ghee
        char kannadaLetterA = '\u0C85';           // Unicode for Kannada letter 'ಅ'
        char tamilLetterA = '\u0B85';             // Unicode for Tamil letter 'அ'
        char paperFeed = '\n';                    // Conductor ticket machine line break

        System.out.println("--- 3. Single Letters & Unicode Seals (Char: '\\uXXXX') ---");
        System.out.println("Ghee Quality Grade  : " + gradeStamp);
        System.out.println("Kannada Letter 'ಅ'  : " + kannadaLetterA);
        System.out.println("Tamil Letter 'அ'    : " + tamilLetterA);
        System.out.print("Ticket Machine Feed :" + paperFeed);
        System.out.println();


        // 4. INT: Counting Cash & Underscores for Lakhs
        int onionDosaPrice = 0b1010000;          // Binary for Rs. 80 (0b prefix)
        int mealsTokenHex = 0x64;                // Hexadecimal for Token #100 (0x prefix)
        int octalCounter = 012;                  // Octal for 10 plates (0 prefix)
        int darshiniDailyTurnover = 15_50_000;   // Underscores for clear Indian Lakhs display

        System.out.println("--- 4. Tiffin Billing in Different Bases (Int) ---");
        System.out.println("Onion Dosa Rate from Binary (0b1010000) : Rs." + onionDosaPrice);
        System.out.println("Token Number from Hex (0x64)            : #" + mealsTokenHex);
        System.out.println("Plates served from Octal (012)          : " + octalCounter);
        System.out.println("Daily Cash Turnover (15_50_000)         : Rs." + darshiniDailyTurnover);
        System.out.println();


        // 5. LONG: Tirupati Hundi & State Highway Budget
        // Exceeds 2.14 Billion (214 Crores); requires mandatory 'L'
        long tirupatiAnnualHundiCollection = 1450_00_00_000L;

        System.out.println("--- 5. Heavy Treasury Numbers (Long: requires 'L') ---");
        System.out.println("Tirupati Annual Hundi Collection (Rs) : " + tirupatiAnnualHundiCollection);
        System.out.println();


        // 6. FLOAT & DOUBLE: Coffee Tumbler Volume vs. Gold Hallmarking Rate
        // float (32-bit): Coffee tumbler capacity (requires 'F')
        float coffeeTumblerLitres = 0.15F;

        // double (64-bit): High precision Tanishq gold rate in grams
        double goldRatePerGram = 7245.8575;

        System.out.println("--- 6. Tumbler Volume vs Gold Scale (Float 'F' & Double) ---");
        System.out.println("Filter Coffee Tumbler Volume (float) : " + coffeeTumblerLitres + " L");
        System.out.println("Tanishq Gold Rate per Gram (double)   : Rs." + goldRatePerGram);
        System.out.println("==========================================================");
    }
}

//JVM HEAP SPACE                                JVM THREAD STACK FRAME
//+------------------------------------------+    +------------------------------------------+
//|  Instance & Class Fields (new Object())  |    |     Local Variables (inside method)      |
//|                                          |    |                                          |
//|  1. JVM allocates block in heap          |    |  1. Fast day2.stack-pointer push (allocation) |
//|  2. Hardware bits zeroed out             |    |  2. NO memory zeroing (performance gain) |
//|  3. Default values guaranteed:           |    |  3. Raw memory contains leftover garbage |
//|     - byte/short/int/long -> 0           |    |  4. Definite Assignment Analysis guards  |
//|     - float/double        -> 0.0         |    |     against reading uninitialized slots  |
//|     - boolean             -> false       |    |                                          |
//|     - char                -> '\u0000'    |    |  int x;                                  |
//|     - Object/Reference    -> null        |    |  System.out.println(x); // COMPILE ERROR |
//+------------------------------------------+    +------------------------------------------+