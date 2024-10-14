package com.wavemaker.multithreadingprograms.defineathread.extendsThread;

public class MainThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        for (int i = 1; i <= 5; i++) {
            System.out.println(i+". Hello and welcome! I am Main Thread");
        }
    }
}