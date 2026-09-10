package day5;

public class ElseIfDemo {

    public static void main(String[] args) {

        int experience = 7;

        if (experience >= 15) {
            System.out.println("Bonus = Rs.50,000");
        }
        else if (experience >= 10) {
            System.out.println("Bonus = Rs.30,000");
        }
        else if (experience >= 5) {
            System.out.println("Bonus = Rs.15,000");
        }
        else {
            System.out.println("Bonus = Rs.5,000");
        }
    }
}

//Card Valid?
//     │
//    Yes
//     │
//PIN Correct?
//     │
//    Yes
//     │
//Withdraw Cash

