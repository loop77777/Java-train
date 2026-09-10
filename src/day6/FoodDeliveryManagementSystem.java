package day6;//Problem Statement
//Online Food Delivery Management System (Swiggy/Zomato Style)

//Project Title
//
//Online Food Delivery Management System (OFDMS)
//
//Business Background
//
//Food delivery platforms such as:
//
//Swiggy
//Zomato
//Uber Eats
//EatSure
//
//serve millions of users every day.
//
//Customers order food from nearby restaurants using mobile applications.
//
//To ensure smooth operations, the company wants to automate the complete order lifecycle from customer registration to order delivery.

//Customer Registration
//         ↓
//Restaurant Discovery
//         ↓
//Food Selection
//         ↓
//Cart Management
//         ↓
//Bill Calculation
//         ↓
//Discount Processing
//         ↓
//Payment Processing
//         ↓
//Order Confirmation
//         ↓
//Invoice Generation
//         ↓
//Delivery Tracking
//         ↓
//Order Delivery

//Module 1: Customer Management
//Business Requirement
//
//Every customer should be uniquely identified.
//
//Customer Details
//
//1
//Customer ID
//2
//Customer Name
//3
//Mobile Number
//4
//Email ID
//5
//Address
//6
//Premium Membership Status
//
//Sample Data
//
//1
//Customer ID : 1001
//2
//Customer Name : Jyothi Prasad
//3
//Mobile Number : 9876543210
//4
//Premium Member : Yes
//
//Module 2: Restaurant Management
//Business Requirement
//
//The system should display nearby restaurants.
//
//Restaurant Information
//
//1
//Restaurant ID
//2
//Restaurant Name
//3
//Location
//4
//Rating
//5
//Open/Closed Status
//
//Sample Data
//
//1
//Restaurant ID : R101
//2
//Name : Meghana Foods
//3
//Location : Bengaluru
//4
//Rating : 4.7
//5
//Status : Open
//6
//``
//
//Module 3: Menu Management
//Business Requirement
//
//Each restaurant maintains food items.
//
//Food Details
//
//1
//Food ID
//2
//Food Name
//3
//Food Category
//4
//Price
//5
//Availability
//
//Sample Data
//
//1
//Food ID : F501
//2
//Food Name : Chicken Biryani
//3
//Category : Non-Veg
//4
//Price : 299
//5
//Available : Yes
//
//Module 4: Cart Management
//Business Requirement
//
//Customers add multiple items into a cart before checkout.
//
//Customer Cart
//
//1
//Chicken Biryani
//2
//Paneer Pizza
//3
//Coke
//4
//French Fries
//
//Real-Time Array Usage
//Java
//1
//String[] cart = {
//2
//"Chicken Biryani",
//3
//"Paneer Pizza",
//4
//"Coke",
//5
//"French Fries"
//6
//};
//
//Module 5: Billing System
//Business Requirement
//
//System should calculate the total order amount.
//
//Example
//
//1
//Chicken Biryani = Rs.299
//2
// 
//3
//Paneer Pizza = Rs.249
//4
// 
//5
//Coke = Rs.60
//6
// 
//7
//French Fries = Rs.99
//
//Calculation
//
//1
//299
//2
//+249
//3
//+60
//4
//+99
//5
//------------
//6
//707
//
//Final Bill
//
//1
//Total Amount = Rs.707
//
//Module 6: Discount Engine
//Business Requirement
//
//Offer discounts based on order value.
//
//Discount Rules
//
//1
//Order >= 2000 → 20% Discount
//2
// 
//3
//Order >= 1000 → 10% Discount
//4
// 
//5
//Order >= 500 → 5% Discount
//6
// 
//7
//Otherwise
//8
//No Discount
//
//Example
//
//1
//Bill Amount = Rs.1200
//2
// 
//3
//Discount = 10%
//4
// 
//5
//Final Amount = Rs.1080
//
//Module 7: Payment Processing
//Business Requirement
//
//Allow multiple payment methods.
//
//Supported Payments
//
//1
//UPI
//2
// 
//3
//Credit Card
//4
// 
//5
//Debit Card
//6
// 
//7
//Net Banking
//8
// 
//9
//Cash On Delivery
//
//Example
//
//1
//Payment Mode = UPI
//2
// 
//3
//Amount = Rs.1080
//4
// 
//5
//Status = SUCCESS
//
//Module 8: Order Processing
//Business Requirement
//
//Process orders only when:
//
//
//1
//Restaurant Open
//2
// 
//3
//Food Available
//4
// 
//5
//Payment Successful
//
//Validation Rule
//Java
//1
//restaurantOpen &&
//2
//foodAvailable &&
//3
//paymentSuccess
//
//Result
//
//1
//Order Confirmed
//
//Module 9: Invoice Generation
//Business Requirement
//
//Generate invoice after successful payment.
//
//Sample Invoice
//
//1
//----------------------------------
//2
//FOOD INVOICE
//3
//----------------------------------
//4
// 
//5
//Customer : Jyothi Prasad
//6
// 
//7
//Order Id : ORD1001
//8
// 
//9
//Items:
//10
// 
//11
//Chicken Biryani Rs.299
//12
//Paneer Pizza Rs.249
//13
//Coke Rs.60
//14
//French Fries Rs.99
//15
// 
//16
//Total Rs.707
//17
// 
//18
//Discount Rs.35
//19
// 
//20
//Final Amount Rs.672
//21
// 
//22
//Payment : SUCCESS
//23
// 
//24
//----------------------------------
//
//Module 10: Delivery Tracking
//Business Requirement
//
//Track order delivery status.
//
//Order Status Flow
//
//1
//Placed
//2
//↓
//3
//Accepted
//4
//↓
//5
//Preparing
//6
//↓
//7
//Packed
//8
//↓
//9
//Out for Delivery
//10
//↓
//11
//Delivered

