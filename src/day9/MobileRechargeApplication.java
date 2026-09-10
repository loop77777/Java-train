package day9;//Mobile Recharge & Digital day9.Wallet Management System (MRDWMS)
//Enterprise Real-Time Case Study
//Domain
//
//Telecommunications Industry
//
//Application Type
//
//Prepaid Mobile Recharge and Digital day9.Wallet Platform
//
//Target Organizations
//Reliance Jio
//Bharti Airtel
//Vodafone Idea (Vi)
//Bharat Sanchar Nigam Limited (BSNL)
//Mobile Virtual Network Operators (MVNOs)
//1. Executive Summary
//
//The Mobile Recharge & Digital day9.Wallet Management System (MRDWMS) is a centralized enterprise telecom platform designed to facilitate secure, fast, and reliable prepaid mobile recharge services through an integrated digital wallet mechanism.
//
//The system enables telecom subscribers to manage their recharge activities digitally while ensuring transaction accuracy, operational efficiency, and enhanced customer satisfaction.
//
//The platform supports:
//
//day9.Customer Registration and Profile Management
//Mobile Number Verification and Validation
//Recharge Plan Browsing and Selection
//Digital day9.Wallet Operations
//Cashback and Promotional Offer Processing
//Recharge day9.Transaction Management
//Invoice and Receipt Generation
//Recharge History Tracking
//Audit Logging and Compliance Monitoring
//Operational and Financial Reporting
//
//The primary goal of the system is to provide a highly scalable, maintainable, secure, and fault-tolerant recharge ecosystem capable of handling millions of transactions daily across multiple telecom operators.
//
//2. Business Problem Statement
//
//Telecommunication service providers process millions of recharge requests every day from customers across different regions and channels.
//
//Without a centralized recharge management solution, organizations often face numerous operational and financial challenges, including:
//
//Invalid or incorrectly entered mobile numbers
//Selection of inappropriate recharge plans
//Insufficient wallet balances
//Duplicate recharge attempts
//Incorrect cashback calculations
//Failed recharge transactions
//Revenue leakage
//day9.Customer complaints and disputes
//Lack of transaction traceability
//Inaccurate financial reporting
//Manual intervention for issue resolution
//
//These challenges can negatively impact customer experience, operational efficiency, and business profitability.
//
//Therefore, telecom organizations require a robust and intelligent recharge platform capable of minimizing errors, automating validations, ensuring transaction integrity, and delivering a seamless recharge experience.
//
//3. Stakeholders
//Primary Stakeholders
//day9.Customer
//
//Uses the platform to perform prepaid mobile recharges, manage wallet balances, and view recharge history.
//
//Telecom Provider
//
//Creates and manages recharge plans, promotional offers, and business policies.
//
//Finance Team
//
//Monitors transaction settlements, revenue collection, and cashback expenditures.
//
//day9.Customer Support Team
//
//Handles recharge-related complaints, disputes, and service requests.
//
//Management Team
//
//Tracks key business metrics, operational reports, and overall platform performance.
//
//Secondary Stakeholders
//Software Developers
//Quality Assurance (QA) Engineers
//System Administrators
//Security and Compliance Teams
//Internal Auditors
//Business Analysts
//Database Administrators
//DevOps Engineers
//4. Key Actors
//Actor 1: day9.Customer
//
//The customer can perform the following operations:
//
//Register on the platform
//View available recharge plans
//Perform mobile recharges
//Add funds to a wallet
//Check wallet balance
//View invoices and receipts
//View recharge transaction history
//Track cashback and rewards
//Actor 2: Recharge Service
//
//Responsible for:
//
//day9.Customer validation
//Recharge plan verification
//day9.Wallet balance verification
//Recharge processing
//Recharge status management
//day9.Transaction execution
//Actor 3: Offer & Cashback Engine
//
//Responsible for:
//
//Offer eligibility validation
//Cashback rule evaluation
//Reward calculations
//Promotional campaign processing
//Actor 4: day9.Transaction Management Service
//
//Responsible for:
//
//day9.Transaction ID generation
//day9.Transaction lifecycle management
//Audit trail generation
//Persistent transaction storage
//Failure recovery mechanisms
//5. Project Scope
//In Scope
//
//The system includes:
//
//day9.Customer Registration Management
//Recharge Plan Management
//Digital day9.Wallet Management
//Recharge Processing
//Cashback and Offer Engine
//day9.Transaction Processing
//Invoice Generation
//Audit Logging
//day9.Customer Reports
//Financial Reports
//day9.Transaction History Management
//Out of Scope
//
//The following integrations are excluded from the current implementation:
//
//UPI Payment Integration
//Credit/Debit Card Payment Processing
//Net Banking Integration
//Telecom Network Switching Infrastructure
//SMS Gateway Services
//Email Gateway Services
//Third-Party Loyalty Programs
//Aadhaar/eKYC Integrations
//6. Business Objectives
//
//The Mobile Recharge & Digital day9.Wallet Management System aims to:
//
//Operational Objectives
//Automate prepaid recharge operations
//Reduce manual intervention
//Eliminate duplicate transactions
//Improve transaction success rates
//day9.Customer Experience Objectives
//Provide faster recharge processing
//Offer personalized cashback benefits
//Deliver easy wallet management
//Improve customer satisfaction
//Financial Objectives
//Increase recharge revenue
//Reduce operational costs
//Accurately manage cashback distribution
//Improve financial transparency
//Technical Objectives
//Ensure high system availability
//Support millions of customers
//Provide transaction-level auditability
//Maintain strong security controls

