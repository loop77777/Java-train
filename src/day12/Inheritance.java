package day12;

//Inheritance: IS-A vs. HAS-A
//IS-A (Inheritance): Implemented via extends or implements. Subclasses specialize a base type.
//
//HAS-A (Composition): A class holds references to other objects. Composition offers lower coupling and greater runtime flexibility than class inheritance.

// HAS-A component
class Engine {
    void ignite() { System.out.println("Engine started."); }
}

// Vehicle base class
class Vehicle {
    protected String registrationNumber;
    Vehicle(String reg) { this.registrationNumber = reg; }
}

// Car IS-A Vehicle, and HAS-A Engine
class Car extends Vehicle {
    private final Engine engine; // HAS-A

    Car(String reg) {
        super(reg);          // IS-A
        this.engine = new Engine();
    }

    void startCar() {
        engine.ignite();
        System.out.println("Car registered under " + registrationNumber + " is running.");
    }
}
