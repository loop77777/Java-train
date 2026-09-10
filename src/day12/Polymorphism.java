package day12;

//Polymorphism: Overloading vs. Overriding
//Compile-Time Polymorphism (Method Overloading): Methods within the same class share a name but differ in parameter count, type, or order. Resolution happens during compilation.
//
//Runtime Polymorphism (Method Overriding): A subclass provides a concrete implementation of an inherited method using matching signatures. Resolution uses dynamic method dispatch at runtime.

class Calculator {
    // Overloaded methods (Resolved at compile-time)
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}

class Animal {
    void sound() { System.out.println("Generic animal sound."); }
}

class Dog extends Animal {
    // Overridden method (Dynamic dispatch at runtime)
    @Override
    void sound() { System.out.println("Bark."); }
}

class Cat extends Animal {
    @Override
    void sound() { System.out.println("Meow."); }
}