//+--------------------------------+
//| day9.Customer |
//+--------------------------------+
//| - customerId : int |
//| - customerName : String |
//| - mobileNumber : String |
//| - wallet : day9.Wallet |
//+--------------------------------+
//| + register() |
//| + recharge() |
//| + viewHistory() |
//+--------------------------------+
//
//1
//|
//| Composition
//|
//1
//
//+--------------------------------+
//| day9.Wallet |
//+--------------------------------+
//| - walletId : int |
//| - balance : double |
//+--------------------------------+
//| + credit() |
//| + debit() |
//| + getBalance() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.RechargePlan |
//+--------------------------------+
//| - planId : int |
//| - planType : day9.PlanType |
//| - price : double |
//| - validity : int |
//| - dataBenefit : String |
//+--------------------------------+
//| + displayPlan() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.PlanType |
//+--------------------------------+
//| <> |
//+--------------------------------+
//| DAILY_PLAN |
//| WEEKLY_PLAN |
//| MONTHLY_PLAN |
//| YEARLY_PLAN |
//+--------------------------------+
//
//+--------------------------------+
//| day9.OfferEngine |
//+--------------------------------+
//| + calculateCashback() |
//| + validateOffer() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.Transaction |
//+--------------------------------+
//| - transactionId : String |
//| - amount : double |
//| - cashback : double |
//| - date : String |
//+--------------------------------+
//| + displayTransaction() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.TransactionManager |
//+--------------------------------+
//| - transactions[] :day9.Transaction |
//| - count : int |
//+--------------------------------+
//| + addTransaction() |
//| + generateTransactionId() |
//| + getTransaction() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.RechargeService |
//+--------------------------------+
//| - plans[] : day9.RechargePlan |
//| - transactionManager |
//| - offerEngine |
//+--------------------------------+
//| + processRecharge() |
//| + validatePlan() |
//| + validateWallet() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.InvoiceService |
//+--------------------------------+
//| + generateInvoice() |
//+--------------------------------+
//
//+--------------------------------+
//| day9.AuditLogger |
//+--------------------------------+
//| - logs[] : String |
//| - count : int |
//+--------------------------------+
//| + logEvent() |
//| + displayLogs() |
//+--------------------------------+

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ============================================================================
// 1. CUSTOM DOMAIN EXCEPTIONS
// ============================================================================
class InvalidMobileNumberException extends RuntimeException {
    public InvalidMobileNumberException(String message) {
        super(message);
    }
}