public class FoodDeliveryManagementSystem {

    // ==================================================
    // STATIC BLOCK
    // ==================================================

    static {

        System.out.println("======================================");
        System.out.println("WELCOME TO SWIGGY FOOD DELIVERY");
        System.out.println("======================================");

        System.out.println("Connecting To Restaurant Database...");
        System.out.println("Loading Food Menu...");
        System.out.println("Payment Gateway Ready...");
    }

    // ==================================================
    // INSTANCE BLOCK
    // ==================================================

    {

        System.out.println("\nCreating New Customer Order...");
    }

    // ==================================================
    // INSTANCE VARIABLES
    // ==================================================

    int customerId;
    String customerName;
    long mobileNumber;
    boolean premiumMember;

    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public FoodDeliveryManagementSystem(
            int customerId,
            String customerName,
            long mobileNumber,
            boolean premiumMember) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.premiumMember = premiumMember;
    }

    // ==================================================
    // PASS BY VALUE DEMO
    // ==================================================

    public static void applyCoupon(double billAmount) {

        billAmount = billAmount - 100;

        System.out.println(
                "Bill After Coupon Inside Method : "
                        + billAmount);
    }

    public static void main(String[] args) {

        // ==========================================
        // CUSTOMER REGISTRATION
        // ==========================================

        FoodDeliveryManagementSystem customer =
                new FoodDeliveryManagementSystem(
                        1001,
                        "Jyothi Prasad",
                        9876543210L,
                        true);

        System.out.println("\n===== CUSTOMER DETAILS =====");

        System.out.println(
                "Customer ID : "
                        + customer.customerId);

        System.out.println(
                "Customer Name : "
                        + customer.customerName);

        System.out.println(
                "Mobile Number : "
                        + customer.mobileNumber);

        System.out.println(
                "Premium Member : "
                        + customer.premiumMember);

        // ==========================================
        // ARRAY DECLARATION
        // ==========================================

        String[] cart;

        // ==========================================
        // ARRAY CONSTRUCTION
        // ==========================================

        cart = new String[4];

        // ==========================================
        // ARRAY INITIALIZATION
        // ==========================================

        cart[0] = "Pizza";
        cart[1] = "Burger";
        cart[2] = "French Fries";
        cart[3] = "Coke";

        System.out.println("\n===== CART ITEMS =====");

        for (int i = 0; i < cart.length; i++) {

            System.out.println(
                    cart[i]);
        }

        // ==========================================
        // FOOD DETAILS (DATATYPES)
        // ==========================================

        int foodId = 501;

        double pizzaPrice = 250.00;
        double burgerPrice = 150.00;
        double friesPrice = 100.00;
        double cokePrice = 50.00;

        char foodType = 'V';

        boolean foodAvailable = true;

        System.out.println("\n===== FOOD DETAILS =====");

        System.out.println(
                "Food ID : " + foodId);

        System.out.println(
                "Food Type : " + foodType);

        System.out.println(
                "Food Available : "
                        + foodAvailable);

        // ==========================================
        // ARITHMETIC OPERATORS
        // ==========================================

        double totalBill =
                pizzaPrice
                        + burgerPrice
                        + friesPrice
                        + cokePrice;

        System.out.println(
                "\nTotal Bill : Rs."
                        + totalBill);

        // ==========================================
        // PASS BY VALUE
        // ==========================================

        System.out.println(
                "\n===== PASS BY VALUE =====");

        System.out.println(
                "Original Bill Before Method Call : "
                        + totalBill);

        applyCoupon(totalBill);

        System.out.println(
                "Original Bill After Method Call : "
                        + totalBill);

        // ==========================================
        // RELATIONAL OPERATOR
        // ==========================================

        boolean freeDelivery =
                totalBill >= 500;

        System.out.println(
                "\nFree Delivery Eligible : "
                        + freeDelivery);

        // ==========================================
        // LOGICAL OPERATOR
        // ==========================================

        boolean restaurantOpen = true;

        boolean orderAccepted =
                restaurantOpen && foodAvailable;

        System.out.println(
                "Order Accepted : "
                        + orderAccepted);

        // ==========================================
        // IF DEMO
        // ==========================================

        System.out.println(
                "\n===== IF STATEMENT =====");

        if (restaurantOpen) {

            System.out.println(
                    "Restaurant Accepting Orders");
        }

        // ==========================================
        // IF ELSE DEMO
        // ==========================================

        System.out.println(
                "\n===== IF ELSE =====");

        boolean paymentDone = true;

        if (paymentDone) {

            System.out.println(
                    "Payment Successful");

        } else {

            System.out.println(
                    "Payment Failed");
        }

        // ==========================================
        // IF ELSE IF DEMO
        // ==========================================

        System.out.println(
                "\n===== DISCOUNT SLABS =====");

        if (totalBill >= 2000) {

            System.out.println(
                    "20% Discount");

        } else if (totalBill >= 1000) {

            System.out.println(
                    "10% Discount");

        } else if (totalBill >= 500) {

            System.out.println(
                    "5% Discount");

        } else {

            System.out.println(
                    "No Discount");
        }

        // ==========================================
        // TERNARY OPERATOR
        // ==========================================

        String orderStatus =
                paymentDone
                        ? "ORDER CONFIRMED"
                        : "ORDER FAILED";

        System.out.println(
                "\nOrder Status : "
                        + orderStatus);

        // ==========================================
        // SWITCH CASE
        // ==========================================

        System.out.println(
                "\n===== FOOD CATEGORY =====");

        int choice = 3;

        switch (choice) {

            case 1:
                System.out.println("Pizza");
                break;

            case 2:
                System.out.println("Burger");
                break;

            case 3:
                System.out.println("Biryani");
                break;

            case 4:
                System.out.println("South Indian");
                break;

            default:
                System.out.println("Invalid Choice");
        }

        // ==========================================
        // WHILE LOOP
        // ==========================================

        System.out.println(
                "\n===== PROCESSING ORDERS =====");

        int order = 1;

        while (order <= 5) {

            System.out.println(
                    "Processing Order "
                            + order);

            order++;
        }

        // ==========================================
        // DO WHILE LOOP
        // ==========================================

        System.out.println(
                "\n===== DISPLAY MENU =====");

        int menu = 1;

        do {

            System.out.println(
                    "Displaying Food Menu");

            menu++;

        } while (menu <= 3);

        // ==========================================
        // FOR LOOP
        // ==========================================

        System.out.println(
                "\n===== INVOICE GENERATION =====");

        for (int invoice = 1;
             invoice <= 5;
             invoice++) {

            System.out.println(
                    "Generating Invoice "
                            + invoice);
        }

        // ==========================================
        // BREAK
        // ==========================================

        System.out.println(
                "\n===== SEARCH ORDER =====");

        for (int orderId = 1001;
             orderId <= 1010;
             orderId++) {

            if (orderId == 1005) {

                System.out.println(
                        "Order Found");

                break;
            }

            System.out.println(
                    "Searching Order "
                            + orderId);
        }

        // ==========================================
        // CONTINUE
        // ==========================================

        System.out.println(
                "\n===== DELIVERY PROCESS =====");

        for (int delivery = 1;
             delivery <= 5;
             delivery++) {

            if (delivery == 3) {

                System.out.println(
                        "Order 3 Cancelled");

                continue;
            }

            System.out.println(
                    "Delivering Order "
                            + delivery);
        }

        System.out.println(
                "\n===== ORDER DELIVERED SUCCESSFULLY =====");
    }
}