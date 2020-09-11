package com.zawadal.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class WorkerTest {

    @Test
    public void depositTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);

        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(account);
            executorService.submit(worker);
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(600, account.getBalance()); //not always when threads share same resource without lock
    }
}