class InvalidCustomerException extends RuntimeException {
    public InvalidCustomerException(String message) {
        super(message);
    }
}

class InvalidPlanException extends RuntimeException {
    public InvalidPlanException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// ============================================================================
// 2. ENUM: day9.PlanType
// ============================================================================
enum PlanType {
    DAILY_PLAN,
    WEEKLY_PLAN,
    MONTHLY_PLAN,
    YEARLY_PLAN
}

// ============================================================================
// 3. CLASS: day9.RechargePlan
// ============================================================================
class RechargePlan {
    private int planId;
    private PlanType planType;
    private double price;
    private int validity;
    private String dataBenefit;

    public RechargePlan(int planId, PlanType planType, double price, int validity, String dataBenefit) {
        this.planId = planId;
        this.planType = planType;
        this.price = price;
        this.validity = validity;
        this.dataBenefit = dataBenefit;
    }

    public int getPlanId() {
        return planId;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public double getPrice() {
        return price;
    }

    public int getValidity() {
        return validity;
    }

    public String getDataBenefit() {
        return dataBenefit;
    }

    public void displayPlan() {
        System.out.printf("Plan #%d | Type: %-12s | Price: Rs. %-7.2f | Validity: %3d Days | Data: %s%n",
                planId, planType, price, validity, dataBenefit);
    }
}

// ============================================================================
// 4. CLASS: day9.Wallet (Composition inside day9.Customer)
// ============================================================================
class Wallet {
    private int walletId;
    private double balance;

    public Wallet(int walletId, double initialBalance) {
        this.walletId = walletId;
        this.balance = Math.max(0.0, initialBalance);
    }

    public int getWalletId() {
        return walletId;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void credit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public synchronized void debit(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Debit amount must be strictly positive.");
        }
        if (this.balance < amount) {
            throw new InsufficientBalanceException(String.format(
                    "Insufficient wallet balance! Required: Rs.%.2f, Available: Rs.%.2f", amount, this.balance));
        }
        this.balance -= amount;
    }
}

// ============================================================================
// 5. CLASS: day9.Transaction
// ============================================================================
class Transaction {
    private String transactionId;
    private double amount;
    private double cashback;
    private String date;

    public Transaction(String transactionId, double amount, double cashback, String date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.cashback = cashback;
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public double getCashback() {
        return cashback;
    }

    public String getDate() {
        return date;
    }

    public void displayTransaction() {
        System.out.printf("  -> [TXN ID: %s] Date: %s | Amount: Rs.%.2f | Cashback: Rs.%.2f%n",
                transactionId, date, amount, cashback);
    }
}

// ============================================================================
// 6. CLASS: day9.TransactionManager
// ============================================================================
class TransactionManager {
    private Transaction[] transactions;
    private int count;
    private static int txnSequence = 100000;

    public TransactionManager(int capacity) {
        this.transactions = new Transaction[capacity];
        this.count = 0;
    }

    public String generateTransactionId() {
        txnSequence++;
        return "TXN-" + txnSequence;
    }

    public void addTransaction(Transaction txn) {
        if (count < transactions.length) {
            transactions[count++] = txn;
        } else {
            // Dynamic array resize if capacity is reached
            Transaction[] resized = new Transaction[transactions.length * 2];
            System.arraycopy(transactions, 0, resized, 0, transactions.length);
            transactions = resized;
            transactions[count++] = txn;
        }
    }

    public Transaction getTransaction(String transactionId) {
        for (int i = 0; i < count; i++) {
            if (transactions[i].getTransactionId().equalsIgnoreCase(transactionId)) {
                return transactions[i];
            }
        }
        return null;
    }

    public Transaction[] getAllTransactions() {
        Transaction[] current = new Transaction[count];
        System.arraycopy(transactions, 0, current, 0, count);
        return current;
    }

    public int getCount() {
        return count;
    }
}

// ============================================================================
// 7. CLASS: day9.AuditLogger
// ============================================================================
class AuditLogger {
    private String[] logs;
    private int count;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public AuditLogger(int capacity) {
        this.logs = new String[capacity];
        this.count = 0;
    }

