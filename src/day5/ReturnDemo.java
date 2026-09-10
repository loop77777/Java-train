package day5;

public class ReturnDemo {

    public static boolean validateAccount(
            int accountNumber) {

        if(accountNumber <= 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        boolean result =
                validateAccount(12345);

        System.out.println(
                "Valid Account : " + result);
    }
}

//Real-Time Problem Statement for day5.BankingFlowControlDemo
//Project Title
//
//ABC Bank Customer Banking Management System
//
//Business Background
//
//ABC Bank has branches across India and serves thousands of customers daily.
//
//The bank wants to automate its customer operations such as:
//
//Customer Login
//Account Verification
//Loan Eligibility Checking
//ATM Withdrawal Verification
//Account Type Selection
//Customer Queue Management
//ATM Menu Display
//Receipt Generation
//Account Listing
//Customer Search
//Blocked Account Handling
//Transaction Validation

//Customer Visits Bank
//          |
//          v
//     Login Check
//          |
//          v
//   Account Verification
//          |
//          v
//  Loan Eligibility Check
//          |
//          v
// ATM Withdrawal Validation
//          |
//          v
// Account Type Selection
//          |
//          v
// Customer Queue Processing
//          |
//          v
// ATM Menu Display
//          |
//          v
// Receipt Generation
//          |
//          v
// Display Available Accounts
//          |
//          v
// Search Customer
//          |
//          v
// Skip Blocked Accounts
//          |
//          v
// Validate Transaction
//          |
//          v
// End



