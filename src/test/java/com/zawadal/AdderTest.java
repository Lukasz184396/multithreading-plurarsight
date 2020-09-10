package com.zawadal;

import org.junit.Test;

import java.util.concurrent.*;

public class AdderTest {

    public static String BASE_DIR = "src/test/resources/";

    @Test
    public void doAddTest() {
        String[] inFiles = {BASE_DIR + "files1.txt", BASE_DIR + "files2.txt", BASE_DIR + "files3.txt", //
                BASE_DIR + "files4.txt", BASE_DIR + "files5.txt", BASE_DIR + "files6.txt"};

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];

        for (int i = 0; i < inFiles.length; i++) {
            Adder adder = new Adder(inFiles[i]);
            results[i] = executorService.submit(adder);
        }

        for (Future<Integer> result : results) {
            try {
                int value = result.get(); //bloks until return value available
                System.out.println("Total: " + value);
            } catch (ExecutionException e) {
                e.printStackTrace();
                Throwable adderEx = e.getCause();
                //do something with adder exception
            } catch (Exception e) {
                //non adder exception
            }
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}