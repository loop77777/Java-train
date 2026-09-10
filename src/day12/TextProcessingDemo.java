package day12;//Parsing, Tokenizing, and Pattern Matching
//Text parsing splits structured data into semantic components using delimiters or regular expressions (java.util.regex).
//
//String.split(regex): Quick tokenization based on regular expression boundaries.
//
//java.util.Scanner: Tokenizes input streams using whitespace or custom patterns.
//
//Pattern and Matcher: Compiles regular expressions and inspects matches via find/capture groups.

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.StringTokenizer;

public class TextProcessingDemo {
    public static void main(String[] args) {
        // 1. Tokenizing using split
        String csv = "Alice,30,Engineer;Bob,25,Analyst";
        String[] records = csv.split(";");
        for (String record : records) {
            String[] fields = record.split(",");
            System.out.println("Name: " + fields[0] + ", Role: " + fields[2]);
        }

        // 2. Pattern Matching with Capture Groups
        String logEntry = "IP: 192.168.1.45 - STATUS: 200 - TIMESTAMP: 2026-09-10";
        Pattern pattern = Pattern.compile("IP:\\s(?<ip>\\S+)\\s-\\sSTATUS:\\s(?<status>\\d{3})");
        Matcher matcher = pattern.matcher(logEntry);

        if (matcher.find()) {
            System.out.println("Extracted IP: " + matcher.group("ip"));
            System.out.println("HTTP Status Code: " + matcher.group("status"));
        }
    }
}