    public void logEvent(String event) {
        String timestampedLog = "[" + LocalDateTime.now().format(FORMATTER) + "] " + event;
        if (count < logs.length) {
            logs[count++] = timestampedLog;
        } else {
            String[] resized = new String[logs.length * 2];
            System.arraycopy(logs, 0, resized, 0, logs.length);
            logs = resized;
            logs[count++] = timestampedLog;
        }
    }

    public void displayLogs() {
        System.out.println("\n=======================================================");
        System.out.println("                 SYSTEM AUDIT LOG TRAIL                ");
        System.out.println("=======================================================");
        for (int i = 0; i < count; i++) {
            System.out.println(logs[i]);
        }
        System.out.println("=======================================================\n");
    }
}

// ============================================================================
// 8. CLASS: day9.OfferEngine
// ============================================================================
class OfferEngine {

    public double calculateCashback(double amount) {
        if (amount >= 999.0) {
            return amount * 0.20; // 20% Cashback
        } else if (amount >= 299.0) {
            return amount * 0.10; // 10% Cashback
        }
        return 0.0;
    }

    public boolean validateOffer(RechargePlan plan) {
        return plan != null && plan.getPrice() >= 299.0;
    }
}

// ============================================================================
// 9. CLASS: day9.InvoiceService
// ============================================================================
class InvoiceService {

    public void generateInvoice(Customer customer, RechargePlan plan, Transaction txn, double finalBalance) {
        System.out.println("=======================================================");
        System.out.println("            OFFICIAL PREPAID RECHARGE INVOICE          ");
        System.out.println("=======================================================");
        System.out.printf(" day9.Transaction ID   : %s%n", txn.getTransactionId());
        System.out.printf(" Date & Time      : %s%n", txn.getDate());
        System.out.printf(" day9.Customer ID      : %d%n", customer.getCustomerId());
        System.out.printf(" day9.Customer Name    : %s%n", customer.getCustomerName());
        System.out.printf(" Mobile Number    : +91-%s%n", customer.getMobileNumber());
        System.out.println("-------------------------------------------------------");
        System.out.printf(" Plan Subscribed  : %s%n", plan.getPlanType());
        System.out.printf(" Validity         : %d Days%n", plan.getValidity());
        System.out.printf(" Benefits         : %s%n", plan.getDataBenefit());
        System.out.println("-------------------------------------------------------");
        System.out.printf(" Base Price       : Rs. %10.2f%n", txn.getAmount());
        System.out.printf(" Cashback Earned  : Rs. %10.2f%n", txn.getCashback());
        System.out.printf(" Effective Paid   : Rs. %10.2f%n", (txn.getAmount() - txn.getCashback()));
        System.out.println("-------------------------------------------------------");
        System.out.printf(" Closing Balance  : Rs. %10.2f%n", finalBalance);
        System.out.println("=======================================================\n");
    }
}

// ============================================================================
// 10. CLASS: day9.RechargeService
// ============================================================================
class RechargeService {
    private RechargePlan[] plans;
    private TransactionManager transactionManager;
    private OfferEngine offerEngine;
    private InvoiceService invoiceService;
    private AuditLogger auditLogger;

    public RechargeService(TransactionManager transactionManager, OfferEngine offerEngine,
                           InvoiceService invoiceService, AuditLogger auditLogger) {
        this.transactionManager = transactionManager;
        this.offerEngine = offerEngine;
        this.invoiceService = invoiceService;
        this.auditLogger = auditLogger;
        initializeCatalog();
    }

    private void initializeCatalog() {
        this.plans = new RechargePlan[] {
                new RechargePlan(1, PlanType.DAILY_PLAN, 19.0, 1, "1GB Total + Unlimited Calls"),
                new RechargePlan(2, PlanType.WEEKLY_PLAN, 99.0, 7, "1.5GB/Day + Unlimited Calls"),
                new RechargePlan(3, PlanType.MONTHLY_PLAN, 299.0, 28, "2GB/Day + 100 SMS/Day"),
                new RechargePlan(4, PlanType.YEARLY_PLAN, 2999.0, 365, "2.5GB/Day + 100 SMS/Day + OTT")
        };
    }

