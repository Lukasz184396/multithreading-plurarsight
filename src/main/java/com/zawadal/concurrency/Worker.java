package com.zawadal.concurrency;

public class Worker implements Runnable {
    private BankAccount account;

    public Worker(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (account) {
                int startBalance = account.getBalance();
                System.out.println("startBalance :" + startBalance);
                account.deposit(10);
                int endBalance = account.getBalance();
                System.out.println("endBalance: " + endBalance);
            }
        }

    }
}
