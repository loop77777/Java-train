package day10.ConstructorsAndInstantiation;
//Overloaded Constructors (Constructor Chaining)
//Constructor Overloading allows a class to have multiple constructors with the same name, differentiated strictly by their parameter list (arity, types, or order of arguments).
//
//To avoid duplicate code across multiple constructors, use constructor chaining via the this(...) keyword:
//
//this(...) calls another constructor within the same class.
//
//If used, this(...) must be the very first statement inside the constructor body.

public class OverloadedDemo {
    public static void main(String[] args) {
        HotelRoom r1 = new HotelRoom(101); // Uses 1-arg constructor
        HotelRoom r2 = new HotelRoom(201, "Deluxe"); // Uses 2-arg constructor
        HotelRoom r3 = new HotelRoom(301, "Penthouse", 450.0); // Uses 3-arg constructor

        r1.printRoomInfo();
        r2.printRoomInfo();
        r3.printRoomInfo();
    }
}
