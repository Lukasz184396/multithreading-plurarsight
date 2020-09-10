package com.zawadal;

import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AdderTest {

    public static String BASE_DIR = "src/test/resources/";

    //single thread
    @Test
    public void doAddTest() {
        String[] inFiles = {BASE_DIR + "files1.txt", BASE_DIR + "files2.txt", BASE_DIR + "files3.txt", //
                BASE_DIR + "files4.txt", BASE_DIR + "files5.txt", BASE_DIR + "files6.txt"};

        String[] outFiles = {BASE_DIR + "filesout1.txt", BASE_DIR + "filesout2.txt", BASE_DIR + "filesout3.txt",  //
                BASE_DIR + "filesout4.txt", BASE_DIR + "filesout5.txt", BASE_DIR + "filesout6.txt"};
//        String[] outFiles = {"./filesout1.txt", "./filesout2.txt", "./filesout3.txt", //
//                "./filesout4.txt", "./filesout5.txt", "./filesout6.txt"};

        Thread [] threads = new Thread[inFiles.length];
        for (int i = 0; i < inFiles.length; i++) {
            Adder adder = new Adder(inFiles[i], outFiles[i]);
//                adder.doAdd();
            threads[i] = new Thread(adder);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); //blocks waiting for thread compeltition
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}