    public RechargePlan[] getPlans() {
        return plans;
    }

    public void displayAllPlans() {
        System.out.println("\n--- AVAILABLE PREPAID TARIFF PLANS ---");
        for (RechargePlan p : plans) {
            p.displayPlan();
        }
        System.out.println();
    }

    public RechargePlan validatePlan(PlanType planType) {
        for (RechargePlan plan : plans) {
            if (plan.getPlanType() == planType) {
                return plan;
            }
        }
        throw new InvalidPlanException("Selected day9.PlanType '" + planType + "' is not supported in the active catalog.");
    }

    public void validateWallet(Wallet wallet, double requiredAmount) throws InsufficientBalanceException {
        if (wallet == null) {
            throw new IllegalArgumentException("day9.Wallet reference cannot be null.");
        }
        if (wallet.getBalance() < requiredAmount) {
            throw new InsufficientBalanceException(String.format(
                    "Insufficient day9.Wallet Balance! Required: Rs.%.2f, Available: Rs.%.2f",
                    requiredAmount, wallet.getBalance()));
        }
    }

    public Transaction processRecharge(Customer customer, PlanType planType) {
        if (customer == null) {
            throw new InvalidCustomerException("Recharge failed: day9.Customer cannot be null.");
        }

        RechargePlan plan = validatePlan(planType);
        Wallet wallet = customer.getWallet();

        try {
            // Validate balance
            validateWallet(wallet, plan.getPrice());

            // 1. Debit wallet
            wallet.debit(plan.getPrice());

            // 2. Calculate and apply cashback
            double cashback = offerEngine.calculateCashback(plan.getPrice());
            if (cashback > 0) {
                wallet.credit(cashback);
            }

            // 3. Create and record day9.Transaction
            String txnId = transactionManager.generateTransactionId();
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            Transaction txn = new Transaction(txnId, plan.getPrice(), cashback, dateStr);

            transactionManager.addTransaction(txn);
            customer.addTransactionToHistory(txn);

            // 4. Log Audit Event
            auditLogger.logEvent(String.format("RECHARGE_SUCCESS | day9.Customer: %d (%s) | Plan: %s | Txn: %s | Paid: Rs.%.2f",
                    customer.getCustomerId(), customer.getMobileNumber(), plan.getPlanType(), txnId, plan.getPrice()));

            // 5. Generate Invoice
            invoiceService.generateInvoice(customer, plan, txn, wallet.getBalance());
            return txn;

        } catch (InsufficientBalanceException ex) {
            auditLogger.logEvent(String.format("RECHARGE_FAILED | day9.Customer: %d | Plan: %s | Reason: %s",
                    customer.getCustomerId(), plan.getPlanType(), ex.getMessage()));
            System.err.println("[RECHARGE FAILED] " + ex.getMessage());
            return null;
        }
    }
}

// ============================================================================
// 11. CLASS: day9.Customer (Composition with day9.Wallet)
// ============================================================================
class Customer {
    private int customerId;
    private String customerName;
    private String mobileNumber;
    private Wallet wallet; // Composition: 1-to-1 lifecycle ownership
    private Transaction[] rechargeHistory;
    private int historyCount;

    private static final String INDIAN_MSISDN_REGEX = "^[6-9]\\d{9}$";

    public Customer(int customerId, String customerName, String mobileNumber, double initialWalletBalance) {
        register(customerId, customerName, mobileNumber, initialWalletBalance);
        this.rechargeHistory = new Transaction[20];
        this.historyCount = 0;
    }

