package day3.shrms;//Smart Hotel Management & Reservation System (SHMRS)
//Module 1: Datatypes, Variables, Arrays, Literals, Pass By Value, Initialization Blocks
//________________________________________
//Problem Statement
//A luxury hotel chain wants to build a Smart Hotel Management & Reservation System (SHMRS).
//The hotel needs to:
//        1.	Manage room inventory.
//2.	Manage customer registrations.
//3.	Allocate rooms.
//        4.	Calculate room charges.
//5.	Maintain room status.
//6.	Store guest information.
//7.	Track occupancy.
//Before implementing OOP and advanced features, the basic foundation of the system must be built using:
//        •	Datatypes
//•	Variables
//•	Arrays
//•	Literals
//•	Initialization Blocks
//•	Method Calls
//•	Passing Variables
//•	Java Pass By Value
//________________________________________
//Real-Time Scenario
//Hotel contains:
//        100 Rooms
//day3.shrms.Room Types:
//Deluxe
//        Premium
//Suite
//Each room should store:
//day3.shrms.Room Number
//day3.shrms.Room Type
//day3.shrms.Room Rent
//Availability
//Floor Number
//Each customer should store:
//Customer Id
//Name
//Mobile Number
//Passport Number
//________________________________________
//1. Datatypes
//Every hotel system starts with data.
//Problem Statement
//A luxury hotel wants to initialize its booking system.
//The system should:
//        1.	Load hotel information.
//2.	Store room details.
//3.	Store customer details.
//4.	Maintain room inventory using arrays.
//5.	Calculate room rent.
//6.	Demonstrate Java Pass-By-Value.
//7.	Load hotel configuration using static blocks.
//8.	Create room objects using initialization blocks.
//        package com.hotel.foundation;

import java.util.Scanner;

class Room {
    private final int roomNumber;
    private final double roomRent;

    static {
        System.out.println("=================================");
        System.out.println("Hotel System Initializing...");
        System.out.println("Loading day3.shrms.Room Master Data...");
        System.out.println("=================================");
    }

    {
        System.out.println("\n[Lifecycle] day3.shrms.Room Object Created (IIB executed)");
    }

    public Room(int roomNumber, double roomRent) {
        this.roomNumber = roomNumber;
        this.roomRent = roomRent;
        System.out.println("[Lifecycle] Constructor Executed for day3.shrms.Room #" + roomNumber);
    }

    public int getRoomNumber() { return roomNumber; }
    public double getRoomRent() { return roomRent; }
}

public class InteractiveHotelManagementDemo {

    public static void calculateBill(double rent, int days) {
        double totalBill = rent * days;
        System.out.println("-> Total Bill for " + days + " days @ ₹" + rent + "/day = ₹" + totalBill);
    }

    public static void main(String[] args) {

        System.out.println("\n===== INTERACTIVE HOTEL MANAGEMENT SYSTEM =====");

        // Try-with-resources: automatically closes the Scanner and releases System.in
        try (Scanner scanner = new Scanner(System.in)) {

            // 1. Reading String (Line input)
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();

            // 2. Reading byte, short, int, long primitives
            System.out.print("Enter Floor Number (byte): ");
            byte floorNumber = scanner.nextByte();

            System.out.print("Enter day3.shrms.Room Number (int): ");
            int roomNumber = scanner.nextInt();

            System.out.print("Enter Guest Mobile Number (long): ");
            long mobileNumber = scanner.nextLong();

            // 3. Reading float and double primitives
            System.out.print("Enter day3.shrms.Room Base Rent (double): ₹");
            double roomRent = scanner.nextDouble();

            System.out.print("Enter Applied Discount Percentage (float): ");
            float discount = scanner.nextFloat();

            // 4. Reading char (Taking first char from next token)
            System.out.print("Enter day3.shrms.Room Category [D: Deluxe, P: Premium, S: Suite]: ");
            char roomCategory = scanner.next().toUpperCase().charAt(0);

            // 5. Reading boolean
            System.out.print("Is day3.shrms.Room Available? (true/false): ");
            boolean isAvailable = scanner.nextBoolean();

            // 6. Number of stay days
            System.out.print("Enter Number of Stay Days: ");
            int stayDays = scanner.nextInt();

            // -------------------------------------------------------------
            // DISPLAYING INPUT SUMMARY
            // -------------------------------------------------------------
            System.out.println("\n=================================");
            System.out.println("       GUEST DETAILS SUMMARY      ");
            System.out.println("=================================");
            System.out.println("Guest Name    : " + customerName);
            System.out.println("Floor Number  : " + floorNumber);
            System.out.println("day3.shrms.Room Number   : " + roomNumber);
            System.out.println("Mobile Number : " + mobileNumber);
            System.out.println("Category      : " + roomCategory);
            System.out.println("Discount      : " + discount + "%");
            System.out.println("Base Rent     : ₹" + roomRent);
            System.out.println("Available     : " + isAvailable);

            // -------------------------------------------------------------
            // METHOD CALL & OBJECT INITIALIZATION
            // -------------------------------------------------------------
            System.out.println("\n--- Calculating Final Tariff ---");
            calculateBill(roomRent, stayDays);

            System.out.println("\n--- Creating day3.shrms.Room Entity ---");
            Room room = new Room(roomNumber, roomRent);
            System.out.println("Confirmed day3.shrms.Room Key: #" + room.getRoomNumber() + " with Daily Rate: ₹" + room.getRoomRent());

        } catch (Exception e) {
            System.err.println("\n[Input Error] Invalid data format entered: " + e.getMessage());
        }

        System.out.println("\n===== APPLICATION SESSION CLOSED =====");
    }
}