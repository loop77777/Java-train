package day12;

//2. Dates, Numbers, and Currency Parsing & Tokenizing
//Enterprise platforms require localization features to safely present dates, numbers, and financial metrics across global regions without hardcoding format strings.
//•	Date Management: While historical classes like java.util.Date exist, modern Java architectures prioritize the Java 8 Date-Time API (java.time) because the old classes are not thread-safe and suffer from poor design choices (like 0-indexed months). java.time.format.DateTimeFormatter provides immutable, thread-safe parsing and formatting.
//•	Localization (java.util.Locale): Represents a specific geographical or political region.
//•	Currency and Decimal Formatting: NumberFormat uses system locale tags to automatically append correct symbols (e.g., $, ₹) and enforce localized digit separators (commas vs. periods).
//•	Tokenizing vs. Parsing: Parsing converts raw text inputs into operational data types (e.g., converting "25" to an int). Tokenizing breaks a single string down into smaller chunks based on delimiter characters using String.split() or StringTokenizer.

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class LocalizationAndDatesDemo {
    public static void main(String[] args) throws Exception {
        // 1. Date creation, manipulation, and formatting
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(30);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println("Formatted Date: " + futureDate.format(dtf));

        // Parsing dates
        LocalDate parsedDate = LocalDate.parse("15-Aug-2026", dtf);
        System.out.println("Parsed Year: " + parsedDate.getYear());

        // 2. Number and Currency Localization
        double transactionAmount = 12450.75;

        // US Locale formatting
        NumberFormat usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("US Currency: " + usCurrency.format(transactionAmount)); // $12,450.75

        // German Locale formatting
        NumberFormat deCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println("DE Currency: " + deCurrency.format(transactionAmount)); // 12.450,75 €
    }
}