    public void register(int customerId, String customerName, String mobileNumber, double initialWalletBalance) {
        if (customerId <= 0) {
            throw new InvalidCustomerException("day9.Customer ID must be a positive integer.");
        }
        if (customerName == null || customerName.trim().length() < 3 || customerName.trim().length() > 50) {
            throw new InvalidCustomerException("day9.Customer name length must be between 3 and 50 characters.");
        }
        if (mobileNumber == null || !mobileNumber.trim().matches(INDIAN_MSISDN_REGEX)) {
            throw new InvalidMobileNumberException("Invalid Mobile Number [" + mobileNumber + "]. Must be 10 digits starting with 6, 7, 8, or 9.");
        }

        this.customerId = customerId;
        this.customerName = customerName.trim();
        this.mobileNumber = mobileNumber.trim();
        this.wallet = new Wallet(customerId + 1000, initialWalletBalance); // Composition instantiation
    }

    public void recharge(RechargeService service, PlanType planType) {
        service.processRecharge(this, planType);
    }

    public void addTransactionToHistory(Transaction txn) {
        if (historyCount < rechargeHistory.length) {
            rechargeHistory[historyCount++] = txn;
        }
    }

    public void viewHistory() {
        System.out.println("--- Recharge History for " + customerName + " (+91-" + mobileNumber + ") ---");
        if (historyCount == 0) {
            System.out.println("  No transactions found.");
        } else {
            for (int i = 0; i < historyCount; i++) {
                rechargeHistory[i].displayTransaction();
            }
        }
        System.out.printf("  Current day9.Wallet Balance: Rs.%.2f%n%n", wallet.getBalance());
    }

    public int getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getMobileNumber() { return mobileNumber; }
    public Wallet getWallet() { return wallet; }
}

// ============================================================================
// 12. MAIN DRIVER APPLICATION
// ============================================================================


public class MobileRechargeApplication {

