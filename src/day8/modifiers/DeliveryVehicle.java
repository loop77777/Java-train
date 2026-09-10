package day8.modifiers;

//10. Class Declarations and Modifiers
//Access and non-access modifiers govern visibility and inheritance mechanics across scopes:
//
//Access Modifiers (Top-level classes): Can only be declared public (accessible to all packages across the entire application runtime) or default/package-private (omitting the keyword makes it visible only to classes residing inside the identical package).
//
//Non-Access Modifiers:
//
//final: The class cannot be subclassed (breaks polymorphism capability; e.g., java.lang.String).
//
//abstract: The class cannot be instantiated via the new keyword. It exists purely as a baseline template containing partial signatures for concrete subclasses to extend.

// =========================================================================
// 1. NON-ACCESS MODIFIER: 'abstract'
// Cannot be instantiated directly with 'new'. Serves as a contract/blueprint.
// =========================================================================
public abstract class DeliveryVehicle {

    private final String registrationNumber;

    public DeliveryVehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // Abstract method: Enforces concrete implementation in subclasses
    public abstract double calculateFuelConsumption(double distanceInKm);

    // Concrete shared behavior
    public void displayVehicleInfo() {
        System.out.println("Vehicle Reg : " + registrationNumber);
    }
}



// =========================================================================
// 2. NON-ACCESS MODIFIER: 'final' (Inheriting from abstract class)
// Prevents any further subclassing/inheritance (immutable class design).
// =========================================================================
final class ElectricBike extends DeliveryVehicle {

    private final double batteryCapacityKWh;

    public ElectricBike(String registrationNumber, double batteryCapacityKWh) {
        super(registrationNumber);
        this.batteryCapacityKWh = batteryCapacityKWh;
    }

    @Override
    public double calculateFuelConsumption(double distanceInKm) {
        // 0.05 kWh per KM
        return distanceInKm * 0.05;
    }

    public double getBatteryCapacityKWh() {
        return batteryCapacityKWh;
    }
}

// =========================================================================
// 3. ACCESS MODIFIER: 'default' / Package-Private (No modifier keyword)
// Visible ONLY to classes residing within 'com.day11.demo.modifiers' package.
// =========================================================================
class InternalRoutePlanner {

    void planRoute(String destination) {
        System.out.println("[Package-Private] Optimized path computed to: " + destination);
    }
}

