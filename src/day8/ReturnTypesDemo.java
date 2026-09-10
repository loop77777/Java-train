

//Legal Return Types
//
//Java methods declare precisely what type of data they will hand back to their invoker upon exit. The runtime system enforces rigid rules here:
//
//
//
//Primitives: Returns raw values (int, char, etc.) by copying bits from the stack frame.
//
//
//
//Objects / Reference Types: Returns the precise memory heap pointer reference to an object.
//
//
//
//Arrays: Returns a pointer to a contiguous sequence of elements stored in the heap.
//
//
//
//Covariant Return Types: Starting from Java 5/7, a method overriding a parent class method can return a subtype of the type declared in the parent method signature.
//
//
//
//Void: Explicitly signifies that the method executes operations but pushes zero data back onto the operand stack. i need working COde
package day8;

import java.util.Arrays;

// =========================================================================
// 1. BASE AND SUBTYPE CLASSES (For Covariant Return Types)
// =========================================================================
class FoodItem {
    public String getCategory() {
        return "Generic Food Item";
    }
}

class GourmetPizza extends FoodItem {
    @Override
    public String getCategory() {
        return "Gourmet Wood-Fired Pizza";
    }

    public String getCrustType() {
        return "Sourdough Thin Crust";
    }
}

// Parent Factory Class
class RestaurantKitchen {
    // Declares return type as the base class 'FoodItem'
    public FoodItem prepareDish() {
        return new FoodItem();
    }
}

// Child Factory Class demonstrating Covariant Return Type
class ItalianKitchen extends RestaurantKitchen {
    // COVARIANT RETURN TYPE: Returns 'GourmetPizza' (a subtype of 'FoodItem')
    @Override
    public GourmetPizza prepareDish() {
        return new GourmetPizza();
    }
}

// =========================================================================
// 2. MAIN DEMO CLASS COVERING ALL LEGAL RETURN TYPES
// =========================================================================
public class ReturnTypesDemo {

    // A. Primitive Return Type: Returns raw bit values directly from stack frame
    public int calculateDiscountedPrice(int originalPrice, int discountPercentage) {
        return originalPrice - (originalPrice * discountPercentage / 100);
    }

    // B. Reference / Object Return Type: Returns a heap pointer reference
    public String fetchCustomerEmail(String customerName) {
        return customerName.toLowerCase().replace(" ", ".") + "@example.com";
    }

    // C. Array Return Type: Returns a reference to heap array structure
    public String[] generatePopularCuisines() {
        return new String[] { "South Indian", "North Indian", "Italian", "Continental" };
    }

    // D. Void Return Type: Pushes zero data back onto the caller's stack frame
    public void printOrderReceipt(String orderId, double amount) {
        System.out.println("[VOID] Receipt for Order #" + orderId + " | Amount Paid: Rs." + amount);
        // Explicit empty return is optional, signifies control exit
        return;
    }

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("       JAVA LEGAL RETURN TYPES: WORKING DEMO      ");
        System.out.println("==================================================\n");

        ReturnTypesDemo demo = new ReturnTypesDemo();

        // 1. Primitive Return Execution
        int finalPrice = demo.calculateDiscountedPrice(500, 20);
        System.out.println("1. Primitive Return (int)         : Rs." + finalPrice);

        // 2. Reference / Object Return Execution
        String email = demo.fetchCustomerEmail("Jyothi Prasad");
        System.out.println("2. Object Return (String ref)     : " + email);

        // 3. Array Return Execution
        String[] cuisines = demo.generatePopularCuisines();
        System.out.println("3. Array Return (String[])        : " + Arrays.toString(cuisines));

        // 4. Void Return Execution
        System.out.print("4. ");
        demo.printOrderReceipt("ORD-9901", 799.50);

        // 5. Covariant Return Type Execution
        System.out.println("\n--- 5. Covariant Return Type Demo ---");
        RestaurantKitchen standardKitchen = new RestaurantKitchen();
        FoodItem genericItem = standardKitchen.prepareDish();
        System.out.println("Standard Kitchen returns          : " + genericItem.getCategory());

        ItalianKitchen specializedKitchen = new ItalianKitchen();
        // No explicit downcasting needed because the overridden method returns GourmetPizza directly
        GourmetPizza pizzaItem = specializedKitchen.prepareDish();
        System.out.println("Specialized Kitchen returns       : " + pizzaItem.getCategory());
        System.out.println("Gourmet Specific Property         : Crust -> " + pizzaItem.getCrustType());
    }
}




