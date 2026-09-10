package day5;//1. Java Flow Control Statements
//Flow control statements change the sequential execution of code by adding conditions, loops, and jumps.
//: [Sequential Execution] (Top to Bottom)
//                 │
//                 ▼
//      ┌────────────────────┐
//      │  Flow Control?     │
//      └──────────┬─────────┘
//                 │
//  ┌──────────────┼──────────────┐
//  ▼              ▼              ▼
//[Selection]  [Iteration]     [Jumps]
// • if         • while        • break
// • switch     • do-while     • continue
//              • for          • return


public class IfDemo {

    public static void main(String[] args) {

        boolean hasMembership = true;

        if (hasMembership) {
            System.out.println("Welcome to Premium Shopping Mall");
        }

        System.out.println("Security Check Completed");
    }
}

//Payment Done?
//     │
// ┌───┴───┐
// ▼       ▼
//YES      NO
//Order    Payment
//Placed   Failed

