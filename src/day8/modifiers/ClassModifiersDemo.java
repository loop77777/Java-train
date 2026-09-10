package day8.modifiers;

// =========================================================================
// 4. ACCESS MODIFIER: 'public' TOP-LEVEL CLASS
// Globally accessible across all packages in the application runtime.
// =========================================================================
public class ClassModifiersDemo {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      CLASS DECLARATIONS & MODIFIERS DEMO         ");
        System.out.println("==================================================\n");

        // 1. Abstract Class: Direct instantiation is forbidden by compiler
        // DeliveryVehicle vehicle = new DeliveryVehicle("KA-01-AB-1234"); // COMPILE ERROR: Cannot instantiate abstract type

        // 2. Concrete Subclass (Polymorphic Reference)
        DeliveryVehicle myBike = new ElectricBike("KA-05-EV-9090", 3.5);
        myBike.displayVehicleInfo();

        double energyConsumed = myBike.calculateFuelConsumption(20.0);
        System.out.println("Energy Used for 20 KM : " + energyConsumed + " kWh");

        // 3. Final Class Direct Instance Usage
        ElectricBike bikeDirect = new ElectricBike("KA-03-EB-4411", 5.0);
        System.out.println("Battery Capacity      : " + bikeDirect.getBatteryCapacityKWh() + " kWh");

        // 4. Default / Package-Private Class Execution
        InternalRoutePlanner planner = new InternalRoutePlanner();
        planner.planRoute("Indiranagar 100ft Road");

        /* =====================================================================
         * COMPILER ILLEGAL COMBINATIONS & EXTENSION ERRORS (DO NOT UNCOMMENT)
         * =====================================================================
         *
         * [Illegal Subclassing of Final Class]
         * class SuperElectricBike extends ElectricBike {
         *     // COMPILE ERROR: The type SuperElectricBike cannot subclass the final class ElectricBike
         * }
         *
         * [Illegal Modifier Combination]
         * abstract final class InvalidStructure {
         *     // COMPILE ERROR: Illegal combination of modifiers: 'abstract' and 'final'
         * }
         *
         * [Illegal Access Modifiers on Top-Level Class]
         * private class PrivateTopClass {}    // COMPILE ERROR: Illegal modifier for the class PrivateTopClass
         * protected class ProtectedClass {}   // COMPILE ERROR: Illegal modifier for the class ProtectedClass
         */
    }
}
