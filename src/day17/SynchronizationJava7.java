package day17;

public class SynchronizationJava7 {
    public static void main(String[] args) {
        final BankAccountJava7 account = new BankAccountJava7();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                account.withdraw(700);
            }
        };

        new Thread(task, "Thread-A").start();
        new Thread(task, "Thread-B").start();
    }
}
