package day4;

class Hotel {



    static {



        System.out.println("Static Block");

    }



    public Hotel() {



        System.out.println("Constructor");

    }

}



public class Test {



    public static void main(String[] args) {

        System.out.println("Application Started");

        Hotel h1 = new Hotel();



        Hotel h2 = new Hotel();



        Hotel h3 = new Hotel();

    }

}