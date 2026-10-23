package day17;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// 1. Defining a thread by extending Thread or implementing Runnable
class WorkerTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Java 7 Worker Thread running: " + Thread.currentThread().getName());
    }
}

public class DefineStartDemoJ7 {
    public static void main(String[] args) {
        // 2. Instantiating a Thread
        WorkerTask task = new WorkerTask();
        Thread thread = new Thread(task, "Worker-1");

        // 3. Starting the Thread
        thread.start();
    }
}


class BankAccountJava7 {
    private int balance = 1000;
    private final Object lock = new Object();

    // Synchronized Block with explicit lock object
    public void withdraw(int amount) {
        synchronized (lock) {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " remaining balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " - Insufficient funds");
            }
        }
    }
}


class BankAccountJava8 {
    // Lock-free thread-safe atomic variable
    private final AtomicInteger balance = new AtomicInteger(1000);

    public void withdraw(int amount) {
        // Atomic compare-and-swap mechanism
        balance.updateAndGet(current -> {
            if (current >= amount) {
                System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
                return current - amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " - Insufficient funds");
                return current;
            }
        });
    }
}

class ResourceJava7 {
    private final String id;
    public ResourceJava7(String id) { this.id = id; }
    public String getId() { return id; }
}