    public static void main(String[] args) {

        System.out.println("=======================================================");
        System.out.println("   MOBILE RECHARGE MANAGEMENT SYSTEM (MRMS) ENGINE     ");
        System.out.println("=======================================================\n");

        // 1. Initialize core system services
        TransactionManager transactionManager = new TransactionManager(50);
        AuditLogger auditLogger = new AuditLogger(50);
        OfferEngine offerEngine = new OfferEngine();
        InvoiceService invoiceService = new InvoiceService();

        RechargeService rechargeService = new RechargeService(
                transactionManager, offerEngine, invoiceService, auditLogger
        );

        // Display catalog
        rechargeService.displayAllPlans();

        // 2. Register day9.Customer with initial wallet balance (Rs. 3200)
        Customer customer1 = new Customer(101, "Jyothi Prasad", "9876543210", 3200.0);
        auditLogger.logEvent("CUSTOMER_REGISTERED | CustomerId: 101 | Name: Jyothi Prasad | MSISDN: 9876543210");

        // 3. Perform Monthly Recharge (Rs. 299 -> 10% Cashback = Rs. 29.90)
        System.out.println(">>> Executing day9.Transaction 1: Monthly Plan <<<");
        customer1.recharge(rechargeService, PlanType.MONTHLY_PLAN);

        // 4. Perform Yearly Recharge (Rs. 2999 -> 20% Cashback = Rs. 599.80)
        // Required: Rs. 2999 | Available: ~Rs. 2930.90 -> Will trigger day9.InsufficientBalanceException
        System.out.println(">>> Executing day9.Transaction 2: Yearly Plan (Exceeds Balance) <<<");
        customer1.recharge(rechargeService, PlanType.YEARLY_PLAN);

        // 5. day9.Customer tops up wallet and retries
        System.out.println("\n>>> day9.Customer Top-up day9.Wallet with Rs. 1000 <<<");
        customer1.getWallet().credit(1000.0);
        auditLogger.logEvent("WALLET_TOPUP | CustomerId: 101 | Amount: Rs.1000.00 | New Balance: Rs." + customer1.getWallet().getBalance());

        System.out.println(">>> Executing day9.Transaction 3: Retrying Yearly Plan <<<");
        customer1.recharge(rechargeService, PlanType.YEARLY_PLAN);

        // 6. View day9.Customer day9.Transaction History
        customer1.viewHistory();

        // 7. Display System Audit Logs
        auditLogger.displayLogs();

        // 8. Test Invalid day9.Customer Validation Edge Cases
        System.out.println("--- TESTING INPUT VALIDATION EXCEPTIONS ---");
        try {
            new Customer(102, "Al", "9876543210", 500.0); // Fails name length < 3
        } catch (InvalidCustomerException ex) {
            System.out.println("[CAUGHT] " + ex.getMessage());
        }

        try {
            new Customer(103, "Rajesh Kumar", "1234567890", 500.0); // Fails starting digit regex
        } catch (InvalidMobileNumberException ex) {
            System.out.println("[CAUGHT] " + ex.getMessage());
        }
    }
}

//===================================
// MOBILE RECHARGE MANAGEMENT SYSTEM (MRMS) ENGINE
//===================================
//
//
//---AVAILABLE PREPAID TARIFF PLANS---
//Plan #1 | Type: DAILY_PLAN   | Price: Rs. 19.00   | Validity:   1 Days | Data: 1GB Total + Unlimited Calls
//Plan #2 | Type: WEEKLY_PLAN  | Price: Rs. 99.00   | Validity:   7 Days | Data: 1.5GB/Day + Unlimited calls
//Plan #3 | Type: MONTHLY_PLAN | Price: Rs. 299.00  | Validity:  28 Days | Data: 2GB/Day + 100 SMS/Day
//Plan #4 | Type: YEARLY_PLAN  | Price: Rs. 2999.00 | Validity: 265 Days | Data: 2.5GB/day + 100 SMS/Day + OTT
//
//>>>Executing Transaction 1: Monthly Plan<<<
//>>>Executing Transaction 2:Yearly Plan (Exceeds Balance)<<<
//==================================================
// OFFICIAL PREPAID RECHARGE INVOICE
//===================================================
//  Transaction ID : TXN-100001
// Date & Time   :08-09-2026 07:58:13
// Customer ID : 101
// Customer Name : Jyothi Prasad
// Mobile Number : +91-null
//===================================
// Plan Subscribed :YEARLY_PLAN
// Validity   :265 Days
//Benefits   :2.5GB/day + 100 SMS/Day + OTT
//========================================
// Base Price  : rs.    2999.00
// CashBack Earned : Rs.     599.80
// Effective Paid  :Rs.    2399.20
//======================================================================
// Closing Balance :Rs.     800.80
//=======================================================================
//
//
//>>>Customer Top-up Wallet with Rs. 1000<<<
//>>>Executing Transaction 3: Retrying Yearly Plan<<<
//[RECHARGE FAILED] Insuffiecient Wallet Balance! Required: rs.2999.00,Available:Rs.1800.80
//---Recharge History for Jyothi Prasad (+91-null)---
//->[TXN ID: TXN-100001] Date: 08-09-2026 07:58:13 | Amount: Rs.2999.00 | Cashback: Rs.599.80
// Current Wallet Balance: Rs.1800.80
//
//
//==========================================
//  SYSTEM AUDIT LOG TRAIL
//=============================================
//[08-09-2026 07:58:12]CUSTOMER_REGISTERED | CustomerId: 101 | Name: Jyothi Prasad | MSISDN: 9876543210
//[08-09-2026 07:58:13]RECHARGE_SUCCESS | Customer: 101 (null) | Plan: YEARLY_PLAN | Txn: TXN-100001 | Paid: Rs.2999.00
//[08-09-2026 07:58:13]WALLET_TOPUP | CustomerId: 101 | Amount: Rs.1000.00 | New Balance: Rs.1800.8000000000002
//[08-09-2026 07:58:13]RECHARGE_FAILED | Customer: 101 | Plan: YEARLY_PLAN | Reason:  Insuffiecient Wallet Balance! Required: rs.2999.00,Available:Rs.1800.80
//============================================
//
//---TESTING INPUT VALIDATION EXCEPTIONS---
//[CAUGHT]Customer name length must be between 3 and 50 characters
//[CAUGHT]Invalid Mobile Number [1234567890]. Must be 10 digits starting with 6,7,8 or 9.