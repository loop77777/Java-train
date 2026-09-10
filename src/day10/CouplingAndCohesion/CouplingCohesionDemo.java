package day10.CouplingAndCohesion;//Coupling and Cohesion
//These two software design metrics dictate how maintainable, testable, and robust an object-oriented codebase is.
//
//+-----------------------------------+-----------------------------------+
//|             COHESION              |             COUPLING              |
//|   (Internal focus of a module)    |  (Interdependence between classes)|
//|                                   |                                   |
//|   Goal: HIGH COHESION             |   Goal: LOOSE (LOW) COUPLING      |
//|   One class = One clear purpose   |   Changes in Class A do not       |
//|                                   |   break Class B                   |
//+-----------------------------------+-----------------------------------+
//Cohesion (Aim for High Cohesion)
//What it is: Measures how focused and closely related the responsibilities of a single class are (Single Responsibility Principle).
//
//Low Cohesion (Bad): A "God Class" that handles database queries, UI rendering, calculations, and logging all at once.
//
//High Cohesion (Good): A class that focuses purely on one cohesive task (e.g., a TaxCalculator only calculates tax; a ReceiptPrinter only formats strings for output).
//
//Coupling (Aim for Loose Coupling)
//What it is: Measures the degree of direct dependency between separate classes.
//
//Tight Coupling (Bad): Class A directly accesses and alters the private fields of Class B, or instantiates hardcoded concrete implementations of Class B inside itself. Changes to B immediately break A.
//
//Loose Coupling (Good): Classes communicate via clean public interfaces, well-defined method boundaries, or Dependency Injection.


// ==========================================
// LOW COHESION & TIGHT COUPLING (BAD DESIGN)
// ==========================================
class BadOrderManager {
    // Violates High Cohesion: Manages items, taxes, printing, and billing internally
    public void processOrder(String item, double price) {
        double tax = price * 0.18; // Tax calculation logic
        double total = price + tax;

        // Tight coupling to System.out & hardcoded console formatting
        System.out.println("[BadDesign] Bill for: " + item);
        System.out.println("[BadDesign] Total: $" + total);
    }
}

// ===========================================
// HIGH COHESION & LOOSE COUPLING (GOOD DESIGN)
// ===========================================

// Responsibility 1: Pure tax calculation (Highly Cohesive)
interface TaxStrategy {
    double calculateTax(double amount);
}

class StandardTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
}

// Responsibility 2: day10.CouplingAndCohesion.Order representation
class Order {
    private final String itemName;
    private final double price;

    public Order(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
}

// Responsibility 3: day10.CouplingAndCohesion.Order processing (Loosely coupled via day10.CouplingAndCohesion.TaxStrategy interface)
class OrderProcessor {
    private final TaxStrategy taxStrategy;

    // Dependency injection achieves loose coupling
    public OrderProcessor(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double computeFinalTotal(Order order) {
        return order.getPrice() + taxStrategy.calculateTax(order.getPrice());
    }
}

public class CouplingCohesionDemo {
    public static void main(String[] args) {
        System.out.println("--- Bad Design (Tightly Coupled) ---");
        BadOrderManager badManager = new BadOrderManager();
        badManager.processOrder("Monitor", 300.0);

        System.out.println("\n--- Good Design (Loosely Coupled, Highly Cohesive) ---");
        Order order = new Order("Monitor", 300.0);
        TaxStrategy standardTax = new StandardTaxStrategy();
        OrderProcessor processor = new OrderProcessor(standardTax);

        double total = processor.computeFinalTotal(order);
        System.out.println("Item: " + order.getItemName() + " | Final Total: $" + total);
    }
}