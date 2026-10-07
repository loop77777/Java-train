Problem Statement

1.1 Business Context & Domain Background

QuickBite E-Commerce (a multi-category online retail platform) processes customer orders across regions like Hyderabad, Bangalore, and Chennai. The system categorizes transactions into departments (e.g., Electronics, Books, Fashion) and categorizes users into Regular and Premium loyalty tiers. To incentivize customer retention, the platform applies tier-based discount rules and generates daily sales metrics for management review.

1.2 The Technical Problem

The current production backend is built entirely on Legacy Java 7 Architecture. While the core domain follows standard Object-Oriented principles, the software design faces severe operational and architectural constraints as business volume grows:

1. Verbose Strategy Pattern & Class Explosion
   To apply tier-based discounts, the application uses the classic GoF Strategy Pattern (DiscountStrategy, PremiumDiscount, RegularDiscount).
   •	The Issue: Creating a physical .java class for every micro-variation of a business rule (e.g., 5% vs. 15% discount) bloats the codebase. Adding new seasonal or flash-sale discounts requires creating and compiling new concrete class files.
2. Imperative, Duplicated Data Processing Logic (ReportService)
   The reporting service generates critical business insights (e.g., filtering high-value orders, computing total revenue, aggregating order counts by category).
   •	The Issue: Every reporting method repeatedly implements imperative boilerplate:

1.	Instantiating temporary mutable collections (new ArrayList<Order>()).
2.	Executing external loops (for (Order order : orders)).
3.	Mutating state step-by-step (result.add(order) or updating map counts manually). This duplication makes maintenance error-prone and prevents easy multi-threaded parallel execution across millions of records.
3. Legacy Date API Bottlenecks (java.util.Date & SimpleDateFormat)
   Order dates are recorded using java.util.Date and formatted using java.text.SimpleDateFormat.
   •	The Issue: SimpleDateFormat is not thread-safe. Sharing a single formatter instance across multi-threaded web servers causes date corruption or runtime crashes. Additionally, java.util.Date is mutable, exposing internal domain states to accidental modification.
4. Null Pointer Instability
   Order instances reference Customer objects directly without null-safe wrappers.
   •	The Issue: If an anonymous guest user places an order (customer == null), invoking methods like order.getCustomer().getName() triggers uncaught NullPointerExceptions (NPE), disrupting the reporting pipeline.
   
1.3 Project Objectives

   The objective is to analyze this existing Java 7 reference codebase, understand its architectural limitations, and establish a foundational baseline so that the entire subsystem can be refactored into a declarative, functional Java 8 Stream & Lambda Pipeline.
2. System Architecture & UML Class Diagram
The following class diagram represents the structure of the Java 7 system (com.company.ordermanagement package):
   +-----------------------------------------------------------------------------------+
   |                            com.company.ordermanagement                            |
   +-----------------------------------------------------------------------------------+

            +-----------------------+                    +-------------------+
            |    DiscountStrategy   |                    |     Customer      |
            |     <<Interface>>     |                    +-------------------+
            +-----------------------+                    | - customerId: int |
            | +calculateDiscount()  |                    | - name: String    |
            +-----------------------+                    | - city: String    |
                        ^                                | - premium: boolean|
                        |                                +-------------------+
           +------------+------------+                   | +getCustomerId()  |
           |                         |                   | +getName()        |
+----------------------+  +---------------------+        | +getCity()        |
|   PremiumDiscount    |  |   RegularDiscount   |        | +isPremium()      |
+----------------------+  +---------------------+        +-------------------+
| +calculateDiscount() |  | +calculateDiscount()|                  ^
+----------------------+  +---------------------+                  | 1
|
| 0..1
+-------------------+
|       Order       |
+-------------------+
| - orderId: int    |
| - customer: Cust  |
| - amount: double  |
| - category: String|
| - orderDate: Date |
+-------------------+
| +getOrderId()     |
| +getCustomer()    |
| +getAmount()      |
| +getCategory()    |
| +getOrderDate()   |
+-------------------+
^
|
| Uses
+-------------------+
|   ReportService   |
+-------------------+
| +getElectronics() |
| +getHighValue()   |
| +getTotalRevenue()|
| +categoryReport() |
| +premiumOrders()  |
+-------------------+


+-----------------------------------------------------------------------------------+
|                            com.company.ordermanagement                            |
+-----------------------------------------------------------------------------------+

            +-----------------------+                    +-------------------+
            |    DiscountStrategy   |                    |     Customer      |
            |     <<Interface>>     |                    +-------------------+
            +-----------------------+                    | - customerId: int |
            | +calculateDiscount()  |                    | - name: String    |
            +-----------------------+                    | - city: String    |
                        ^                                | - premium: boolean|
                        |                                +-------------------+
           +------------+------------+                   | +getCustomerId()  |
           |                         |                   | +getName()        |
+----------------------+  +---------------------+        | +getCity()        |
|   PremiumDiscount    |  |   RegularDiscount   |        | +isPremium()      |
+----------------------+  +---------------------+        +-------------------+
| +calculateDiscount() |  | +calculateDiscount()|                  ^
+----------------------+  +---------------------+                  | 1
|
| 0..1
+-------------------+
|       Order       |
+-------------------+
| - orderId: int    |
| - customer: Cust  |
| - amount: double  |
| - category: String|
| - orderDate: Date |
+-------------------+
| +getOrderId()     |
| +getCustomer()    |
| +getAmount()      |
| +getCategory()    |
| +getOrderDate()   |
+-------------------+
^
|
| Uses
+-------------------+
|   ReportService   |
+-------------------+
| +getElectronics() |
| +getHighValue()   |
| +getTotalRevenue()|
| +categoryReport() |
| +premiumOrders()  |
+-------------------+