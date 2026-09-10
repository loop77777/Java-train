package day5;

public class BankingFlowControlDemo {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("BANKING MANAGEMENT SYSTEM");
        System.out.println("======================================");

        ifDemo();

        ifElseDemo();

        elseIfDemo();

        nestedIfDemo();

        switchDemo();

        whileDemo();

        doWhileDemo();

        forDemo();

        enhancedForDemo();

        breakDemo();

        continueDemo();

        returnDemo();
    }

    // =================================================
    // 1. IF DEMO
    // =================================================

    public static void ifDemo() {

        System.out.println("\n1. IF DEMO - CUSTOMER LOGIN");

        boolean loginSuccessful = true;

        if (loginSuccessful) {
            System.out.println("Customer Login Successful");
        }
    }

    // =================================================
    // 2. IF ELSE DEMO
    // =================================================

    public static void ifElseDemo() {

        System.out.println("\n2. IF ELSE DEMO - ACCOUNT VERIFICATION");

        boolean accountVerified = false;

        if (accountVerified) {
            System.out.println("Account Verified");
        } else {
            System.out.println("Account Verification Failed");
        }
    }

    // =================================================
    // 3. IF ELSE IF DEMO
    // =================================================

    public static void elseIfDemo() {

        System.out.println("\n3. IF ELSE IF DEMO - LOAN ELIGIBILITY");

        double salary = 85000;

        if (salary >= 150000) {
            System.out.println("Eligible For Premium Loan");
        }
        else if (salary >= 80000) {
            System.out.println("Eligible For Home Loan");
        }
        else if (salary >= 50000) {
            System.out.println("Eligible For Personal Loan");
        }
        else {
            System.out.println("Loan Not Approved");
        }
    }

    // =================================================
    // 4. NESTED IF DEMO
    // =================================================

    public static void nestedIfDemo() {

        System.out.println("\n4. NESTED IF DEMO - WITHDRAWAL");

        boolean cardValid = true;
        boolean pinCorrect = true;

        if (cardValid) {

            if (pinCorrect) {

                System.out.println("Cash Withdrawal Approved");

            }
        }
    }

    // =================================================
    // 5. SWITCH DEMO
    // =================================================

    public static void switchDemo() {

        System.out.println("\n5. SWITCH DEMO - ACCOUNT TYPE");

        int accountType = 2;

        switch (accountType) {

            case 1:
                System.out.println("Savings Account");
                break;

            case 2:
                System.out.println("Current Account");
                break;

            case 3:
                System.out.println("Fixed Deposit Account");
                break;

            default:
                System.out.println("Invalid Account Type");
        }
    }

    // =================================================
    // 6. WHILE LOOP DEMO
    // =================================================

    public static void whileDemo() {

        System.out.println("\n6. WHILE LOOP DEMO - CUSTOMER QUEUE");

        int customer = 1;

        while (customer <= 5) {

            System.out.println(
                    "Serving Customer " + customer);

            customer++;
        }
    }

    // =================================================
    // 7. DO WHILE DEMO
    // =================================================

    public static void doWhileDemo() {

        System.out.println("\n7. DO WHILE DEMO - ATM MENU");

        int menuCount = 1;

        do {

            System.out.println(
                    "Displaying ATM Menu");

            menuCount++;

        } while (menuCount <= 3);
    }

    // =================================================
    // 8. FOR LOOP DEMO
    // =================================================

    public static void forDemo() {

        System.out.println("\n8. FOR LOOP DEMO - RECEIPT GENERATION");

        for (int receipt = 1; receipt <= 5; receipt++) {

            System.out.println(
                    "Generating Receipt #" + receipt);
        }
    }

    // =================================================
    // 9. ENHANCED FOR LOOP DEMO
    // =================================================

    public static void enhancedForDemo() {

        System.out.println("\n9. ENHANCED FOR LOOP - ACCOUNT LIST");

        String[] accounts = {
                "Savings",
                "Current",
                "FD",
                "Loan"
        };

        for (String account : accounts) {

            System.out.println(account);
        }
    }

    // =================================================
    // 10. BREAK DEMO
    // =================================================

    public static void breakDemo() {

        System.out.println("\n10. BREAK DEMO - SEARCH CUSTOMER");

        for (int customerId = 1001;
             customerId <= 1010;
             customerId++) {

            if (customerId == 1006) {

                System.out.println(
                        "Customer Found");

                break;
            }

            System.out.println(
                    "Searching Customer "
                            + customerId);
        }
    }

    // =================================================
    // 11. CONTINUE DEMO
    // =================================================

    public static void continueDemo() {

        System.out.println("\n11. CONTINUE DEMO - SKIP BLOCKED ACCOUNT");

        for (int account = 1;
             account <= 5;
             account++) {

            if (account == 3) {

                System.out.println(
                        "Account 3 Blocked");

                continue;
            }

            System.out.println(
                    "Processing Account "
                            + account);
        }
    }

    // =================================================
    // 12. RETURN DEMO
    // =================================================

    public static void returnDemo() {

        System.out.println("\n12. RETURN DEMO - TRANSACTION STATUS");

        boolean status = processTransaction(5000);

        System.out.println(
                "Transaction Success : " + status);
    }

    public static boolean processTransaction(double amount) {

        if (amount <= 0) {

            return false;
        }

        return true;
    }
}