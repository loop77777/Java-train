package day17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizationJava8 {
    public static void main(String[] args) {
        BankAccountJava8 account = new BankAccountJava8();
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Submit concurrent withdrawal requests using method references / lambdas
        pool.submit(() -> account.withdraw(700));
        pool.submit(() -> account.withdraw(700));

        pool.shutdown();
    }
}
