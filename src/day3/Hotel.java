package day3;//What is an Initialization Block?
//
//An initialization block is a piece of code that executes automatically during class loading or object creation.

//Java provides two types:
//
//
//1. Static Initialization Block
//
//2. Instance Initialization Block
//3. Static Initialization Block
//
//static {
//
// / initialization code
//
//}
//
//Characteristics
//Executes only once.
//Executes when the class is loaded into memory.
//Executes before main().
//Used to initialize static resources.
//
//
//When a hotel application starts:
//
//Load Configuration
//
//Load Tax Rates
//
//Initialize Database URL
//
//Load day3.Hotel Details
//
//These should execute once only

class Hotel {

    static {

        System.out.println("day3.Hotel Configuration Loaded");
        System.out.println("Tax Configuration Loaded");
        System.out.println("Room Master Data Loaded");
    }

    public static void main(String[] args) {

        System.out.println("Application Started");
    }
}

// class day3.Hotel {
//
//    static {
//
//        System.out.println("Static Block");
//    }
//
//    public day3.Hotel() {
//
//        System.out.println("Constructor");
//    }
//}
//
//public class day4.Test {
//
//    public static void main(String[] args) {
//
//        day3.Hotel h1 = new day3.Hotel();
//
//        day3.Hotel h2 = new day3.Hotel();
//
//        day3.Hotel h3 = new day3.Hotel();
//    }
//}

//Instance Initialization Block

//{
//   // initialization code
//}

//Characteristics
//Executes for every object.
//Executes before constructor body.
//Compiler copies this code into every constructor.

